package com.example.demo.Service.impl;

import com.example.demo.Service.SmsService;
import com.example.demo.config.CloopenConfig;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.cloopen.rest.sdk.BodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private CloopenConfig cloopenConfig;

    @Override
    public boolean sendSms(String phone) {
        // 1. 生成验证码
        int code = ThreadLocalRandom.current().nextInt(1000, 9999);
        System.out.println("发送短信验证码: " + code);

        // 2. 获取到配置信息
        String serverIp = cloopenConfig.getServerIp();
        String serverPort = cloopenConfig.getPort();
        String accountSId = cloopenConfig.getAccountSId();
        String accountToken = cloopenConfig.getAccountToken();
        String appId = cloopenConfig.getAppId();
        String templateId = cloopenConfig.getTemplateId();

        // 3. 创建sdk对象
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);

        String[] datas = {String.valueOf(code), "1"};
        String subAppend = "1234";
        String reqId = UUID.randomUUID().toString();

        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, datas, subAppend, reqId);

        if ("000000".equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息 (map)
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            // 异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息=" + result.get("statusMsg"));
        }
        return false;
    }
}