package com.park.consumer.controller;

import com.park.consumer.api.ProviderApi;
import com.park.consumer.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BarryLee
 */
@RestController
public class AccountController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProviderApi api;

    @PostMapping("/reg")
    public String reg(@RequestBody Account account) {
        String name = account.getName();
        System.out.println("consumer " + port + ", name=" + name);
        String res = api.send(account);
        return "consumer-->" + port + ", " + res;
    }

}
