package com.seal.consul.controller;

import com.seal.consul.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/3-14:50
 **/
@RestController
public class TestController {

    @Autowired
    HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "fengzhiqiang", required = false) String name) {
        return hiService.sayHi(name);
    }

}
