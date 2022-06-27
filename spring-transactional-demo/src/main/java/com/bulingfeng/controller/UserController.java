package com.bulingfeng.controller;

import com.bulingfeng.entity.User;
import com.bulingfeng.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/one")
    public User getUser(){
        return userMapper.selectById(1);
    }
}
