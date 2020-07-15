package com.park.gateway.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class GatewayPredicateDefinition {
    //断言对应的Name
    private String name;
    //配置的断言规则
    private Map<String, String> args = new LinkedHashMap<>();
    //此处省略Get和Set方法
}