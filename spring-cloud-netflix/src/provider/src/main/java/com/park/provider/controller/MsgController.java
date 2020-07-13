package com.park.provider.controller;

import com.park.provider.domain.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BarryLee
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Value("${server.port}")
    private String port;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Email email) {
        System.out.println("provider port：" + port);
        System.out.println("provider 收到邮件：" + email);
        email.setContent("偷偷改一下内容");
        return port;
    }

}
