package com.seal.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description 断路器(Hystrix)
 **/
@SpringBootApplication
public class SpringcloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudHystrixApplication.class, args);
    }

}
