package com.bulingfeng.util;

import com.bulingfeng.config.ServiceConfig;
import com.bulingfeng.model.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
@Component
public class LoadBalanceServiceUtil {

   private volatile Queue<ServiceClient> queue=null;

   @Autowired
   private ServiceConfig serviceConfig;

    /**
     * 添加锁从而避免多线程同时获取serviceClient发生异常
     */
   public synchronized ServiceClient  getServiceClient(){
       if (queue==null){
           initQueue();
       }
       ServiceClient serviceClient=queue.poll();
       queue.offer(serviceClient);
       System.out.println(serviceClient);
       return serviceClient;
   }


    private  void initQueue(){
        List<String> serviceList=serviceConfig.getList();
        if (serviceList==null || serviceList.size()==0){
            throw new RuntimeException("配置文件没有配置服务器列表");
        }
        if (this.queue==null){
            queue=new LinkedList<ServiceClient>();
            ServiceClient serviceClient=null;
            for (String s : serviceList) {
                serviceClient=new ServiceClient();
                int localtion=s.indexOf(":");
                serviceClient.setHost(s.substring(0,localtion));
                serviceClient.setPort(Integer.parseInt(s.substring(localtion+1)));
                queue.offer(serviceClient);
            }
            System.out.println("初始化的队列为:"+queue);
        }
    }



}
