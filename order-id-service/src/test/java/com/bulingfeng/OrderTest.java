package com.bulingfeng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
public class OrderTest extends OrderIdApplicationTests{

    @Autowired
    private RedisTemplate redisTemplate;


//    @Autowired
//    private Jedis jedis;
//
//    @Test
//    public void getTime(){
//        Object o=jedis.eval("return redis.call（'time'）[1]");
//        System.out.println();
//    }

    @Test
    public void getTime(){
        String headerKey="userId2:";
        Long increment = redisTemplate.opsForValue().increment(headerKey, 1);
        System.out.println(increment);
        redisTemplate.expire(increment,2, TimeUnit.SECONDS);
    }


}
