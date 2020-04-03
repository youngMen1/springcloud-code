package com.seal.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/3-14:17
 * Consul服务注册和发现、配置
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudConsulConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsulConsumerApplication.class, args);
    }

}
