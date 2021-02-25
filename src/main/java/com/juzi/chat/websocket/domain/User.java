package com.juzi.chat.websocket.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class User extends org.springframework.security.core.userdetails.User {

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String nickName, String phone) {
        super(username, password, authorities);
        this.id = id;
        this.nickName = nickName;
        this.phone = phone;
    }

    private Long id;

    private String nickName;


    private String phone;


}
