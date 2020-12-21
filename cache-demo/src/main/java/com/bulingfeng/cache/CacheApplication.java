package com.bulingfeng.cache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/21
 */
@EnableCaching
@SpringBootApplication
public class CacheApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CacheApplication.class)
                .run(args);
    }
}
