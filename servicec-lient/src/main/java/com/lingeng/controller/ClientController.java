package com.lingeng.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-22
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    AtomicInteger ac = new AtomicInteger();
    @GetMapping("/test")
    public String requestClientTest(String name){
        System.out.println(ac.getAndAdd(1));
        return "request service-client-1 is successful";
    }
}
