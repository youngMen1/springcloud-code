package com.seal.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description 配置中心
 **/
@EnableConfigServer
@SpringBootApplication
public class SpringcloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigApplication.class, args);
    }

}
