package com.bulingfeng;

import com.bulingfeng.entity.User;
import com.bulingfeng.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectOneTest(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
