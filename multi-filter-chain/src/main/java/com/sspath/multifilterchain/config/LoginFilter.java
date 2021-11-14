package com.sspath.multifilterchain.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @FileName: LoginFilter.java
 * @Description: 登录过滤器，替代原来UsernamePasswordAuthenticationFilter在过滤器链的位置
 * @Author: ABCpril
 * @Date: 2021/11/14
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String kaptcha = request.getParameter("kaptcha");
        String sessionKaptcha = (String) request.getSession().getAttribute("kaptcha");
        if (StringUtils.hasText(kaptcha) && StringUtils.hasText(sessionKaptcha) && kaptcha.equalsIgnoreCase(sessionKaptcha)) {
            return super.attemptAuthentication(request, response);
        }
        throw new AuthenticationServiceException("验证码输入错误");
    }
}
