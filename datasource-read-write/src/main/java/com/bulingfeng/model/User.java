package com.bulingfeng.model;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
public class User {
    private Integer userId;
    private String userName;
    private Integer userAge;
    private Integer userSex;

    public User() {
    }

    public User(Integer userId, String userName, Integer userAge, Integer userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
}
