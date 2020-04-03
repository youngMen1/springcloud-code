package com.seal.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/3-14:17
 * Consul 服务注册和发现、配置中心
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudConsulProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsulProviderApplication.class, args);
    }

}
