package com.park.consumer.fallback;

import com.park.consumer.api.ProviderApi;
import com.park.consumer.domain.Account;
import org.springframework.stereotype.Component;

/**
 * @author BarryLee
 */
@Component
public class ProviderFallback implements ProviderApi{

    @Override
    public String send(Account account) {
        return "我熔断了";
    }

}
