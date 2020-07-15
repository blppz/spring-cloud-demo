package com.park.providerservice.msg;

import com.park.api.MsgApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author BarryLee
 */
@Service
public class MsgService implements MsgApi {

    @Value("${server.port}")
    private String port;

    @Override
    public String send(String name) {
        System.out.println(name);
        return "provider --> " + port;
    }

}
