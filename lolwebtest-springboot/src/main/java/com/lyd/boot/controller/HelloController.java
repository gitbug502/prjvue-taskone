package com.lyd.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody
//@Controller
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle01() {
        return "Hello,SpringBoot 2!";
    }
}
