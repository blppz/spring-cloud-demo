package com.park.consumer.fallback;

import com.park.consumer.api.MsgApi;
import com.park.consumer.domain.Email;
import org.springframework.stereotype.Component;

/**
 * @author BarryLee
 * 1.需要继承 MsgApi
 * 2.必须 spring 容器管理
 */
@Component
public class MsgFallBack implements MsgApi {
    @Override
    public String sendEmail(Email email) {
        System.out.println("发送邮件熔断了");
        return "-1";
    }
}
