package com.park.consumerservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.park.api.MsgApi;
import com.park.consumerservice.fallback.MsgFallback;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author BarryLee
 */
@Service
public class AccountService {

    @Reference
    private MsgApi api;

    @SentinelResource(value = "reg", fallbackClass = MsgFallback.class, fallback = "send")
    public String reg(String name) {
        return api.send(name);
    }

}
