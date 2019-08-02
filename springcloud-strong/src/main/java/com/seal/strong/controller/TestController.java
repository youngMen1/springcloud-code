package com.seal.strong.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 17:01
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/cloud")
@Api(value = "controller", tags = "测试管理")
public class TestController {

    @PostMapping("/hello")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String hello(@RequestParam String name) {
        return "hi " + name + ",i am from port:8080";
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String test(@RequestParam String name) {
        return "hi " + name + ",i am from port:8080";
    }


}
