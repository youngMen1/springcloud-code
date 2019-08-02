package com.seal.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description 服务消费者（rest+ribbon）
 * ribbon默认的负载均衡策略就是轮询
 **/
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudRestApplication.class, args);
    }

    /**
     * 在工程的启动类中,通过@EnableEurekaClient向服务中心注册；
     * 并且向程序的ioc注入一个bean: restTemplate;
     * 并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
