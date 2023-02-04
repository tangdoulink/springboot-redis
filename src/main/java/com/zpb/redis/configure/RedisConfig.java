package com.zpb.redis.configure;

import com.zpb.redis.listener.CustomerRedisMessageListener;
import com.zpb.redis.listener.RedisRouteMessageListener;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;

/**
 * @author       pengbo.zhao
 * @description  redis-config
 * @createDate   2021/12/23 21:36
 * @updateDate   2021/12/23 21:36
 * @version      1.0
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 设置redis模板
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    /**
     * 设置线程池
     * @return thread pool
     */
    @Bean
    public ThreadPoolTaskScheduler redisTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //设置线程数
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Bean
    public MessageListenerAdapter customerMessageListenerAdapter(){
        return new MessageListenerAdapter(new CustomerRedisMessageListener(),"getMessage");
    }

    /**
     *  redis 消息监听器
     *  <P>
     *      RedisMessageListenerContainer 为redis 提供异步消息监听
     *  </P>
     * @return RedisMessageListenerContainer
     */
    // @Bean
    // public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,RedisRouteMessageListener redisRouteMessageListener,
    //                                                MessageListenerAdapter listenerAdapter, MessageListenerAdapter listenerAdapterTest2){
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,RedisRouteMessageListener redisRouteMessageListener,
                                                   MessageListenerAdapter customerMessageListenerAdapter){



        // 初始化 redis 消息监听器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        // 设置redis 连接工厂
        container.setConnectionFactory(redisConnectionFactory);

        //添加监听器
        // container.addMessageListener(listenerAdapter,new PatternTopic("phone"));
        // container.addMessageListener(listenerAdapterTest2,new PatternTopic("phoneTest2"));

        container.addMessageListener(customerMessageListenerAdapter,new PatternTopic("phone"));
        container.addMessageListener(redisRouteMessageListener,new PatternTopic("phone"));

        //非常关键不添加线程一直往上累加 不销毁
        container.setTaskExecutor(redisTaskScheduler());

        return container;
    }



}
