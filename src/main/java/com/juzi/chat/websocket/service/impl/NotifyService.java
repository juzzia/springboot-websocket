package com.juzi.chat.websocket.service.impl;

import com.juzi.chat.websocket.provider.RedisPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.juzi.chat.websocket.handler.MessageHandler.SESSIONS;

@Service
public class NotifyService {

    @Autowired
    private RedisPusher redisPusher;


    public void notify(String msg,Integer type){
        // 保存通知到数据库

        // 将通知推送给redis
        Map<String,Object> no = new HashMap<>();
        no.put("msg",msg);
        no.put("type",type);
        redisPusher.pushNotify(no);

    }
}
