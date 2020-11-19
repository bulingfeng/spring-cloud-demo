package com.bulingfeng.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-18
 */
@Component
@Order(value = -1)
public class Task1 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        System.exit(1);



        Runnable runnable1=()->{
            while (true){
                excute1();
            }
        };

        Runnable runnable2=()->{
            while (true){
                excute2();
            }
        };



        Runnable runnable3=()->{
            while (true){
                excute3();
            }
        };

        Thread t1=new Thread(runnable1);
        Thread t2=new Thread(runnable2);
        Thread t3=new Thread(runnable3);

        t1.start();
        t2.start();
        t3.start();
    }

    protected void excute1(){
        try {
            System.out.println("excute1......");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void excute2()  {
        try {
            System.out.println("excute2......");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void excute3()  {
        try {
            System.out.println("excute3......");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
