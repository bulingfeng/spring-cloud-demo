package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsIndexUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-29
 */
public class IndexTest extends ElasticsearchTests {



    @Test
    public void createIndex(){
        try {
            EsIndexUtils.createIndex("twitter-1");
        } catch (IOException e) {
            System.out.println("创建索引失败");
        }
    }


    @Test
    public void deleteIndex() throws IOException {
        EsIndexUtils.deleteIndex("twitter-1");
    }

    @Test
    public void existIndex() throws IOException {
        boolean flag=EsIndexUtils.existIndex("index");
        System.out.println(flag);
    }

    @Test
    public void createIndex2() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
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
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();

                // 3、文件类型设置mapping
                builder.startObject("file_type");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();

                // 4、文件类型设置mapping
                builder.startObject("file_id");
                {
                    builder.field("type", "integer");
                }
                builder.endObject();


            }
            builder.endObject();
        }
        builder.endObject();

        EsIndexUtils.createIndex("index-test-20201105",builder);
    }


    @Test
    public void createIndex3() throws IOException {
        String indexName="index-test-20201124";
        XContentBuilder builder = XContentFactory.jsonBuilder();

        builder.startObject();
        {
            builder.startObject("_source");
            builder.field("enabled",false);
            builder.endObject();
        }
        builder.endObject();

        builder.startObject();
        {
            builder.startObject("properties");
            {
//                // 1、内容字段设置mapping
//                builder.startObject("content");
//                {
//                    builder.field("type", "text");
//                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
//                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
//                }
//                builder.endObject();


                // 1、内容字段设置mapping
                builder.startObject("content");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();

                // 2、标题设置mapping
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();






            }
            builder.endObject();
        }
        builder.endObject();

        EsIndexUtils.createIndex(indexName,builder);
    }

}
