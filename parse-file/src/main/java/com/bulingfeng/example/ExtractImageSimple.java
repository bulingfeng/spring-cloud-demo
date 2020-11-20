//package com.tbd.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDResources;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
//
///**
// * 提取PDF中的图片
// * pdfbox 版本 1.8.13
// * @author Charlie Wu
// * 2018/05/24
// */
//public class ExtractImageSimple {
//    /**
//     * 提取
//     * @param file			PDF文件
//     * @param targetFolder 	图片存放目录
//     * @return
//     */
//    public static boolean extractImages(File file, String targetFolder) {
//        boolean result = true;
//
//        try{
//            PDDocument document = PDDocument.load(file);
//
//            List<PDPage> pages = document.getDocumentCatalog().getAllPages();
//            Iterator<PDPage> iter = pages.iterator();
//            int count = 0;
//            while( iter.hasNext()){
//                PDPage page = (PDPage)iter.next();
//                PDResources resources = page.getResources();
//                Map<String, PDXObjectImage> images = resources.getImages();
//                Map<String, PDFont> fonts = resources.getFonts();
//
//                if (fonts!=null){
//                    Iterator<String> stringIterator = fonts.keySet().iterator();
//                    while (stringIterator.hasNext()){
//                        String key = (String)stringIterator.next();
//                        PDXObjectImage image = (PDXObjectImage)images.get( key );
//                    }
//                }
//
//
//                if(images != null)
//                {
//                    Iterator<String> imageIter = images.keySet().iterator();
//                    while(imageIter.hasNext())
//                    {
//                        count++;
//                        String key = (String)imageIter.next();
//                        PDXObjectImage image = (PDXObjectImage)images.get( key );
//                        String name = file.getName() + "_" + count;	// 图片文件名
////                        image.write2file(targetFolder + name);		// 保存图片
//                    }
//                }
//            }
//        } catch(IOException ex){
//            ex.printStackTrace();
//            return false;
//        }
//
//        return result;
//    }
//
//
//
//    public static void main(String[] args) {
//        File file = new File("/Users/bulingfeng/Desktop/带文字的pdf.pdf");
//        String targerFolder = "/Users/bulingfeng/Desktop/";
//
//        extractImages(file, targerFolder);
//    }
//}