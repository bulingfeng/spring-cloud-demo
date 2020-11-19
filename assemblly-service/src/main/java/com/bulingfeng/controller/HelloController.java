package com.bulingfeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-19
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String test(){
        return "hello world";
    }
}
