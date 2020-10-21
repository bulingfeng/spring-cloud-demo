package com.lingeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-20
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello client";
    }
}
