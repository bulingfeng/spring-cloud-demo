package com.bulingfeng;

import com.bulingfeng.model.ServiceClient;
import com.bulingfeng.util.LoadBalanceServiceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
public class ServiceClientTest extends LoadBalanceServiceTests{

    @Autowired
    private LoadBalanceServiceUtil loadBalanceServiceUtil;

    @Test
    public void getServiceClientTest() throws InterruptedException {
        for (int i = 1; i <100 ; i++) {
            Runnable runnable=()->{
                loadBalanceServiceUtil.getServiceClient();
//                System.out.println("线程名字为:"+Thread.currentThread().getName()+""+loadBalanceServiceUtil.getServiceClient());
            };
            Thread t=new Thread(runnable);
            t.setName("thread-name-"+i);
            t.start();
        }

        Thread.currentThread().join();

    }
}
