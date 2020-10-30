package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsIndexUtils;
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

}
