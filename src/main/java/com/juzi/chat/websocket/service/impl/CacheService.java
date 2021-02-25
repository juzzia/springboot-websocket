package com.juzi.chat.websocket.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private RedisTemplate redisTemplate;

    private static final String CACHE_USERNAME_KEY = "im:onlineUser";

    public void setUsername(String username,String id){
        redisTemplate.opsForSet().add(CACHE_USERNAME_KEY,username+":"+id);
    }


    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        Object sessionConfig = beanFactory.getBean("sessionConfig");
        System.out.println("sessionConfig: "+ sessionConfig);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
    }


}


