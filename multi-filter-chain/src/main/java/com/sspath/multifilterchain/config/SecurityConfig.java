package com.sspath.multifilterchain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @FileName: SecurityConfig.java
 * @Description: 配置类里，两个WebSecurityConfigurerAdapter实例配置两条过滤器链
 * @Author: ABCpril
 * @Date: 2021/11/14
 */

@Configuration
public class SecurityConfig {
    // 已经注册进Spring容器
    // 调用AuthenticationConfiguration.getAuthenticationManager()方法自动生成全局AM时
    // 从Spring容器查找UserDetailsService实例，从而被加入全局AuthenticationManager
    @Bean
    UserDetailsService us() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("extraUser").password("{noop}123").roles("admin").build());
        return users;
    }

    @Configuration
    @Order(1)
    static class SecurityConfig01 extends WebSecurityConfigurerAdapter {
        // 没有重写configure(AuthenticationManagerBuilder auth)方法
        // 全局AuthenticationManager由AuthenticationConfiguration.getAuthenticationManager()方法自动生成
        // extraUser作为全局AuthenticationManager的用户，在该过滤器链有效
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("bar").password("{noop}123").roles("admin").build());

            http.antMatcher("/bar/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/bar/login")
                    .loginProcessingUrl("/bar/doLogin")
                    .defaultSuccessUrl("/index.html")
//                    .successHandler((req, resp, auth) -> {
//                        resp.setContentType("application/json;charset=utf-8");
//                        String s = new ObjectMapper().writeValueAsString(auth);
//                        resp.getWriter().write(s);
//                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }

    @Configuration
    @Order(2)
    static class SecurityConfig02 extends WebSecurityConfigurerAdapter {
        // 重写了configure(AuthenticationManagerBuilder auth)方法
        // 相当于配置了局部AuthenticationManager的parent，将不会调用getAuthenticationManager去获取全局AuthenticationManager
        // extraUser在该过滤器链无效
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("builderUser")
                    .password("{noop}123")
                    .roles("admin");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("foo").password("{noop}123").roles("admin").build());

            http.antMatcher("/foo/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/foo/login")
                    .loginProcessingUrl("/foo/doLogin")
                    .defaultSuccessUrl("/index.html")
//                    .successHandler((req, resp, auth) -> {
//                        resp.setContentType("application/json;charset=utf-8");
//                        String s = new ObjectMapper().writeValueAsString(auth);
//                        resp.getWriter().write(s);
//                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }
}
