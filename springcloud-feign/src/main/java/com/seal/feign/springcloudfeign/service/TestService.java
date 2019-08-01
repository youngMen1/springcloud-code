package com.seal.feign.springcloudfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 17:35
 * @description
 **/
@RequestMapping("/api/cloud")
@FeignClient(value = "springcloud-strong")
public interface TestService {

    /**
     * 调用测试接口
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/hello")
    String getHello(@RequestParam(value = "name") String name);

}
