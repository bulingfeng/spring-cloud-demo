package com.bulingfeng.util;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 */
public class EsDocumentUtils {


    public static void addDocument(String indexName,Object doc) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "bulingfeng");
        jsonMap.put("postDate", new Date());
        jsonMap.put("document", doc);
        IndexRequest indexRequest = new IndexRequest(indexName)
                .source(jsonMap);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        client.close();
    }

    /**
     *
     * @param indexName
     * @param id 文档的id
     * @param doc
     */
    public static void addDocument(String indexName,String id,Object doc) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "bulingfeng");
        jsonMap.put("postDate", new Date());
        jsonMap.put("content", doc);
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(id).source(jsonMap);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        client.close();
    }


    public static SearchResponse queryAllDocument() throws IOException {
        SearchRequest searchRequest = new SearchRequest("my_index-1");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        client.close();
        return searchResponse;
    }

    public static SearchResponse queryAllDocument(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        client.close();
        return searchResponse;
    }

    /**
     *
     * @param name 搜的字段名称
     * @param keyWord 关键字
     * @return
     * @throws IOException
     */
    public static SearchResponse getDocumentByCondition(String name,String keyWord) throws IOException {
        SearchRequest request=new SearchRequest();
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery(name, keyWord));
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(sourceBuilder);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        return client.search(request,RequestOptions.DEFAULT);
    }

    /**
     *
     * @param name 搜的字段名称
     * @param keyWord 关键字
     * @return
     * @throws IOException
     */
    public static SearchResponse getDocumentByCondition(String indexName,String name,String keyWord) throws IOException {
        SearchRequest request=new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery(name, keyWord));
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(sourceBuilder);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        return client.search(request,RequestOptions.DEFAULT);
    }

    /**
     * 样板的方式查询
     * @param indexName
     * @return
     */
    public static SearchTemplateResponse getDocumentByTemplate(String indexName) throws IOException {
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest(indexName));

        request.setScriptType(ScriptType.INLINE);
        request.setScript(
                "{" +
                        "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                        "  \"size\" : \"{{size}}\"" +
                        "}");

        Map<String, Object> scriptParams = new HashMap<>();
        scriptParams.put("field", "content");
        scriptParams.put("value", "谷歌");
        scriptParams.put("size", 5);
        request.setScriptParams(scriptParams);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    /**
     * 根据文档id进行删除操作
     * @param indexName
     * @param id
     * @return
     */
    public static boolean deleteDocument(String indexName,String id) throws IOException {
        DeleteRequest request = new DeleteRequest(
                indexName,
                id);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
        return true;
    }


    public static UpdateResponse updateDocumentById(String index,String id,Map<String,Object> jsonMap) throws IOException {

        UpdateRequest request = new UpdateRequest(index, id)
                .doc(jsonMap);
        RestHighLevelClient client=EsClientUtils.getRestHighLevelClient();
        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);
        return updateResponse;
    }
}
