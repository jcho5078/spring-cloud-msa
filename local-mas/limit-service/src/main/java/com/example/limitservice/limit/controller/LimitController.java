package com.example.limitservice.limit.controller;

import com.example.limitservice.limit.bean.ConfigLimits;
import com.example.limitservice.limit.bean.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private ConfigLimits configLimits;

    @GetMapping(value = "/")
    public Limits getLimit(){

        return new Limits(configLimits.getMin(), configLimits.getMax());
    }
}
