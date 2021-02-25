package com.juzi.chat.websocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@Data
public class UserSessionBo {

    private User user;

    private WebSocketSession session;
}
