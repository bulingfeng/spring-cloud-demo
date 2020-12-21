package com.bulingfeng.cache.controller;

import com.bulingfeng.cache.model.Person;
import com.bulingfeng.cache.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/21
 */
@RestController
@RequestMapping("/person")
public class CatchController {

    private CacheService cacheService;

    public CatchController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/get")
    public Person getPerson(Integer id){
        return cacheService.getPersonFromCatching(id);
    }

    @GetMapping("/update")
    public void updatePerson(Person person){
        cacheService.putPersonToCatching(person);
    }

    @GetMapping("/delete")
    public void deletePerson(Integer id){
        cacheService.deletePersonFromCaching(id);
    }
}
