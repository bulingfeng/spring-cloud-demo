package com.bulingfeng.util;


import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-02
 */
public class TikaAnalysis {

    /**
     * 判断文件类型 版本-1
     * @param stream
     * @return
     * @throws IOException
     */
    public static String detectDocTypeUsingDetector(InputStream stream)
            throws IOException {
        Detector detector = new DefaultDetector();
        Metadata metadata = new Metadata();

        MediaType mediaType = detector.detect(stream, metadata);
        return mediaType.toString();
    }


    /**
     * 判断文件类型 版本-2
     * @param stream
     * @return
     * @throws IOException
     */
    public static String detectDocTypeUsingFacade(InputStream stream)
            throws IOException {
        Tika tika = new Tika();
        String mediaType = tika.detect(stream);
        return mediaType;
    }


    /**
     * 解析文件流
     * @param stream
     * @return
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    public static String extractContentUsingParser(InputStream stream)
            throws IOException, TikaException, SAXException {
        Parser parser = new AutoDetectParser();
        // 默认读取1000w以上的文件
        ContentHandler handler = new BodyContentHandler(10 * 1024 * 1024);
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        parser.parse(stream, handler, metadata, context);
        return handler.toString();
    }


}
