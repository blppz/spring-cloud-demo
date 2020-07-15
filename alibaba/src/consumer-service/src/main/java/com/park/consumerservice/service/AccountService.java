package com.park.consumerservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.park.api.MsgApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author BarryLee
 */
@Service
public class AccountService {

    @Reference
    private MsgApi api;

    @SentinelResource(value = "reg", fallback = "send")
    public String reg(String name) {
        throw new RuntimeException();
        //return api.send(name);
    }


    public String send() {
        return "熔断了";
    }
}
