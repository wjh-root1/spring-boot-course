package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MoodController {

    // 从配置文件中读取应用名称
    @Value("${spring.application.name}")
    private String appName;

    // 读取自定义的心情配置
    @Value("${my.mood.today}")
    private String today;

    @Value("${my.mood.content}")
    private String content;

    @Value("${my.mood.author}")
    private String author;

    @Value("${my.feature.helloSwitch}")
    private Boolean helloSwitch;

    @Value("${my.feature.closeMsg}")
    private String closeMsg;

    /**
     * 接口：根据开关状态返回配置信息
     */
    @RequestMapping("/mood")
    @ResponseBody
    public Map<String, Object> getMoodConfig() {
        Map<String, Object> result = new HashMap<>();

        // 根据开关状态返回不同内容
        if (helloSwitch) {
            // 开关开启时返回正常数据
            result.put("appName", appName);
            result.put("today", today);
            result.put("content", content);
            result.put("author", author);
            result.put("status", "接口正常运行中");
        } else {
            // 开关关闭时返回提示信息
            result.put("status", "接口已关闭");
            result.put("message", closeMsg);
        }

        return result;
    }
}
