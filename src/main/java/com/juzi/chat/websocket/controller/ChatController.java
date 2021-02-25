package com.juzi.chat.websocket.controller;

import com.juzi.chat.websocket.domain.User;
import com.juzi.chat.websocket.service.impl.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {

    @RequestMapping("/chat")
    public String chatView(){
        return "chat";
    }

    @Autowired
    NotifyService notifyService;



    @RequestMapping("/notify")
    @ResponseBody
    public String notifyM(){

        return "您拥有此权限 "+ "notify";
    }


    @RequestMapping("/hh")
    @ResponseBody
    public String hh(){
        return "无需权限";
    }


    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){

        return "您拥有此权限 admin";
    }


}
