package com.seal.comman.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import com.seal.comman.vo.LotteryNumberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/28 19:00
 * @description 操作list
 * Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）
 * ListOperations专门操作list列表
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTest {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，
     * 其中0是列表的第一个元素（列表的头部），
     * 1是下一个元素
     */
    @Test
    public void range() {
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 修剪现有列表，使其只包含指定的指定范围的元素，起始和停止都是基于0的索引
     */
    @Test
    public void trim() {
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        // 裁剪第一个元素
        redisTemplate.opsForList().trim("list", 1, -1);
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。
     */
    @Test
    public void size() {
        System.out.println("结果:" + redisTemplate.opsForList().size("list"));
    }

    /**
     * 在变量左边添加元素值。
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
     */
    @Test
    public void leftPush() {
        redisTemplate.opsForList().leftPush("list", "java");
        redisTemplate.opsForList().leftPush("list", "python");
        redisTemplate.opsForList().leftPush("list", "c++");
    }

    /**
     * 向左边批量添加参数元素。
     */
    @Test
    public void leftPushAll() {

        LotteryNumberVO lotteryNumberVO = new LotteryNumberVO();
        lotteryNumberVO.setUid("1123");
        lotteryNumberVO.setMemberId("124243");
        lotteryNumberVO.setReceiveTime(new Date());
        lotteryNumberVO.setNumber(10);

        List<LotteryNumberVO> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(seal -> {
            list.add(lotteryNumberVO);
        });
        redisTemplate.opsForList().leftPushAll("listarray", list);
        System.out.println("结果:" + redisTemplate.opsForList().range("listarray", 0, -1));
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 向已存在的集合中添加元素。
     */
    @Test
    public void leftPushIfPresent() {
        System.out.println("结果:" + redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "aa"));
        System.out.println("结果:" + redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb"));

        System.out.println("==========分割线===========");

        System.out.println("结果:" + redisTemplate.opsForList().leftPush("leftPushIfPresent", "aa"));
        System.out.println("结果:" + redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb"));
    }

    /**
     * 把value值放到key对应列表中pivot值的左面，如果pivot值存在的话
     */
    @Test
    public void newLeftPush() {
        redisTemplate.opsForList().leftPush("list", "java", "oc");
        System.out.print("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        // 结果：[c++, python, oc, java, c#, c#]
    }

    /**
     * 移除集合中的左边第一个元素。
     */
    @Test
    public void leftPop() {
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        System.out.println("结果:" + redisTemplate.opsForList().leftPop("list"));
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从右边插入）
     * 向集合最右边添加元素。
     */
    @Test
    public void rightPush() {
        redisTemplate.opsForList().rightPush("listRight", "java");
        redisTemplate.opsForList().rightPush("listRight", "python");
        redisTemplate.opsForList().rightPush("listRight", "c++");
    }

    /**
     * 向右边批量添加元素。
     */
    @Test
    public void rightPushAll() {
        List<Object> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        redisTemplate.opsForList().rightPushAll("listcollectionright", strings);
        System.out.println("结果:" + redisTemplate.opsForList().range("listcollectionright", 0, -1));
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 向已存在的集合中添加元素。
     */
    @Test
    public void rightPushIfPresent() {
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "aa"));
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "bb"));

        System.out.println("==========分割线===========");

        System.out.println(redisTemplate.opsForList().rightPush("rightPushIfPresent", "aa"));
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "bb"));
    }

    /**
     * 把value值放到key对应列表中pivot值的右面，如果pivot值存在的话
     */
    @Test
    public void newRightPush() {
        System.out.println("结果:" + redisTemplate.opsForList().range("listRight", 0, -1));
        redisTemplate.opsForList().rightPush("listRight", "python", "封志强");
        System.out.println("结果:" + redisTemplate.opsForList().range("listRight", 0, -1));
    }

    /**
     * 在列表中index的位置设置value值
     */
    @Test
    public void set() {
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        redisTemplate.opsForList().set("listRight", 1, "setValue");
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * 计数参数以下列方式影响操作：
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     */
    @Test
    public void remove() {
        System.out.println("结果:" + redisTemplate.opsForList().range("listRight", 0, -1));
        // 将删除列表中存储的列表中第一次次出现的“setValue”。
        redisTemplate.opsForList().remove("listRight", 1, "setValue");
        System.out.println("结果:" + redisTemplate.opsForList().range("listRight", 0, -1));
    }


    /**
     * 移除集合中的右边第一个元素。
     */
    @Test
    public void rightPop() {
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        System.out.println("结果:" + redisTemplate.opsForList().rightPop("list"));
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 移除集合中右边的元素，同时在左边加入一个元素。
     */
    @Test
    public void rightPopAndLeftPush() {
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        redisTemplate.opsForList().rightPopAndLeftPush("list", "rightPopAndLeftPush");
        System.out.println("结果:" + redisTemplate.opsForList().range("list", 0, -1));
        System.out.println("结果:" + redisTemplate.opsForList().range("rightPopAndLeftPush", 0, -1));
    }

}
