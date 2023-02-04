package com.zpb.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author       pengbo.zhao
 * @description  redis-controller
 * @createDate   2021/12/24 11:55
 * @updateDate   2021/12/24 11:55
 * @version      1.0
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("p1")
    public String p1(){
        return redisTemplate.opsForValue().get("k1");
    }

    @GetMapping("p2")
    public String p2(){
        return redisTemplate.opsForValue().get("k2");
    }
}
