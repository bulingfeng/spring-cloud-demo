package com.bulingfeng.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
@Component
public class OrderIdUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 时间戳 16位
     * 随机数 6位
     * 自增id 8位
     */
    public String getOrderId(){
        int numLength=6;
        // 这个需要优化成从数据库读取获取或者从redis中读取
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Random random=new Random();
        String randomNum= random.nextInt(100000)+"";
        StringBuffer sb=new StringBuffer();
        if (randomNum.length()<numLength){
            for (int i = 0; i < numLength-randomNum.length(); i++) {
                sb.append("0");

            }
            sb.append(randomNum);
        }

        System.out.println(sb);
        redisTemplate.opsForValue().increment("user",1);
        return null;
    }



}
