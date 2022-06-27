package com.bulingfeng.controller;

import com.bulingfeng.service.IServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propagation")
public class PropagationController {

    @Autowired
    private IServiceA iServiceA;

    @GetMapping("/required")
    public void required(){
        iServiceA.entranceHaveTransactional();
    }

    @GetMapping("/requiredNew")
    public void requiredNew(){
        iServiceA.entranceRequiredNew();
    }
}
