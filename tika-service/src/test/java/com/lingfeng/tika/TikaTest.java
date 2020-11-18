package com.lingfeng.tika;

import com.bulingfeng.util.TikaAnalysis;
import com.lingfeng.TikaServiceTests;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.*;

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


    @Test
    public void readImage() throws TikaException, IOException, SAXException {
        // 解析图片 不成功
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("image/11.jpg");
        String mediaType = TikaAnalysis.detectDocTypeUsingFacade(stream);
        String content=TikaAnalysis.extractContentUsingParser(stream);
        System.out.println("文件类型为:"+mediaType);
        System.out.println(content);
    }

    @Test
    public void readJpg() throws IOException, TikaException, SAXException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("/Users/bulingfeng/Desktop/github-private/spring-cloud-demo/tika-service/src/main/resources/image/11.jpg"));
        ParseContext pcontext = new ParseContext();

        //Jpeg Parse
        JpegParser JpegParser = new JpegParser();
        JpegParser.parse(inputstream, handler, metadata,pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

//        for(String name : metadataNames) {
//            System.out.println(name + ": " + metadata.get(name));
//        }
    }

}
