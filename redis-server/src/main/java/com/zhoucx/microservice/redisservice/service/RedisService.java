package com.zhoucx.microservice.redisservice.service;

public interface RedisService {
    void deductionStock(String key, String result);
}
