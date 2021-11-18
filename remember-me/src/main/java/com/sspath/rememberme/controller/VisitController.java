package com.sspath.rememberme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: VisitController.java
 * @Description: 三种认证方式测试接口
 * @Author: ABCpril
 * @Date: 2021/11/18
 */

@RestController
public class VisitController {
    // 认证后才可访问，无论通过何种认证方式
    @GetMapping("/commonauth")
    public String commonauth() {
        return "commonauth";
    }
    // 必须通过用户名/密码的认证方式才可访问
    @GetMapping("/forcelogin")
    public String forcelogin() {
        return "forcelogin";
    }
    // 必须通过RememberMe的认证方式才可访问
    @GetMapping("/rememberme")
    public String rememberme() {
        return "rememberme";
    }
}
