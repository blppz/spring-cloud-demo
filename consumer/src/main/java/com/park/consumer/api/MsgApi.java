package com.park.consumer.api;

import com.park.consumer.domain.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 这个类的所有注解都是给 Feign 看的，它会根据这里的注解来去组装一个 http 请求
 * 注解 FeignClient 标注服务名
 * 这个接口也可以额外放到一个服务中，打包，然后通过 maven 引入，这里直接使用继承的方式
 *
 * @author BarryLee
 */
@FeignClient(name = "provider")
public interface MsgApi {
    /**
     * OpenFeign 相比较 Feign，它可以支持 SpringMVC 注解
     * 发送邮件
     */
    @PostMapping("/msg/sendEmail")
    String sendEmail(@RequestBody Email email);
}
