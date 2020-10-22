package com.lingeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-22
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @GetMapping("/test")
    public String requestClientTest(){
        return "request service-client-1 is successful";
    }
}
