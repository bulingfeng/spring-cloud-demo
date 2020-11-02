package com.bulingfeng.user;

import com.bulingfeng.DataSourceReadWriteTests;
import com.bulingfeng.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
public class UserTest extends DataSourceReadWriteTests {

    @Autowired
    private UserService userService;


    @Test
    public void testSelect(){
        for (int i = 0; i < 10; i++) {
            userService.getUserInfo();
            userService.read();
        }
    }
}
