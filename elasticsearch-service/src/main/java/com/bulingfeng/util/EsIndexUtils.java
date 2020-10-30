package com.bulingfeng.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 */
public class EsIndexUtils {


    public static boolean createIndex(String indexName) throws IOException {
        if (StringUtils.isEmpty(indexName))
            throw new IllegalArgumentException("索引名称不能为空");
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        CreateIndexRequest request=new CreateIndexRequest(indexName);
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"message\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return true;
    }


    public static boolean deleteIndex(String indexName) throws IOException {
        if (StringUtils.isEmpty(indexName))
            throw new RuntimeException("索引名称不能为空");
        DeleteIndexRequest request=new DeleteIndexRequest(indexName);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

    


}
