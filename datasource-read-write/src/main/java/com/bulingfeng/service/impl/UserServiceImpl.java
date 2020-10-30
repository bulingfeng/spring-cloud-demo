package com.bulingfeng.service.impl;

import com.bulingfeng.dao.UserMapper;
import com.bulingfeng.model.User;
import com.bulingfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public User getUserInfo() {
        write();
        read();



        return null;
    }

    @Transactional(transactionManager = "platformTransactionManager")
    @Override
    public void write(){
        User u=new User();
        u.setUserName("bulingfeng");
        u.setUserAge(29);
        u.setUserSex(1);
        userMapper.insertUser(u);
    }


    @Transactional(transactionManager = "platformTransactionManager2")
    @Override
    public void read(){
        for (int i = 0; i <5 ; i++) {
            userMapper.selectOneUser(1);
        }
    }
}
