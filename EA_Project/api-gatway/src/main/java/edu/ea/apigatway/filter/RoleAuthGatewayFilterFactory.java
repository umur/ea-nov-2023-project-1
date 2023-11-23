package edu.ea.apigatway.filter;



import edu.ea.apigatway.util.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<RoleAuthGatewayFilterFactory.Config>  {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public RoleAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println( "Config Role" + config.role);
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    System.out.println(authHeader);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);

                   // var request = exchange.getRequest();
                    // JWTUtil can extract the token from the request, parse it and verify if the given role is available
                    if(!jwtUtil.hasRole(authHeader, config.getRole())){
                        // seems we miss the auth token
                        var response = exchange.getResponse();
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return response.setComplete();
                    }
                    System.out.println(exchange.getRequest().getURI());

                    return chain.filter(exchange);


                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("unauthorized access to application");
                }
            }

             return chain.filter(exchange);

        });
    }

    @Override
    public List shortcutFieldOrder() {
        // we need this to use shortcuts in the application.yml
        return Arrays.asList("role");
    }

    @Data
    public static class Config {
        private String role;

    }
}