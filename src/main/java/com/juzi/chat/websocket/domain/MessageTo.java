package com.juzi.chat.websocket.domain;

import lombok.Data;

@Data
public class MessageTo {

    private Long messageId;

    private Long toId;

    public MessageTo(Long messageId,Long toId) {
        this.messageId = messageId;
        this.toId = toId;
    }
}
