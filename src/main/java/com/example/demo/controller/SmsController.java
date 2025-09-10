package com.example.demo.controller;

import com.example.demo.Service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/send")
    public String sendSms(@RequestParam String phone) {
        smsService.sendSms(phone);
        return "短信发送成功，验证码：" + ThreadLocalRandom.current().nextInt(1000, 9999);
    }
}