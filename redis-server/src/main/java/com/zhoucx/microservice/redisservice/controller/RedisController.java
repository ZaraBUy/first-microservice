package com.zhoucx.microservice.redisservice.controller;

import com.zhoucx.microservice.redisservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/redisController")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping(value="/deductionStock")
    public void deductionStock(String key, String result) {
        redisService.deductionStock(key, result);
    }
}
