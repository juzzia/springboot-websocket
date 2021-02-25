package com.juzi.chat.websocket.provider;

import com.alibaba.fastjson.JSONObject;
import com.juzi.chat.websocket.domain.Message;
import com.juzi.chat.websocket.domain.MessageTo;
import com.juzi.chat.websocket.domain.UserSessionBo;
import com.juzi.chat.websocket.handler.MessageHandler;
import com.juzi.chat.websocket.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@Component
public class RedisReceiver {

    @Autowired
    private MessageMapper messageMapper;

    public void receiveMessage(String messageTo){

        try {
            MessageTo message = JSONObject.parseObject(messageTo, MessageTo.class);
            UserSessionBo userSessionBo = MessageHandler.SESSIONS.get(message.getToId());
            if (userSessionBo != null){
                System.out.println(messageTo);
                Message recordMsg = messageMapper.selectById(message.getMessageId());
                recordMsg.setStatus(true);
                messageMapper.updateById(recordMsg);
                userSessionBo.getSession().sendMessage(new TextMessage(JSONObject.toJSONString(recordMsg)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
