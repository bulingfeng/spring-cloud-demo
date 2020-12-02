package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-16
 */
public class BulkApiTests extends ElasticsearchTests {


    /**
     * index重新索引是先删除，然后再插入
     * @throws IOException
     */
    @Test
    public void bulkTest() throws IOException {
        List<String> messages=new ArrayList<>();
        String index="index-post";
        BulkRequest request = new BulkRequest();

        int count=5;
        for (String message : messages) {
            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("user", "kimchy");
//            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "042dbd0510fa4376863a348920772288(1).pdf");
            IndexRequest indexRequest = new IndexRequest(index)
                    .id(count+"").source(jsonMap);
            request.add(indexRequest);
            count++;
        }
        EsDocumentUtils.bulkApi(request);
    }
}
