package com.seal.comman.controller;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSON;
import com.seal.comman.vo.LotteryNumberVO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/12/4 16:11
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @org.junit.Test
    public void test() {
        // 65b31241-ea8b-4ad6-a239-4195b4edd4b9
        // 50cff40d-1929-4d35-9a24-d9b45a18e83f
        String uid = "933db480-a16b-47f1-9e2e-f63a1bac1931";
        LotteryNumberVO lotteryNumberVOS = LotteryNumberVO.createUserLotteryNumber(10000, uid);
        // 缓存奖品信息
        stringRedisTemplate.opsForValue().set("lottery_number:" + uid, JSON.toJSONString(lotteryNumberVOS));
        String list = stringRedisTemplate.opsForValue().get("lottery_number:" + uid);
        System.out.println("结果:" + list);
    }

    @org.junit.Test
    public void Test() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");
        list1.add("7");
        list1.add("8");

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("7");
        list2.add("10");

        //交集
        List<String> collect1 = list1.stream().filter(num -> list2.contains(num))
                .collect(Collectors.toList());
        System.out.println("交集");
        collect1.stream().forEach(System.out::println);

        //差集 list1-list2
        List<String> collect2 = list1.stream().filter(num -> !list2.contains(num))
                .collect(Collectors.toList());
        System.out.println("差集list1-list2");
        collect2.stream().forEach(System.out::println);

        //差集list2-list1
        List<String> collect3 = list2.stream().filter(num -> !list1.contains(num))
                .collect(Collectors.toList());
        System.out.println("差集list2-list1");
        collect3.stream().forEach(System.out::println);

        //并集  不去重
        list1.addAll(null);
        System.out.println("并集  不去重");
        list1.stream().forEach(System.out::println);

        //并集  去重
        List<String> collect4 = list1.stream().distinct().collect(Collectors.toList());
        System.out.println("并集  去重");
        collect4.stream().forEach(System.out::println);
    }
}
