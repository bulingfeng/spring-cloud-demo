package com.bulingfeng;

import com.bulingfeng.entity.User;
import com.bulingfeng.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 1. 只有@Test,不会进行回滚
     * 2. 如果在单元测试上加上@Transactional会回滚
     * 3. 如果不想让单元测试进行回滚，使用@Rollback注解，并且value=false这样就可以让
     * 单元测试提交事务。
     */
    @Rollback(value = false)
    @Transactional
    @Test
    public void testInsertOne(){
        User user= User.builder()
                .id(3)
                .passWord("122")
                .userName("hello")
                .realName("realname")
                .build();
        userMapper.insertOne(user);
    }
}
