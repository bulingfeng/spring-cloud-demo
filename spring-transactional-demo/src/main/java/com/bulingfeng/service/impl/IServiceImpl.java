package com.bulingfeng.service.impl;

import com.bulingfeng.mapper.UserMapper;
import com.bulingfeng.service.IServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
