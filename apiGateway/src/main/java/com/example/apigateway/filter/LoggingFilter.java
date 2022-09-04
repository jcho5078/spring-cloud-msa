package com.example.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Configuration
@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    //@Bean
    public RouteLocator setRoutLocateFilter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(t -> t.path("/user-service/**")
                    .filters(f -> f.addRequestHeader("user-service-request", "check-user-service-request")
                            .addResponseHeader("user-service-response", "check-user-service-response"))
                            .uri("http://localhost:0")
            ).build();
    }

    public LoggingFilter(){
        super(Config.class);
    }

    @Data
    public static class Config{
        private String msg;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("doing pre logger..");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("doing Logger Filter : {}", config.getMsg());
            }));
        });
    }
}
