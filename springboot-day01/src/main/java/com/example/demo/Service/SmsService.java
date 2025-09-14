package com.example.demo.Service;

public interface SmsService {
    /**
     * 发送短信验证码
     * @param to 接收短信的手机号
     * @return 发送结果 true表示成功，false表示失败
     */
    boolean sendSms(String to);
}