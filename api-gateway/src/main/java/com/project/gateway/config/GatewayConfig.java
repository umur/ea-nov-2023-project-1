package com.project.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableHystrix
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/alumni/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("business-service", r -> r.method(HttpMethod.DELETE, HttpMethod.GET)
                        .and()
                        .path("/prof_experiences/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://business-service"))
                .route("business-service", r -> r.path("/open_jobs/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://business-service"))
                .build();
    }

}