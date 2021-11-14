package com.sspath.multifilterchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName: MyLoginController.java
 * @Description: 使用ThymeLeaf模板
 * @Author: ABCpril
 * @Date: 2021/11/14
 */
@Controller
public class MyLoginController {
    @RequestMapping("/bar/login")
    public String barlogin() {
        return "barlogin";
    }

    @RequestMapping("/foo/login")
    public String foologin() {
        return "foologin";
    }

    @RequestMapping("/kaptcha/login")
    public String kaptchalogin() {
        return "kaptchalogin";
    }
}
