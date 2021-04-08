package com.zhoucx.microservice.redisservice.service.impl;

import com.zhoucx.microservice.redisservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void deductionStock(String key, String result) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        for (int i = 0; i < 10000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Long increment = stringRedisTemplate.opsForValue().increment(key, -1);
                    if (increment >= 0) {
                        //扣减成功执行后续真实扣减库存逻辑
                        System.out.println(Thread.currentThread().getName() + "  扣减成功，执行真实扣减库存");
                        stringRedisTemplate.opsForValue().increment(result,1);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "  库存不足！！");
                        stringRedisTemplate.opsForValue().increment("fail",1);
                    }
                }
            });
        }
    }
}
