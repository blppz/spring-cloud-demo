package com.park.consumerservice.fallback;

/**
 * @author BarryLee
 */
public class MsgFallback {

    public static String send(String name) {
        return "熔断了" + name;
    }

}
