package com.bulingfeng.download.down;

import com.bulingfeng.download.DownLoadTests;
import com.bulingfeng.download.dao.WpsTestDao;
import com.bulingfeng.download.model.WpsTestPo;
import com.bulingfeng.download.utils.DownFileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-25
 */
public class DownLoadTest extends DownLoadTests {

    @Autowired
    private WpsTestDao wpsTestDao;


    @Test
    public void downFileToLocal() {
        String path="/Users/bulingfeng/Desktop/test-flie/";
        List<WpsTestPo> all=new ArrayList<>();
//        String pdfType="pdf";
        String pptType="ppt";
        String docType="doc";
        String excelType="xls";
        int limit=10;
//        List<WpsTestPo> pdfList = wpsTestDao.selectByType(pdfType, limit);
        List<WpsTestPo> pptList = wpsTestDao.selectByType(pptType, limit);
        List<WpsTestPo> docList = wpsTestDao.selectByType(docType, limit);
        List<WpsTestPo> xlsList = wpsTestDao.selectByType(excelType, limit);

        all.addAll(pptList);
        all.addAll(docList);
        all.addAll(xlsList);
        for (WpsTestPo wpsTestPo : all) {
            try {
                DownFileUtil.downloadFile(wpsTestPo.getUrl(),path+wpsTestPo.getType()+"/"+wpsTestPo.getName());
            } catch (Exception e) {
                continue;
            }
        }

    }
}
