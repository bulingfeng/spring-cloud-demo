package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsClientUtils;
import com.bulingfeng.util.EsDocumentUtils;
import com.bulingfeng.util.EsIndexUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
        String indexName="index-test-20201120";
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            // 设置mappings
//                builder.startObject("_source");
//                {
//                    builder.field("includes",new String[]{"postDate","user"});
//                    builder.field("excludes",new String[]{"content"});
//                }
//                builder.endObject();


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
    public void createIndex2() throws IOException {
        String indexName="index-test-20201124";
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            // 设置mappings
            builder.startObject("_source");
            {

                builder.field("enabled",false);
            }
            builder.endObject();


            builder.startObject("properties");
            {
                // 1、内容字段设置mapping
                builder.startObject("content");
                {
                    builder.field("store",false);
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();
                // 2、标题设置mapping
                builder.startObject("user");
                {
                    builder.field("type", "keyword");
                    builder.field("store",false);
//                    builder.field("type", "text");
//                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
//                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();


                // 2、标题设置mapping
                builder.startObject("postDate");
                {
                    builder.field("type", "keyword");
                    builder.field("store",false);
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
        String index="index-test-20201124";
        List<String> documents= Arrays.asList("谷歌地图之父跳槽facebook",
                "谷歌地图之父加盟facebook",
                "谷歌地图创始人拉斯离开谷歌加盟facebook",
                "谷歌地图之父跳槽facebook与wave项目取消有关",
                "谷歌地图之父拉斯加盟社交网站facebook"
                );
        int i=11;
        for (String document : documents) {
            EsDocumentUtils.addDocument(index,i+"",document);
            i++;
        }
    }


    @Test
    public void insertDocumentToEs2() throws IOException {
        String index="index-test-20201126-1";
        List<String> documents= Arrays.asList("I have two dogs"
        );
        int i=11;
        for (String document : documents) {
            EsDocumentUtils.addDocument(index,i+"",document);
            i++;
        }
    }

    @Test
    public void queryAllDocument() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.queryAllDocument("index-test-20201106-alias");
        System.out.println(searchResponse);
    }

    @Test
    public void queryDocumentByCondition() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.getDocumentByCondition("index-test-20201120","user","bulingfeng");
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
    public void hightlightview() throws IOException {
        String indexName="index-test-20201120";
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("content","加盟"));
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("content");
        sourceBuilder.highlighter(highlightBuilder);
        System.out.println(sourceBuilder);
        RestHighLevelClient client= EsClientUtils.getRestHighLevelClient();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("内容:"+searchResponse);

        printHightLightWord(searchResponse);

    }

    @Test
    public void hightTest() throws IOException {
        String indexName="index-test-20201120";
        String name="user";
        String keyWord="bulingfeng";
        SearchRequest searchRequest = new SearchRequest(indexName);
//
//
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery(name, keyWord));
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field(name);
        searchSourceBuilder.highlighter(highlightBuilder);
        RestHighLevelClient client= EsClientUtils.getRestHighLevelClient();
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("内容:"+searchResponse.getHits().getHits()[0]);


        printHightLightWord(searchResponse);
    }


    private void printHightLightWord(SearchResponse searchResponse){
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlight = highlightFields.get("user");
            Text[] fragments = highlight.fragments();
            String fragmentString = fragments[0].string();
            System.out.println(fragmentString);
        }
    }



    @Test
    public void hightLightTemplate() throws IOException {
        String indexName="index-test-20201120";
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest(indexName));

        request.setScriptType(ScriptType.INLINE);
        request.setScript("{\"query\":{\"match\":{\"content\":\"加盟\"}},\"highlight\":{\"fields\":{\"content\":{}}}}");

        Map<String, Object> scriptParams = new HashMap<>();
        scriptParams.put("field", "content");
        scriptParams.put("value", "谷歌");
        scriptParams.put("size", 5);
        request.setScriptParams(scriptParams);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);
        System.out.println("response:"+response);

        printHightLightWord(response.getResponse());
    }


    @Test
    public void sourceBuilderTest() throws IOException {
        String indexName="2020-11-24";
        XContentBuilder xContentBuilder = EsIndexUtils.elasticSearchMapping();
        EsIndexUtils.createIndex(indexName,xContentBuilder);

    }

    @Test
    public void queryBySearchSourceBuilder() throws IOException {
        String indexName="index-test-20201124";
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("user","bulingfeng"));
        sourceBuilder.storedFields(Arrays.asList("postDate","user"));
        SearchResponse searchResponse = EsDocumentUtils.queryDocument(indexName, sourceBuilder);
        System.out.println("内容:"+searchResponse.getHits().getHits());

        resoveStoredFields(searchResponse.getHits().getHits());
    }


    private void resoveStoredFields(SearchHit[] searchHits){
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getFields().get("postDate").getName());
            System.out.println(searchHit.getFields().get("postDate").getValues().get(0));
        }
    }
}
