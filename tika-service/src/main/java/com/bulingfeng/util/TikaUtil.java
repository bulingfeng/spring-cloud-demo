package com.bulingfeng.util;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-02
 */
public class TikaUtil {
    public static String parse(String filePath) throws Exception{
        return parse(filePath,10*1024*1024);
    }

    /**
     * 默认可读取10万以内个字符文档。通过配置可以读取1000w以上的文档。
     * @param filePath
     * @param limit
     * @return
     * @throws Exception
     */
    public static String parse(String filePath,int limit) throws Exception{
        File file=new File(filePath);
        if(!file.exists()){
            System.out.println("目标文件不存在！");
            return null;
        }
        BodyContentHandler handler=null;
        if(limit>10*1024*1024) {
            handler = new BodyContentHandler(limit);
        }else{
            handler = new BodyContentHandler(10 * 1024 * 1024);
        }
        Metadata metadata = new Metadata();
        FileInputStream input=new FileInputStream(file);
        ParseContext context=new ParseContext();
        new AutoDetectParser().parse(input,handler,metadata,context);
        return handler.toString();
    }
}
