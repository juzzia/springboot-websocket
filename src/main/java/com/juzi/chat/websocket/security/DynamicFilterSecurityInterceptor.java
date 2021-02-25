package com.juzi.chat.websocket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

public class DynamicFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    @Autowired
    private DynamicFilterInvocationSecurityMetadataSource dynamicFilterInvocationSecurityMetadataSource;

    String[] urls = {"/hh"};

    @Autowired
    public void setAccessDecisionManager(AccessDecisionManager dynamicAccessDecisionManager) {
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);

        System.out.println(request.getMethod());
        if (request.getMethod().equalsIgnoreCase("options")){
            fi.getChain().doFilter(servletRequest,servletResponse);
        }

        String path = request.getRequestURI();

        for (String url : urls) {
            System.out.println("白名单路径： " + url);
            if (url.equals(path)) {
                fi.getChain().doFilter(servletRequest,servletResponse);
            }
        }


        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }

        super.afterInvocation(token, null);
    }


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return dynamicFilterInvocationSecurityMetadataSource;
    }
}
