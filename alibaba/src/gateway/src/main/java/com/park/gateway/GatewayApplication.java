package com.park.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author BarryLee
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    //@Bean
    //public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    //    return builder.routes()
    //        // 配制一个路由,把 http://网关地址:网关端口/demo/ 下的请求路由到 demo-service 微服务中
    //        .route(p -> p
    //            .path("/consumer-service/**") //url匹配
    //            .uri("lb://consumer-service"))  // 将请求路由到指定目标, lb开头是注册中心中的服务, http/https 开头
    //        .build();
    //}

}
