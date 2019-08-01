package com.seal.strong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description 项目
 **/
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudStrongApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStrongApplication.class, args);
    }

}
