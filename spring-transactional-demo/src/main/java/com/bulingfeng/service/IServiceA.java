package com.bulingfeng.service;

/**
 * 由于事务在同一个类中会导致@Transactional注解失效，
 * 所以使用两个类来进行调用
 */
public interface IServiceA {

    // 有事务注解
    void entranceHaveTransactional();
    //无事务注解
    void entranceRequiredNew();
}
