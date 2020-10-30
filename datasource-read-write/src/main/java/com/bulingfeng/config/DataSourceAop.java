package com.bulingfeng.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.bulingfeng.config.Master) " +
            "&& (execution(* com.bulingfeng.service..*.select*(..)) " +
            "|| execution(* com.bulingfeng.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.bulingfeng.config.Master) " +
            "|| execution(* com.bulingfeng.service..*.insert*(..)) " +
            "|| execution(* com.bulingfeng.service..*.add*(..)) " +
            "|| execution(* com.bulingfeng.service..*.update*(..)) " +
            "|| execution(* com.bulingfeng.service..*.edit*(..)) " +
            "|| execution(* com.bulingfeng.service..*.delete*(..)) " +
            "|| execution(* com.bulingfeng.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}