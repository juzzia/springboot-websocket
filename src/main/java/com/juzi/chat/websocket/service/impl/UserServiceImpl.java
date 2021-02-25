package com.juzi.chat.websocket.service.impl;

import com.juzi.chat.websocket.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> Users = new ArrayList<>();
        Users.add(new User("lisi", "123456", Arrays.asList(new SimpleGrantedAuthority("notify")), 1L, "李四", "15575481531"));
        Users.add(new User("lisi1", "123456", Arrays.asList(new SimpleGrantedAuthority("admin")), 2L, "李四", "15575481532"));
        Users.add(new User("lisi2", "123456", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), 2L, "李四", "15575481532"));
        Users.add(new User("lisi3", "123456", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), 1L, "李四", "15575481531"));
        Users.add(new User("lisi4", "123456", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), 1L, "李四", "15575481531"));

        for (User user : Users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

}
