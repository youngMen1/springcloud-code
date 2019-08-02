package com.seal.zuul.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 09:16
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/zuul")
@Api(value = "controller", tags = "测试管理")
public class TestController {
}
