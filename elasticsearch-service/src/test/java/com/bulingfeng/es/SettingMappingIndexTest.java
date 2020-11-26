package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsIndexUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-26
 */
public class SettingMappingIndexTest extends ElasticsearchTests {


    @Test
    public void createIndexBySettingMappingBuiler() throws IOException {
        String indexName="index-test-20201126-1";
        EsIndexUtils.createIndex(indexName,createSettingBuilder(),createMappingBuilder());
    }




    /**
     * 创建mapping
     * @return
     * @throws IOException
     */
    private XContentBuilder createMappingBuilder() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {

            builder.startObject("properties");
            {
                // 1、内容字段设置mapping
                builder.startObject("content");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "es_ik");// 分析的时候使用最大颗粒度
                    builder.field("search_analyzer", "ik_smart"); // 搜寻的时候使用最小颗粒度
                }
                builder.endObject();
                // 2、标题设置mapping
                builder.startObject("user");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();


                // 2、标题设置mapping
                builder.startObject("postDate");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();

            }
            builder.endObject();
        }
        builder.endObject();

        return builder;
    }

    /**
     * 创建setting
     * @return
     * @throws IOException
     */
    private XContentBuilder createSettingBuilder() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {

            builder.startObject("analysis");
            {
                builder.startObject("analyzer");
                {
                    builder.startObject("es_ik");
                    {
                        builder.field("tokenizer", "ik_max_word");
                        builder.field("type", "custom");
                        builder.field("filter", new String[]{"stemmer"});
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
        }

        builder.endObject();
        return builder;
    }
}
