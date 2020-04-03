package com.seal.consul.service;

import com.seal.consul.feign.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/3-14:53
 **/
@Service
public class HiService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;


    public String sayHi(String name) {
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
