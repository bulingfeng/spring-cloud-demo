package com.lingeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-20
 */
@SpringBootApplication
@EnableEurekaClient
public class ServerClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerClientApplication.class,args);
    }
}
