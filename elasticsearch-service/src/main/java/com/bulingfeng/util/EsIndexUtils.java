package com.bulingfeng.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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

    public static boolean createIndex(String indexName, XContentBuilder builder) throws IOException {
        if (StringUtils.isEmpty(indexName))
            throw new IllegalArgumentException("索引名称不能为空");
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        CreateIndexRequest request=new CreateIndexRequest(indexName);
        request.mapping(builder);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }


    public static boolean deleteIndex(String indexName) throws IOException {
        if (StringUtils.isEmpty(indexName))
            throw new RuntimeException("索引名称不能为空");
        DeleteIndexRequest request=new DeleteIndexRequest(indexName);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }


    public static boolean existIndex(String indexName) throws IOException {
        if (StringUtils.isEmpty(indexName))
            throw new RuntimeException("索引名称不能为空");
        GetIndexRequest request=new GetIndexRequest(indexName);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        return client.indices().exists(request,RequestOptions.DEFAULT);
    }



    public static XContentBuilder elasticSearchMapping() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("_source");
            {
                builder.field("enabled",false);
            }
            builder.endObject();


            builder.startObject("properties");
            {
                builder.startObject("fileType");
                {
                    builder.field("type", "keyword");
                    builder.field("store",true);
                }
                builder.endObject();

                builder.startObject("content");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                    builder.field("search_analyzer", "ik_smart");
                    builder.field("store",false);
                }
                builder.endObject();


                builder.startObject("fileName");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                    builder.field("search_analyzer", "ik_max_word");
                    builder.field("store",true);
                }
                builder.endObject();



                builder.startObject("createTime");
                {
                    builder.field("type", "keyword");
                    builder.field("store",true);
                }
                builder.endObject();

                builder.startObject("roomId");
                {
                    builder.field("type", "keyword");
                    builder.field("store",true);
                }
                builder.endObject();


                builder.startObject("path");
                {
                    builder.field("type", "keyword");
                    builder.field("store",true);
                }
                builder.endObject();


            }
            builder.endObject();
        }
        builder.endObject();
        return builder;
    }
}
