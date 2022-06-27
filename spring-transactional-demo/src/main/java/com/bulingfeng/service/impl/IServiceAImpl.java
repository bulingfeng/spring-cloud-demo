package com.bulingfeng.service.impl;

import com.bulingfeng.entity.User;
import com.bulingfeng.mapper.UserMapper;
import com.bulingfeng.service.IServer;
import com.bulingfeng.service.IServiceA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class IServiceAImpl implements IServiceA {
    @Autowired
    private IServer iServer;

    @Autowired
    private UserMapper userMapper;


//    @Transactional
    @Override
    public void entranceHaveTransactional() {
        User user= User.builder()
                .id(20)
                .passWord("122")
                .userName("hello")
                .realName("realname")
                .build();
        userMapper.insertOne(user);
        iServer.required();
        throw new RuntimeException("人为制造异常");
    }

//    @Transactional
    @Override
    public void entranceRequiredNew() {
        User user= User.builder()
                .id(21)
                .passWord("122")
                .userName("hello")
                .realName("realname")
                .build();
        userMapper.insertOne(user);
        iServer.requiredNew();
        throw new RuntimeException("人为制造异常");
    }

}
