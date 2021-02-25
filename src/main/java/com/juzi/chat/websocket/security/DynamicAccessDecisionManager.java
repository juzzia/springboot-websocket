package com.juzi.chat.websocket.security;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DynamicAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication 当前用户
     * @param object
     * @param configAttributes 所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes.isEmpty()) return;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (ConfigAttribute configAttribute : configAttributes) {
            String needAuth = configAttribute.getAttribute();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needAuth)){
                    return;
                }
            }
        }

        throw new AccessDeniedException("抱歉，您没有访问权限！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
