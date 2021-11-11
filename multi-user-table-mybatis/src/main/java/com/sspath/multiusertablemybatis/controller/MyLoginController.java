package com.sspath.multiusertablemybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName: MyLoginController.java
 * @Description: 使用ThymeLeaf模板转mylogin.html
 * @Author: ABCpril
 * @Date: 2021/11/10
 */
@Controller
public class MyLoginController {
    @RequestMapping("/mylogin.html")
    public String mylogin() {
        return "mylogin";
    }
}
