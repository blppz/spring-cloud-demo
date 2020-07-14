package com.park.provider.controller;

import com.park.provider.domain.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BarryLee
 */
@RestController
public class MsgController {

    @Value("${server.port}")
    private String port;

    @PostMapping("/send")
    public String send(@RequestBody Account account) {
        System.out.println("provider " + port + ", name=" + account.getName());
        return "provider-->" + port;
    }

}
