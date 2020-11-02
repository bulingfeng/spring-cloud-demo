package com.lingfeng.tika;

import com.bulingfeng.util.TikaAnalysis;
import com.lingfeng.TikaServiceTests;
import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-02
 */
public class TikaTest extends TikaServiceTests {


    @Test
    public void whenUsingDetector_thenDocumentTypeIsReturned() throws IOException, TikaException, SAXException {
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("thymeleafspring.pdf");
        String mediaType = TikaAnalysis.detectDocTypeUsingFacade(stream);
        String content=TikaAnalysis.extractContentUsingParser(stream);
        System.out.println("文件类型为:"+mediaType);
        System.out.println("文件的内容为:"+content);
        stream.close();
    }

}
