package com.seal.comman.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/28 19:01
 * @description 操作有序set
 * Redis的ZSet数据结构
 * Redis 有序集合和无序集合一样也是string类型元素的集合,且不允许重复的成员。
 * 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
 * 有序集合的成员是唯一的,但分数(score)却可以重复。
 * public interface ZSetOperations<K,V>
 * ZSetOperations提供了一系列方法对有序集合进行操作：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZsetTest {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 新增一个有序集合，存在的话为false，不存在的话为true
     * true
     */
    @Test
    public void add() {
        System.out.println(redisTemplate.opsForZSet().add("zset1", "zset-1", 1.0));
    }

    /**
     * 新增一个有序集合
     * 2
     * [zset-5, zset-6]
     */
    @Test
    public void newAdd() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6", 9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>(16);
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset2", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset2", 0, -1));
    }

    /**
     * 从有序集合中移除一个或者多个元素
     * [zset-1, zset-5, zset-6]
     * 1
     * [zset-1, zset-5]
     */
    @Test
    public void remove() {
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        System.out.println(redisTemplate.opsForZSet().remove("zset1", "zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }

    /**
     * 增加元素的score值，并返回增加后的值
     * 2.1
     */
    @Test
    public void incrementScore() {
        // 原为1.1
        System.out.println(redisTemplate.opsForZSet().incrementScore("zset1", "zset-1", 1.1));
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     * [zset-2, zset-1, zset-3, zset-4, zset-5]
     * 0   // 表明排名第一
     */
    @Test
    public void rank() {
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        System.out.println(redisTemplate.opsForZSet().rank("zset1", "zset-2"));
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     * [zset-2, zset-1, zset-3, zset-4, zset-5]
     * 4 //递减之后排到第五位去了
     */
    @Test
    public void reverseRank() {
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        System.out.println(redisTemplate.opsForZSet().reverseRank("zset1", "zset-2"));
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     * [zset-2, zset-1, zset-3, zset-4, zset-5]
     */
    @Test
    public void range() {
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
     * value:zset-2score:1.2
     * value:zset-1score:2.2
     * value:zset-3score:2.3
     * value:zset-4score:6.6
     * value:zset-5score:9.6
     */
    @Test
    public void rangeWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeWithScores("zset1", 0, -1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     * [zset-2, zset-1, zset-3]
     */
    @Test
    public void rangeByScore() {
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));
    }

    /**
     * 通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
     * value:zset-2score:1.2
     * value:zset-1score:2.2
     * value:zset-3score:2.3
     */
    @Test
    public void rangeByScoreWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     * [zset-2, zset-1, zset-3]
     * [zset-1, zset-3]
     */
    @Test
    public void newRangeByScore() {
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5, 1, 2));
    }

    /**
     * 通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     * value:zset-1score:2.2
     * value:zset-3score:2.3
     */
    @Test
    public void newRangeByScoreWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5, 1, 2);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     * [zset-5, zset-4, zset-3, zset-1, zset-2]
     */
    @Test
    public void reverseRange(){
        System.out.println(redisTemplate.opsForZSet().reverseRange("zset1",0,-1));
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
     * value:zset-5score:9.6
     * value:zset-4score:6.6
     * value:zset-3score:2.3
     * value:zset-1score:2.2
     * value:zset-2score:1.2
     */
    @Test
    public  void reverseRangeWithScores(){
        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores("zset1",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
        while (iterator.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
    }
}
