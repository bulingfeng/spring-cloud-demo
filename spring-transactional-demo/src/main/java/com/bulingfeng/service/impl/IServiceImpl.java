package com.bulingfeng.service.impl;

import com.bulingfeng.entity.User;
import com.bulingfeng.mapper.UserMapper;
import com.bulingfeng.service.IServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class IServiceImpl implements IServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void methodA() {

    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void methodB() {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkExceptionMethod() throws IOException {
        User user= User.builder()
                .id(4)
                .passWord("122")
                .userName("hello")
                .realName("realname")
                .build();
        userMapper.insertOne(user);
        try {
            File file=new File("");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("发生异常");
        }
    }
}
