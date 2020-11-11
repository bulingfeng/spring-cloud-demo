package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import com.bulingfeng.util.EsIndexUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 * 打分
 *
 */

public class DocumentTests extends ElasticsearchTests {

    @Test
    public void createIndex() throws IOException {
        String indexName="index-test-20201111";
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            // 设置mappings
                builder.startObject("_source");
                {
                    builder.field("includes",new String[]{"postDate","user"});
                    builder.field("excludes",new String[]{"content"});
                }
                builder.endObject();


            builder.startObject("properties");
            {
                // 1、内容字段设置mapping
                builder.startObject("content");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();
                // 2、标题设置mapping
                builder.startObject("user");
                {
                    builder.field("type", "keyword");
//                    builder.field("type", "text");
//                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
//                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();


            }
            builder.endObject();
        }
        builder.endObject();

        EsIndexUtils.createIndex(indexName,builder);
    }

    @Test
    public void insertDocumentToEs() throws IOException {
        String index="index-test-20201111";
        List<String> documents= Arrays.asList("谷歌地图之父跳槽facebook",
                "谷歌地图之父加盟facebook",
                "谷歌地图创始人拉斯离开谷歌加盟facebook",
                "谷歌地图之父跳槽facebook与wave项目取消有关",
                "谷歌地图之父拉斯加盟社交网站facebook"
                );

        int i=1;
        for (String document : documents) {
            EsDocumentUtils.addDocument(index,i+"",document);
            i++;
        }


        while (true){
            EsDocumentUtils.addDocument(index,i+"",null);
            i++;
            if (i==10) return;
        }
    }

    @Test
    public void queryAllDocument() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.queryAllDocument("twitter");
        System.out.println(searchResponse);
    }

    @Test
    public void queryDocumentByCondition() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.getDocumentByCondition("index-test-20201105","user","bulingfeng");
        System.out.println(searchResponse.getHits().getHits()[0]);

//        SearchResponse searchResponse1=EsDocumentUtils.queryAllDocument("index");
//        System.out.println(searchResponse1);

    }

    @Test
    public void queryDocByTemplate() throws IOException {
        SearchTemplateResponse response = EsDocumentUtils.getDocumentByTemplate("index");
        System.out.println(response.getResponse().getHits().getHits()[0]);
    }

    @Test
    public void deleteDocument() throws IOException {
        EsDocumentUtils.deleteDocument("index","1");
    }

    @Test
    public void updateDocument() throws IOException {
        Map<String,Object> map=new HashMap<>();
        map.put("user","bulingfeng-20201110");
        map.put("content","谷歌地图创始人拉斯离开谷歌加盟facebook");
        UpdateResponse response = EsDocumentUtils.updateDocumentById("index-test-20201110", "3", map);
        System.out.println(response);
    }



    @Test
    public void scrollBoolSearchTest(){
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .must(QueryBuilders.matchAllQuery());

                EsDocumentUtils.scrollqueryByCondition(boolQueryBuilder,"index-test-20201110");
    }
}
