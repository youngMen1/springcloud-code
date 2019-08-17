package com.seal.gateway.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/17 17:21
 * @description 根据uri去限流
 **/
public class UriKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getURI().getPath());
    }

    @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

}



