package com.bulingfeng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020-12-07
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public void testResponse(String name){
        System.out.println(name+"开始处理逻辑");
    }
}
