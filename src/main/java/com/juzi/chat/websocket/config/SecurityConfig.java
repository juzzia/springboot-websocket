package com.juzi.chat.websocket.config;


import com.juzi.chat.websocket.security.DynamicAccessDecisionManager;
import com.juzi.chat.websocket.security.DynamicFilterInvocationSecurityMetadataSource;
import com.juzi.chat.websocket.security.DynamicFilterSecurityInterceptor;
import com.juzi.chat.websocket.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().defaultSuccessUrl("/chat")
                .and().cors().disable();

//        http.addFilterBefore(dynamicFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @Bean
//    DynamicFilterSecurityInterceptor dynamicFilterSecurityInterceptor(){
//        return new DynamicFilterSecurityInterceptor();
//    }
//
//    @Bean
//    DynamicAccessDecisionManager dynamicAccessDecisionManager(){
//        return new DynamicAccessDecisionManager();
//    }
//
//    @Bean
//    DynamicFilterInvocationSecurityMetadataSource dynamicFilterInvocationSecurityMetadataSource(){
//        return new DynamicFilterInvocationSecurityMetadataSource();
//    }

}

