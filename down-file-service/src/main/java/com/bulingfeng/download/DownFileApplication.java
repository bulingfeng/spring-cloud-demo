package com.bulingfeng.download;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-25
 */
@SpringBootApplication
@MapperScan(basePackages="com.bulingfeng.download.dao")
public class DownFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownFileApplication.class,args);
    }
}
