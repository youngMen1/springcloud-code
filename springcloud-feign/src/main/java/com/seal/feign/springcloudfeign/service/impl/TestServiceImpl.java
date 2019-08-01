package com.seal.feign.springcloudfeign.service.impl;

import com.seal.feign.springcloudfeign.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 18:11
 * @description TestServiceImpl需要实现TestService接口，并注入到Ioc容器中
 **/

@Component
public class TestServiceImpl implements TestService {
    @Override
    public String getHello(String name) {
        // 记录日志
        return "sorry " + name;
    }
}
