package com.juzi.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
public class ChatApp {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(ChatApp.class);

        Thread.sleep(10000);
        SpringApplication.exit(run);
    }

    @Bean
    public TaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler();
            threadPoolScheduler.setPoolSize(10);
            threadPoolScheduler.initialize();
        return threadPoolScheduler;
    }


}
