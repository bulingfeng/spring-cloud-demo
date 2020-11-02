package com.bulingfeng.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 */
public class EsClientUtils {
    public static RestHighLevelClient getRestHighLevelClient() {
        //初始化ES操作客户端
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "bulingfeng"));

        RestHighLevelClient client =new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200)
                ).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        httpClientBuilder.disableAuthCaching();
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                })
        );

        return client;
    }


    public static void closeRestHighLevelClient(RestHighLevelClient client) throws IOException {
        client.close();
    }
}
