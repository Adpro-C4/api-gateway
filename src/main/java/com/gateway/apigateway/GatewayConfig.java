package com.gateway.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*")
@Configuration
public class GatewayConfig{

    

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("purchase-service-id", r -> r.path("/purchase-service/**")
                        .filters(f -> f.rewritePath("/purchase-service/(?<remaining>.*)", "/$\\{remaining}")) 
                        .uri("https://purchase-service-specialitystore.up.railway.app"))
                .route("auth-service", r -> r.path("/auth-service/**")
                        .filters(f -> f.rewritePath("/auth-service/(?<remaining>.*)", "/$\\{remaining}")) 
                        .uri("https://specialitystorebackend.up.railway.app/auth"))
                .route("user-data-service", r -> r.path("/user-data-service/**") // Menyesuaikan path ke /user-data-service/**
                        .filters(f -> f.rewritePath("/user-data-service/(?<remaining>.*)", "/$\\{remaining}")) // Rewrite path agar sesuai dengan prefix
                        .uri("https://specialitystorebackend.up.railway.app/data"))
                .route("product-data-service", r -> r.path("/product-service/**")
                        .filters(f -> f.rewritePath("/product-service/(?<remaining>.*)", "/$\\{remaining}")) 
                        .uri("https://microservice-katalog-production.up.railway.app"))
                .route("order-status-service", r -> r.path("/order-status-service/**")
                        .filters(f -> f.rewritePath("/order-status-service/(?<remaining>.*)", "/$\\{remaining}")) 
                        .uri("https://microservice-status-production.up.railway.app"))
                .route("voucher-service", r -> r.path("/voucher-service/**")
                        .filters(f -> f.rewritePath("/voucher-service/(?<remaining>.*)", "/${remaining}"))
                        .uri("http://34.143.184.254"))
                .build();
    }
}

