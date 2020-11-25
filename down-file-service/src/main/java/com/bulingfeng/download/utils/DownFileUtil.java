package com.bulingfeng.download.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-25
 */
public class DownFileUtil {
    /**
     * 文件路径
     * @param fileUrl
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean downloadFile(String fileUrl, String path) throws Exception {
        boolean flag = false;
        URL url = new URL(fileUrl);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }
        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        flag = true;
        return flag;
    }
}
