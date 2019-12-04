package com.seal.comman.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/28 19:00
 * @description 操作字符串
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void setString() {
        redisTemplate.opsForValue().set("name", "tom");
    }

    /**
     * 由于设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null
     */
    @Test
    public void setTimeUnitString() {
        redisTemplate.opsForValue().set("name", "tom", 10, TimeUnit.SECONDS);
    }

    /**
     * 输出结果为:tom
     */
    @Test
    public void getString() {
        System.out.println("结果:" + redisTemplate.opsForValue().get("name"));
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值。
     */
    @Test
    public void setIfAbsentString() {
        // false  multi1之前已经存在
        System.out.println("结果:" + redisTemplate.opsForValue().setIfAbsent("multi1", "multi1"));
        // true  multi111之前不存在
        System.out.println("结果:" + redisTemplate.opsForValue().setIfAbsent("multi111", "multi111"));
    }

    /**
     * 为多个键分别设置它们的值
     */
    @Test
    public void multiSetString() {
        Map<String, String> maps = new HashMap<>(16);
        maps.put("multi1", "multi1");
        maps.put("multi2", "multi2");
        maps.put("multi3", "multi3");
        redisTemplate.opsForValue().multiSet(maps);
        // 还有扩展方法multiSetIfAbsent 如果对应的map集合名称不存在，则添加，如果存在则不做修改。
    }

    /**
     * 为多个键分别取出它们的值
     */
    @Test
    public void multiGetString() {
        List<String> keys = new ArrayList<>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println("结果:" + redisTemplate.opsForValue().multiGet(keys));

    }

    /**
     * 以增量的方式将值存储在变量中。
     * 支持整数也支持浮点数
     */
    @Test
    public void increment() {
        // 整数
        redisTemplate.opsForValue().increment("increInt", 1);
        System.out.println("结果:" + redisTemplate.opsForValue().get("increInt"));

        // double
        redisTemplate.opsForValue().increment("increlong", 1.2);
        System.out.println("结果:" + redisTemplate.opsForValue().get("increlong"));
    }

    /**
     * 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。
     * 如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
     */
    @Test
    public void append() {
        redisTemplate.opsForValue().append("appendTest", "Hello");
        System.out.println("结果:" + redisTemplate.opsForValue().get("appendTest"));

        redisTemplate.opsForValue().append("appendTest", "world");
        System.out.println("结果:" + redisTemplate.opsForValue().get("appendTest"));
    }

    /**
     * 截取key所对应的value字符串
     */
    @Test
    public void getAppend() {
        System.out.println("结果:" + redisTemplate.opsForValue().get("appendTest", 0, 5));
        System.out.println("结果:" + redisTemplate.opsForValue().get("appendTest", 0, -1));
        System.out.println("结果:" + redisTemplate.opsForValue().get("appendTest", -3, -1));
    }

    /**
     * 返回key所对应的value值得长度
     */
    @Test
    public void set() {
        redisTemplate.opsForValue().set("key", "hello world");
        System.out.println("结果:" + redisTemplate.opsForValue().size("key"));
    }


}
