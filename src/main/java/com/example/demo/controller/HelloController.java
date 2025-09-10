package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        return "Hello";
    }
    @RequestMapping("/list")
    @ResponseBody
    public int[] gitTest() {
        // 返回整数数组 [1, 2, 3]
        return new int[]{1, 2, 3};
    }

    /**
     * 简单的测试方法，返回问候信息
     * @return 字符串
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test1() {
        return "Hello, Test!";
    }
}
