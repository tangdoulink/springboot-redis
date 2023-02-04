package com.zpb.redis.listener;


/**
 * @author       pengbo.zhao
 * @description  自定义-redis消息-监听
 * @createDate   2021/12/24 11:13
 * @updateDate   2021/12/24 11:13
 * @version      1.0
 */
public class CustomerRedisMessageListener {

    public void getMessage(String message,String topic){
        System.err.println("consumer listener topic: " + topic);
        System.err.println("consumer listener message: "+ message);
    }

}
