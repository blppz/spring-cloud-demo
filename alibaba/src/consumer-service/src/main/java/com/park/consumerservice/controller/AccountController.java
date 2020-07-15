package com.park.consumerservice.controller;

import com.park.consumerservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BarryLee
 */
@RestController
@RequestMapping("/consumer")
public class AccountController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private AccountService service;

    @GetMapping("/reg/{name}")
    public String reg(@PathVariable String name) {
        System.out.println(name);
        return "consumer --> " + port + ", " + service.reg(name);
    }

}
