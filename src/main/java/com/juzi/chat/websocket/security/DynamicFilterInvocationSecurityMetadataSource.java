package com.juzi.chat.websocket.security;

import com.sun.deploy.util.URLUtil;
import lombok.Data;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class DynamicFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    String[] strArr = {"/hh"};


    private static Map<String, ConfigAttribute> resourceMap = new HashMap<>();

    static {
        resourceMap.put("/notify/**", new SecurityConfig("notify"));
        resourceMap.put("/admin/**", new SecurityConfig("admin"));
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;

        String path = filterInvocation.getRequest().getRequestURI();

        List<ConfigAttribute> list = new ArrayList<>();

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 从redis中获取权限管理列表
        for (Map.Entry<String, ConfigAttribute> entry : resourceMap.entrySet()) {
            String pattern = entry.getKey();
            if (antPathMatcher.match(pattern, path)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
