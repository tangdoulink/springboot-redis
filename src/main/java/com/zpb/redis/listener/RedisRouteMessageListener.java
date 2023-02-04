package com.zpb.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author       pengbo.zhao
 * @description  redis-消息-监听
 * @createDate   2021/12/24 10:57
 * @updateDate   2021/12/24 10:57
 * @version      1.0
 */
@Component
public class RedisRouteMessageListener implements MessageListener {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(@NonNull Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        Object topicMessage = redisTemplate.getValueSerializer().deserialize(body);
        String topicName = redisTemplate.getStringSerializer().deserialize(channel);

        System.err.println("topic:" + topicName);
        System.err.println("topic value:" + topicMessage);

    }

}
