package com.alumni.apigateway.filter;

import com.alumni.apigateway.entity.Role;
import com.alumni.apigateway.util.Jwt;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomHeaderGatewayFilterFactory.Config> {

    private Jwt jwtUtil = new Jwt();
    public CustomHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String header = null;

            if (request.getHeaders().get("Authorization") != null && request.getHeaders().get("Authorization").size() > 0) {
                header = request.getHeaders().get("Authorization").get(0);
            }

            if (header == null || !header.startsWith("Bearer ")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            final String token = header.split(" ")[1].trim();

            if (!jwtUtil.validateToken(token)) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String email = jwtUtil.getEmailFromToken(token);
            Role role = Role.valueOf(jwtUtil.getRoleFromToken(token));

            return chain.filter(exchange);
        };
    }

    public static class Config {
        private String headerValue;

        public String getHeaderValue() {
            return headerValue;
        }

        public void setHeaderValue(String headerValue) {
            this.headerValue = headerValue;
        }

    }
}
