package com.sspath.multifilterchain.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        String sessionKaptcha = (String) request.getSession().getAttribute("kaptcha");

        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE) ||
                request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> userInfo = new HashMap<>();
            try {
                userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = userInfo.get(getUsernameParameter());
                String password = userInfo.get(getPasswordParameter());
                String kaptcha = userInfo.get("kaptcha");
                if (StringUtils.hasText(kaptcha) && StringUtils.hasText(sessionKaptcha) && kaptcha.equalsIgnoreCase(sessionKaptcha)) {
                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                            username, password);
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String kaptcha = request.getParameter("kaptcha");

        if (StringUtils.hasText(kaptcha) && StringUtils.hasText(sessionKaptcha) && kaptcha.equalsIgnoreCase(sessionKaptcha)) {
            return super.attemptAuthentication(request, response);
        }
        throw new AuthenticationServiceException("验证码输入错误");
    }
}
