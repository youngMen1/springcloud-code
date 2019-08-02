package com.seal.rest.ribbon.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.seal.rest.ribbon.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 08:12
 * @description
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 直接用的程序名替代了具体的url地址，
     * 在ribbon中它会根据服务名来选择具体的服务实例，
     * 根据服务实例在请求的时候会用具体的url替换掉服务名
     *
     * @param name
     * @return
     * @HystrixCommand注解。该注解对该方法创建了熔断器的功能， 并指定了fallbackMethod熔断方法，
     * 熔断方法直接返回了一个字符串，字符串为"hi,"+name+",sorry,error!"
     */
    @Override
    @HystrixCommand(fallbackMethod = "testError")
    public String testService(String name) {
        return restTemplate.getForObject("http://SPRINGCLOUD-STRONG/api/strong/test?name=" + name, String.class);
    }


    public String testError(String name) {
        return "hi," + name + ",sorry,error!";
    }


}
