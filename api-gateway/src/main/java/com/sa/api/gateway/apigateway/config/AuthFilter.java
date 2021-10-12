package com.sa.api.gateway.apigateway.config;

import com.sa.api.gateway.apigateway.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
//public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
//
//    private final WebClient.Builder webClientBuilder;
//
//    public AuthFilter(WebClient.Builder webClientBuilder) {
//        super(Config.class);
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        System.out.println("hey  hey :" + config);
//        System.out.println("hey");
//        return (exchange, chain) -> {
//            System.out.println("am in return" + exchange);
//            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                throw new RuntimeException("Missing authorization information");
//            }
//
//            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//
//            String[] parts = authHeader.split(" ");
//
//            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
//                throw new RuntimeException("Incorrect authorization structure");
//            }
//
//            return webClientBuilder.build()
//                    .post()
//                    .uri("http://eshop-account-service/api/auth/validateToken?token=" + parts[1])
//                    .retrieve().bodyToMono(UserDto.class)
//                    .map(userDto -> {
//                        exchange.getRequest()
//                                .mutate()
//                                .header("X-auth-user-id", String.valueOf(userDto.getId()));
//                        return exchange;
//                    }).flatMap(chain::filter);
//        };
//    }
//
//    public static class Config {
//    }
//}

@RefreshScope
@Component
public class AuthFilter implements GatewayFilter {

    @Autowired
    private RouterValidator routerValidator;

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
        }
        //return chain.filter(exchange);

        String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        String[] parts = authHeader.split(" ");

        return webClientBuilder.build().post()
                .uri("http://eshop-account-service/api/auth/validateToken?token=" + parts[1])
                .retrieve().bodyToMono(UserDto.class)
                .map(userDto -> {
                        exchange.getRequest()
                                .mutate()
                                .header("X-auth-user-id", String.valueOf(userDto.getId()));
                        return exchange;
                }).flatMap(chain::filter);
    }


    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
