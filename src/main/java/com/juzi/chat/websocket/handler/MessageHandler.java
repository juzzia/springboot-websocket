package com.juzi.chat.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juzi.chat.websocket.domain.Message;
import com.juzi.chat.websocket.domain.MessageTo;
import com.juzi.chat.websocket.domain.User;
import com.juzi.chat.websocket.domain.UserSessionBo;
import com.juzi.chat.websocket.mapper.MessageMapper;
import com.juzi.chat.websocket.provider.RedisPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Future;

@Component
public class MessageHandler extends TextWebSocketHandler {


    @Autowired
    private MessageMapper messageMapper;



    @Autowired
    private RedisPusher redisPusher;

    // 会话列表
    public static ConcurrentHashMap<Long,UserSessionBo> SESSIONS = new ConcurrentHashMap<>();

    // 连接事件
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 从session中获取用户
        User user = (User) session.getAttributes().get("user");
        UserSessionBo userSessionBo = new UserSessionBo(user,session);
        // 会话列表
        SESSIONS.put(user.getId(),userSessionBo);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        try {
            this.sendMessage(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(TextMessage textMessage) throws IOException {

        Message message = JSONObject.parseObject(textMessage.getPayload(), Message.class);
        // 消息收送方id
        Long receiveId = message.getReceiveId();
        message.setDate(new Date());

        UserSessionBo userSessionBo = SESSIONS.get(receiveId);
        if (userSessionBo != null){
            message.setSendId(userSessionBo.getUser().getId());
            message.setStatus(true);
            messageMapper.insert(message);
            userSessionBo.getSession().sendMessage(textMessage);
        }else {
            message.setStatus(false);
            messageMapper.insert(message);
            Long messageId = message.getId();
            MessageTo messageTo = new MessageTo(messageId,receiveId);
            // 发送消息id给redis， 使redis将消息推送给订阅的客户端
            redisPusher.pushMessage(messageTo);
        }

    }
}
