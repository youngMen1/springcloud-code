package com.seal.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description 高可用注册中心
 **/
@EnableEurekaServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaApplication.class, args);
    }

}
