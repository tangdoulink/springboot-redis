package com.zpb.redis.configure;

import com.zpb.redis.model.ReceiverRedisMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * @author       pengbo.zhao
 * @description  
 * @createDate   2021/12/23 16:59
 * @updateDate   2021/12/23 16:59
 * @version      1.0
 */
@Configuration
public class RedisListener {

    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver receiver
     * @return MessageListener
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver receiver
     * @return MessageListener
     */
    @Bean
    public MessageListenerAdapter listenerAdapterTest2(ReceiverRedisMessage  receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage2");
    }

    /**
     * 注册订阅者
     * @param latch latch
     * @return ReceiveRedisMessage
     */
    @Bean
    public ReceiverRedisMessage receiver(CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }


    /**
     * 计数器，用来控制线程
     * @return 计数器
     */
    @Bean
    public CountDownLatch latch(){
        //指定了计数的次数 1
        return new CountDownLatch(1);
    }


}
