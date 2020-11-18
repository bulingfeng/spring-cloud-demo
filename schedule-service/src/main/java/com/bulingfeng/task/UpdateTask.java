package com.bulingfeng.task;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-18
 */
public class UpdateTask implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("updateTask......");
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
