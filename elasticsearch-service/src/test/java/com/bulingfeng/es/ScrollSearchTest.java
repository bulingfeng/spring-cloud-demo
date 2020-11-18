package com.bulingfeng.es;

import com.bulingfeng.ElasticsearchTests;
import com.bulingfeng.util.EsDocumentUtils;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-16
 */
public class ScrollSearchTest extends ElasticsearchTests {


    @Test
    public void queryTest() throws IOException {
        List<SearchHit> searchHits = EsDocumentUtils.scorllSearch(15, "index-test-20201106");
        System.out.println(searchHits.size());
        System.out.println(searchHits);
    }
}
