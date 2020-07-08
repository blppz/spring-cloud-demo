package com.park.consumer.api;

import com.park.consumer.domain.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author BarryLee
 */
@FeignClient(name = "provider")
public interface MsgApi {
    /**
     * 发送邮件
     */
    @PostMapping("/msg/sendEmail")
    Email sendEmail(@RequestBody Email email);
}
