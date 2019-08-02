package com.seal.feign.springcloudfeign.service;

import com.seal.feign.springcloudfeign.service.impl.TestServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 17:35
 * @description
 **/
@FeignClient(value = "springcloud-strong", fallback = TestServiceImpl.class)
public interface TestService {

    /**
     * 调用测试接口
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/api/strong/hello")
    String getHello(@RequestParam(value = "name") String name);

}
