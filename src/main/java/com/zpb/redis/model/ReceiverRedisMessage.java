package com.zpb.redis.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @author       pengbo.zhao
 * @description  
 * @createDate   2021/12/23 17:15
 * @updateDate   2021/12/23 17:15
 * @version      1.0
 */
@Slf4j
public class ReceiverRedisMessage {

    private CountDownLatch latch;

    @Autowired
    public ReceiverRedisMessage(CountDownLatch latch) {
        this.latch = latch;
    }


    /**
     * 队列消息接收方法
     * @param jsonMsg msg
     */
    public void receiveMessage(String jsonMsg) {
        System.err.println("[开始消费REDIS消息队列phone数据...]");
        try {
            System.err.println(jsonMsg);
            System.err.println("[消费REDIS消息队列phone数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }

    /**
     * 队列消息接收方法
     * @param jsonMsg msg
     */
    public void receiveMessage2(String jsonMsg) {
        log.info("[开始消费REDIS消息队列phoneTest2数据...]");
        try {
            System.out.println(jsonMsg);
            /**
             *  此处执行自己代码逻辑 例如 插入 删除操作数据库等
             */

            System.err.println("[消费REDIS消息队列phoneTest2数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phoneTest2数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }
}
