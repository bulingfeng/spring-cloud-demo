package com.bulingfeng.cache.service;

import com.bulingfeng.cache.model.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/21
 * key必须为实体类中的某个值而且不能有改变,从controller层开始传入参数就必须一致，但是service层无需一致。
 * cacheNames则无所谓。
 */
public interface CacheService {
    @Cacheable(cacheNames="person1")
    Person getPersonFromCatching(Integer id);

    @CachePut(cacheNames = "person1",key = "#person.id")
    Person putPersonToCatching(Person person1);


    @CacheEvict(cacheNames="person1",key = "#id2")
    void deletePersonFromCaching(Integer id2);
}
