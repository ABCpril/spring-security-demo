package com.sspath.rememberme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @FileName: SecurityConfig.java
 * @Description: 对3个测试接口配置不同的认证方式
 * @Author: ABCpril
 * @Date: 2021/11/18
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jack")
                .password("123")
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forcelogin").fullyAuthenticated()
                .antMatchers("/rememberme").rememberMe()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .rememberMe()
                // 普通的Remember Me（非持久化令牌）需要配置key
                // 否则系统每次重启都自动生成一个uuid作为key，导致之前下发的remember-me都失效
                .key("remember")
                .and()
                .csrf().disable();
    }
}
