package com.seal.comman.controller;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSON;
import com.seal.comman.vo.LotteryNumberVO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
        LotteryNumberVO lotteryNumberVOS = LotteryNumberVO.createUserLotteryNumber(10000);
        // 缓存奖品信息
        stringRedisTemplate.opsForValue().set("lottery_number:" + "50cff40d-1929-4d35-9a24-d9b45a18e83f", JSON.toJSONString(lotteryNumberVOS));
        String list = stringRedisTemplate.opsForValue().get("lottery_number:" + "50cff40d-1929-4d35-9a24-d9b45a18e83f");
        System.out.println("结果:" + list);
    }
}
