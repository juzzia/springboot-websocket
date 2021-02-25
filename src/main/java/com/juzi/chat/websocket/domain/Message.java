package com.juzi.chat.websocket.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_message")
public class Message implements Serializable {

    // 消息id
    @TableId
    private Long id;
    // 发送者Id
    private Long sendId;
    // 接收方id
    private Long receiveId;
    // 消息内容
    private String message;
    // 消息类型  0 文本消息  1 图片消息  2 文件消息
    private Integer type;
    // 消息接收状态
    private Boolean status;
    // 消息发送时间
    private Date date;

}
