package com.bulingfeng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
@Configuration
@ConfigurationProperties(prefix = "test-service")
public class ServiceConfig {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public ServiceConfig(List<String> list) {
        this.list = list;
    }

    public ServiceConfig() {
    }
}
