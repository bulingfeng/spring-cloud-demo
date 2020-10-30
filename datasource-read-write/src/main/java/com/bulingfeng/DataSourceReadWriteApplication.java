package com.bulingfeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
@SpringBootApplication
@MapperScan("com.bulingfeng.dao")
public class DataSourceReadWriteApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataSourceReadWriteApplication.class,args);
    }
}
