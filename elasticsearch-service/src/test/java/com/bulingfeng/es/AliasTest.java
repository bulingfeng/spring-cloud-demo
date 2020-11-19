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


    @Test
    public void existAlias() throws IOException {
        boolean b1 = EsDocumentUtils.existAliasWithIndex("index-test-20201106", "index-test-20201106-alias");
        System.out.println(b1);
    }

    @Test
    public void existAliasMany() throws IOException {
        String[] indexs=new String[]{"index-test-20201106"};
        String[] alias=new String[]{"index-test-20201106-alias","index-test-20201106-alias-1"};
        boolean b1 = EsDocumentUtils.existAliasWithIndexs(indexs, alias);
        System.out.println(b1);
    }
}
