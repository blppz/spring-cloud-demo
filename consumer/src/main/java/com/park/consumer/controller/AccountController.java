package com.park.consumer.controller;

import com.park.consumer.api.MsgApi;
import com.park.consumer.domain.Account;
import com.park.consumer.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BarryLee
 */
@RestController
@RequestMapping("/account")
public class AccountController {

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

}
