package com.energy.gateway;

import com.energy.gateway.logic.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/test/**")
                        .filters(f -> f.addRequestHeader("something", "something"))
                        .uri("lb://FIRST-SERVICE"))
            .route(r -> r.path("/auth/**")
                .filters(f -> f.filter(filter))
                .uri("lb://AUTHENTICATIONAPI"))
            .route(r -> r.path("/program/**")
                .filters(f -> f.filter(filter))
                .uri("lb://PROGRAMAPI"))
            .route(r -> r.path("/user/**")
                .filters(f -> f.filter(filter))
                .uri("lb://USERAPI"))
            .route(r -> r.path("/template/**")
                .filters(f -> f.filter(filter))
                .uri("lb://TEMPLATEAPI"))
            .route(r -> r.path("/scanner/**")
                .filters(f -> f.filter(filter))
                .uri("lb://VIRUSSCANNERAPI"))
            .build();
    }
    //TODO thinking about possible circuitBreaker implementation against poorly behaving services: resilience4j dependency
}
