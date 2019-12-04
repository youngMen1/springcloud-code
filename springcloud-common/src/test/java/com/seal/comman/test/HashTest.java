package com.seal.comman.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/28 19:00
 * @description 操作hash
 * Redis的散列可以让用户将多个键值对存储到一个Redis键里面。
 * public interface HashOperations<H,HK,HV>
 * HashOperations提供一系列方法操作hash
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashTest {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void put() {

        redisTemplate.opsForHash().put("redisHash", "name", "tom");
        redisTemplate.opsForHash().put("redisHash", "age", 26);
        redisTemplate.opsForHash().put("redisHash", "sex", "6");

        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "jack");
        testMap.put("age", 27);
        testMap.put("class", "1");
        redisTemplate.opsForHash().putAll("redisHash1", testMap);
    }

    /**
     * 确定哈希hashKey是否存在
     */
    @Test
    public void hasKey() {
        System.out.println("结果:" + redisTemplate.opsForHash().hasKey("redisHash", "age"));
        System.out.println("结果:" + redisTemplate.opsForHash().hasKey("redisHash", "ttt"));
    }

    /**
     * 删除给定的哈希hashKeys
     */
    @Test
    public void delete() {
        System.out.println(redisTemplate.opsForHash().delete("redisHash", "name"));
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     */
    @Test
    public void get() {
        System.out.println("结果:" + redisTemplate.opsForHash().get("redisHash", "age"));
    }

    /**
     * 从哈希中获取给定hashKey的值
     */
    @Test
    public void multiGet() {
        List<Object> kes = new ArrayList<>();
        kes.add("name");
        kes.add("age");
        System.out.println("结果:" + redisTemplate.opsForHash().multiGet("redisHash", kes));
    }

    /**
     * 通过给定的delta增加散列hashKey的值（整型）
     */
    @Test
    public void increment() {
        System.out.println("结果:" + redisTemplate.opsForHash().get("redisHash", "age"));
        System.out.println("结果:" + redisTemplate.opsForHash().increment("redisHash", "age", 1));
    }

    /**
     * 获取key所对应的散列表的key
     */
    @Test
    public void keys() {
        // redisHash1所对应的散列表为{class=1, name=jack, age=27}
        System.out.println("结果:" + redisTemplate.opsForHash().keys("redisHash1"));
    }

    /**
     * 获取key所对应的散列表的大小个数
     */
    @Test
    public void size() {
        // redisHash1所对应的散列表为{class=1, name=jack, age=27}
        System.out.println("结果:" + redisTemplate.opsForHash().size("redisHash1"));
    }

    /**
     * 使用m中提供的多个散列字段设置到key对应的散列表中
     */
    @Test
    public void putAll() {
        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "jack");
        testMap.put("age", 27);
        testMap.put("class", "1");
        redisTemplate.opsForHash().putAll("redisHash1", testMap);
        System.out.println("结果:" + redisTemplate.opsForHash().entries("redisHash1"));
    }

    /**
     * 设置散列hashKey的值
     */
    @Test
    public void newPut() {
        redisTemplate.opsForHash().put("redisHash", "name", "tom");
        redisTemplate.opsForHash().put("redisHash", "age", 26);
        redisTemplate.opsForHash().put("redisHash", "class", "6");
        System.out.println("结果:" + redisTemplate.opsForHash().entries("redisHash"));
    }

    /**
     * 仅当hashKey不存在时才设置散列hashKey的值。
     */
    @Test
    public void putIfAbsent() {
        System.out.println("结果:" + redisTemplate.opsForHash().putIfAbsent("redisHash", "age", 30));
        System.out.println("结果:" + redisTemplate.opsForHash().putIfAbsent("redisHash", "kkk", "kkk"));
    }

    /**
     * 获取整个哈希存储的值根据密钥
     */
    @Test
    public void values() {
        System.out.println(redisTemplate.opsForHash().values("redisHash"));
    }

    /**
     * 获取整个哈希存储根据密钥
     */
    @Test
    public void entries() {
        System.out.println("结果:" + redisTemplate.opsForHash().entries("redisHash"));
    }

    /**
     * 使用Cursor在key的hash中迭代，相当于迭代器。
     */
    @Test
    public void scan() {
        Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("redisHash", ScanOptions.NONE);
        while (curosr.hasNext()) {
            Map.Entry<Object, Object> entry = curosr.next();
            System.out.println("结果:" + entry.getKey() + ":" + entry.getValue());
        }
    }


}
