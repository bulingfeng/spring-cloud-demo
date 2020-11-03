package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 * 打分
 *
 */

public class DocumentTests extends ElasticsearchTests {

    @Test
    public void insertDocumentToEs() throws IOException {
        List<String> documents= Arrays.asList("谷歌地图之父跳槽facebook",
                "谷歌地图之父加盟facebook",
                "谷歌地图创始人拉斯离开谷歌加盟facebook",
                "谷歌地图之父跳槽facebook与wave项目取消有关",
                "谷歌地图之父拉斯加盟社交网站facebook"
                );

        int i=1;
        for (String document : documents) {
            EsDocumentUtils.addDocument("index",i+"",document);
            i++;
        }
    }

    @Test
    public void queryAllDocument() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.queryAllDocument("twitter");
        System.out.println(searchResponse);
    }

    @Test
    public void queryDocumentByCondition() throws IOException {
        SearchResponse searchResponse = EsDocumentUtils.getDocumentByCondition("index","content","取消");
        System.out.println(searchResponse.getHits().getHits()[0]);

//        SearchResponse searchResponse1=EsDocumentUtils.queryAllDocument("index");
//        System.out.println(searchResponse1);

    }

    @Test
    public void queryDocByTemplate() throws IOException {
        SearchTemplateResponse response = EsDocumentUtils.getDocumentByTemplate("index");
        System.out.println(response.getResponse().getHits().getHits()[0]);
    }
}
