package com.zpb.redis.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author       pengbo.zhao
 * @description  
 * @createDate   2021/12/24 11:46
 * @updateDate   2021/12/24 11:46
 * @version      1.0
 */
@Service
public class RedisCache {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 更新table2缓存
     */
    @CachePut(value = "test",key = "k1")
    public void redisCacheUpdate(){
        System.err.println("test缓存更新了");
        System.err.println(redisTemplate.opsForValue().get("k1"));
    }
}
