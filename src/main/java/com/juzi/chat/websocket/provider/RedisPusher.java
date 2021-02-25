package com.juzi.chat.websocket.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPusher {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.redis.topic}")
    private String subscribeTopic;

    public void pushMessage(Object message){
        redisTemplate.convertAndSend(subscribeTopic,message);
    }


    public void pushNotify(Object messsage){

        redisTemplate.convertAndSend("notify",messsage);
    }

}
