package com.bulingfeng.dao;

import com.bulingfeng.model.User;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
public interface UserMapper {

    User selectOneUser(int userId);

    void insert(User user);
}
