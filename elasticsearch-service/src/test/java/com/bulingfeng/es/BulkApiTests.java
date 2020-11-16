package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
        String index="index-post";
        BulkRequest request = new BulkRequest();
        for (int i = 0; i <5 ; i++) {
            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("user", "kimchy");
//            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearch");
            IndexRequest indexRequest = new IndexRequest(index)
                    .id(i+"").source(jsonMap);
            request.add(indexRequest);
        }

        EsDocumentUtils.bulkApi(request);
    }
}
