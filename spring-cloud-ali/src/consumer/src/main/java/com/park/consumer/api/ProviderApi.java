package com.park.consumer.api;

import com.park.consumer.domain.Account;
import com.park.consumer.fallback.ProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author BarryLee
 */
@FeignClient(name = "provider-service", fallback = ProviderFallback.class)
public interface ProviderApi {

    @PostMapping("/send")
    String send(Account account);

}
