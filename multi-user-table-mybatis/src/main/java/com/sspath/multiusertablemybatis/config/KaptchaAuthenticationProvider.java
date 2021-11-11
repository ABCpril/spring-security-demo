package com.sspath.multiusertablemybatis.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName: KaptchaAuthenticationProvider.java
 * @Description: 重写authenticate方法，在数据库查询用户名密码前，做验证码校验
 *               重写DaoAuthenticationProvider的additionalAuthenticationChecks方法，是在数据库查询后检测验证码，不符合验证码初衷
 * @Author: ABCpril
 * @Date: 2021/11/11
 */
public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String kaptcha = req.getParameter("kaptcha");
        String sessionKaptcha = (String) req.getSession().getAttribute("kaptcha");
        if (kaptcha != null && sessionKaptcha != null && kaptcha.equalsIgnoreCase(sessionKaptcha)) {
            return super.authenticate(authentication);
        }
        throw new AuthenticationServiceException("验证码输入错误");
    }


}
