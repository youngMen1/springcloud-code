package com.seal.feign.springcloudfeign.controller;

import com.seal.feign.springcloudfeign.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 17:35
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/cloud")
@Api(value = "controller", tags = "测试管理")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String home(@RequestParam String name) {
        System.out.println(testService.getHello(name));
        return testService.getHello(name);
    }

}
