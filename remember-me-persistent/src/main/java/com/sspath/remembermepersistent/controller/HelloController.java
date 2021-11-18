package com.sspath.remembermepersistent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: HelloController.java
 * @Description: 重启浏览器或者服务端后访问接口，都会建立新会话，token也随之更新
 * @Author: ABCpril
 * @Date: 2021/11/18
 */

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
