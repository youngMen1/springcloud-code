package com.seal.hystrixdashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 14:13
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/hystrix")
@Api(value = "controller", tags = "测试管理")
public class TestController {
    @PostMapping("/hello")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String hello(@RequestParam String name) {
        return "hi " + name + ",i am from port:8080";
    }

}
