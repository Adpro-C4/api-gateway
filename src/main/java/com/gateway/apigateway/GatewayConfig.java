package com.gateway.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("P");
        return builder.routes()
                .route("purchase-service", r -> r.path("/purchase/**")
                        .uri("https://purchase-service-specialitystore.up.railway.app"))
                .route("auth-service", r -> r.path("/auth/**")
                        .uri("https://specialitystorebackend.up.railway.app"))
                .route("user-data-service", r -> r.path("/data/customer/154")
                        .uri("https://specialitystorebackend.up.railway.app/data/customer/154"))
                .build();
    }
    // https://specialitystorebackend.up.railway.app/data/customer/4
    // http://localhost:8080/user/data/customer/4
}

