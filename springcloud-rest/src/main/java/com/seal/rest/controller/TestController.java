package com.seal.rest.controller;

import com.seal.rest.ribbon.TestService;
import com.seal.rest.ribbon.impl.TestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 08:14
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/cloud")
@Api(value = "TestController", tags = "测试管理")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/home")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String home(@RequestParam String name) {
        return testService.testService(name);
    }


}
