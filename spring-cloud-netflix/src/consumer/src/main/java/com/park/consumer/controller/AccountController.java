package com.park.consumer.controller;

import com.park.consumer.api.MsgApi;
import com.park.consumer.domain.Account;
import com.park.consumer.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author BarryLee
 */
@RefreshScope
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${server.port}")
    private String port;

    /**
     * 从配置文件读取，用来测试配置文件是否更新成功
     */
    @Value("${configText}")
    private String configText;

    @Autowired
    private MsgApi msgApi;

    @PostMapping("/register")
    public Account register(@RequestBody Account account) {
        System.out.println("consumer 收到注册请求：" + account);

        Email email = new Email();
        email.setEmail(account.getEmail());
        email.setContent("打开网址xxx，激活你的账号噢");

        // 使用 OpenFeign 调用
        String res = msgApi.sendEmail(email);
        System.out.println("consumer 收到 provider 返回端口：" + res);

        // 设置id为调用的provider的端口，方便直接查看调用情况
        account.setId(Integer.parseInt(res));
        return account;
    }

    @GetMapping("/zuul")
    public String testZuul() {
        return port;
    }

    @GetMapping("/testConfig")
    public String testConfig() {
        return configText;
    }

}
