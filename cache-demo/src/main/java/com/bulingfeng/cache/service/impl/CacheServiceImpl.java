package com.bulingfeng.cache.service.impl;

import com.bulingfeng.cache.model.Person;
import com.bulingfeng.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/21
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Override
    public Person getPersonFromCatching(Integer id) {
        log.info("开始调用数据库中数据......");
        Person person=new Person(id,"name"+id,id);
        return person;
    }


    @Override
    public Person putPersonToCatching(Person person) {
        log.info("更新数据库中的内容......");
        return person;
    }

    @Override
    public void deletePersonFromCaching(Integer id2) {
        log.info("删除id为{}的数据......",id2);
    }
}
