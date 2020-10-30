package com.bulingfeng.config;

import com.bulingfeng.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE1);
        System.out.println("切换到slave1");
    }

}
