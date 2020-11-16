package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-16
 */
public class ScrollSearchTest extends ElasticsearchTests {


    @Test
    public void queryTest() throws IOException {
        EsDocumentUtils.scorllSearch("index-test-20201106");
    }
}
