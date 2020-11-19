package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-18
 */
public class AliasTest extends ElasticsearchTests {


    @Test
    public void aliasToIndex() throws IOException {
        EsDocumentUtils.aliaseWithOneIndex("index-test-20201106-alias","index-test-20201106");
    }
}
