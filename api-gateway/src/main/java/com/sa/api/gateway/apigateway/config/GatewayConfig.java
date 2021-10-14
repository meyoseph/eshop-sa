package com.sa.api.gateway.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    AuthFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("shipping-service", r -> r.path("/shipping-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://SHIPPING-SERVICE"))
                .route("eshop-account-service", r -> r.path("/eshop-account-service/**")
                        .filters(f -> f.filter(filter)).uri("lb://ESHOP-ACCOUNT-SERVICE"))
                .route("product-service2", r -> r.path("/product-service2/**")
                        .filters(f -> f.filter(filter)
                ).uri("lb://PRODUCT-SERVICE2")).route("order-service", r -> r.path("/order-service/**")
                        .filters(f -> f.filter(filter)).uri("lb://ORDER_SERVICE"))
                .build();
    }
}
