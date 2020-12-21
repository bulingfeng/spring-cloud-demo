package com.bulingfeng.consul.controller;

import com.bulingfeng.consul.config.MySQLProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/18
 */
@RestController
@RequestMapping("/producer")
public class TestController {

    @Autowired
    private MySQLProperties studentConfig;

    @GetMapping("/config")
    public MySQLProperties testConfig(){
        System.out.printf("实体类:"+studentConfig);
        return studentConfig;
    }
}
