package com.zpb.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author       pengbo.zhao
 * @description  发布消息-controller
 * @createDate   2021/12/23 17:36
 * @updateDate   2021/12/23 17:36
 * @version      1.0
 */
@Slf4j
@RestController
@RequestMapping("pub")
public class PublisherController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("p1")
    public String publishMsg1(){
        redisTemplate.convertAndSend("phone","223333");
        redisTemplate.convertAndSend("phoneTest2","34555665");
        System.err.println("Publisher send Topic... ");
        return "success";
    }

    @GetMapping("p2")
    public String publishMsg2(){
        redisTemplate.convertAndSend("phone","223333");
        redisTemplate.convertAndSend("phoneTest2","34555665");
        System.err.println("Publisher send Topic... ");
        return "success";
    }

    @GetMapping("p3")
    public String publishMsg3(){
        redisTemplate.convertAndSend("phone","我是p3");
        System.err.println("p3 send message ... ");
        return "success";
    }
}
