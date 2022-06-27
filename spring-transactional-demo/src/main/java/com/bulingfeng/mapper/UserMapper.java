package com.bulingfeng.mapper;


import com.bulingfeng.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insertOne(@Param("user") User user);

    User selectById(int id);
}
