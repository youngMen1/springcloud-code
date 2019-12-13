package com.seal.comman.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/28 19:00
 * @description 操作set
 * Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
 * Redis 中 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
 * public interface SetOperations<K,V>
 * SetOperations提供了对无序集合的一系列操作：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SetTest {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 无序集合中添加元素，返回添加个数
     * 也可以直接在add里面添加多个值
     */
    @Test
    public void add() {
        String[] strarrays = new String[]{"封志强", "18"};
        System.out.println("结果:" + redisTemplate.opsForSet().add("setTest", strarrays));
    }

    /**
     * 移除集合中一个或多个成员
     */
    @Test
    public void remove() {
        String[] strarrays = new String[]{"strarr1", "sgtarr2"};
        System.out.println("结果:" + redisTemplate.opsForSet().remove("setTest", strarrays));
    }

    /**
     * 移除并返回集合中的一个随机元素
     */
    @Test
    public void pop() {
        System.out.println("结果:" + redisTemplate.opsForSet().pop("setTest"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest"));
    }

    /**
     * 将 member 元素从 source 集合移动到 destination 集合
     */
    @Test
    public void move() {
        redisTemplate.opsForSet().move("setTest", "aaa", "setTest2");
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest2"));
    }

    /**
     * 无序集合的大小长度
     */
    @Test
    public void size() {
        System.out.println("结果:" + redisTemplate.opsForSet().size("setTest"));
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void isMember() {
        System.out.println("结果:" + redisTemplate.opsForSet().isMember("setTest", "ccc"));
        System.out.println("结果:" + redisTemplate.opsForSet().isMember("setTest", "asd"));
    }


    /**
     * key对应的无序集合与otherKey(其他key)对应的无序集合求交集
     */
    @Test
    public void intersect() {
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("结果:" + redisTemplate.opsForSet().intersect("setTest", "setTest2"));
    }

    /**
     * key对应的无序集合与多个otherKey(其他key)对应的无序集合求交集
     */
    @Test
    public void newIntersect() {
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println("结果:" + redisTemplate.opsForSet().intersect("setTest", strlist));
    }

    /**
     * key无序集合与otherkey(其他key)无序集合的交集存储到destKey无序集合中
     */
    @Test
    public void intersectAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("结果:" + redisTemplate.opsForSet().intersectAndStore("setTest", "setTest2", "destKey1"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("destKey1"));
    }

    /**
     * key对应的无序集合与多个otherKey(其他key)对应的无序集合求交集存储到destKey无序集合中
     */
    @Test
    public void newIntersectAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("setTest3:" + redisTemplate.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println("结果:" + redisTemplate.opsForSet().intersectAndStore("setTest", strlist, "destKey2"));
        System.out.println("结果:" + redisTemplate.opsForSet().members("destKey2"));
    }

    /**
     * key无序集合与otherKey(其他key)无序集合的并集
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * 结果：[ccc, aaa, ddd, bbb]
     */
    @Test
    public void union() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("结果:" + redisTemplate.opsForSet().union("setTest", "setTest2"));

    }


    /**
     * key无序集合与多个otherKey(其他key)无序集合的并集
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * setTest3:[xxx, ccc, aaa]
     * 结果:[ddd, xxx, bbb, aaa, ccc]
     */
    @Test
    public void newUnion() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("结果:" + redisTemplate.opsForSet().union("setTest", "setTest2"));
    }

    /**
     * key无序集合与otherkey(其他key)无序集合的并集存储到destKey无序集合中
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * 4
     * unionAndStoreTest1:[ccc, aaa, ddd, bbb]
     */
    @Test
    public void unionAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println(redisTemplate.opsForSet().unionAndStore("setTest", "setTest2", "unionAndStoreTest1"));
        System.out.println("unionAndStoreTest1:" + redisTemplate.opsForSet().members("unionAndStoreTest1"));
    }

    /**
     * key无序集合与多个otherkey(其他key)无序集合的并集存储到destKey无序集合中
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * setTest3:[xxx, ccc, aaa]
     * 5
     * unionAndStoreTest2:[ddd, xxx, bbb, aaa, ccc]
     */
    @Test
    public void newUnionAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("setTest3:" + redisTemplate.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println(redisTemplate.opsForSet().unionAndStore("setTest", strlist, "unionAndStoreTest2"));
        System.out.println("unionAndStoreTest2:" + redisTemplate.opsForSet().members("unionAndStoreTest2"));
    }

    /**
     * key无序集合与otherKey(其他key)无序集合的差集
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * [bbb, ddd]
     */
    @Test
    public void difference() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println(redisTemplate.opsForSet().difference("setTest", "setTest2"));
    }

    /**
     * key无序集合与多个otherKey(其他key)无序集合的差集
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * setTest3:[xxx, ccc, aaa]
     * [bbb, ddd]
     */
    @Test
    public void newDifference() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("setTest3:" + redisTemplate.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println(redisTemplate.opsForSet().difference("setTest", strlist));
    }

    /**
     * key无序集合与otherkey(其他key)无序集合的差集存储到destKey无序集合中
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * 2
     * differenceAndStore1:[bbb, ddd]
     */
    @Test
    public void differenceAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println(redisTemplate.opsForSet().differenceAndStore("setTest", "setTest2", "differenceAndStore1"));
        System.out.println("differenceAndStore1:" + redisTemplate.opsForSet().members("differenceAndStore1"));
    }

    /**
     * key无序集合与多个otherkey(其他key)无序集合的差集存储到destKey无序集合中
     * setTest:[ddd, bbb, aaa, ccc]
     * setTest2:[ccc, aaa]
     * setTest3:[xxx, ccc, aaa]
     * 2
     * differenceAndStore2:[bbb, ddd]
     */
    @Test
    public void newDifferenceAndStore() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTest2:" + redisTemplate.opsForSet().members("setTest2"));
        System.out.println("setTest3:" + redisTemplate.opsForSet().members("setTest3"));
        List<String> strlist = new ArrayList<>();
        strlist.add("setTest2");
        strlist.add("setTest3");
        System.out.println(redisTemplate.opsForSet().differenceAndStore("setTest", strlist, "differenceAndStore2"));
        System.out.println("differenceAndStore2:" + redisTemplate.opsForSet().members("differenceAndStore2"));
    }

    /**
     * 返回集合中的所有成员
     * [ddd, bbb, aaa, ccc]
     */
    @Test
    public void members() {
        System.out.println(redisTemplate.opsForSet().members("setTest"));
    }

    /**
     * 随机获取key无序集合中的一个元素
     * setTest:[ddd, bbb, aaa, ccc]
     * setTestrandomMember:aaa
     * setTestrandomMember:bbb
     */
    @Test
    public void randomMember() {
        System.out.println("setTest:" + redisTemplate.opsForSet().members("setTest"));
        System.out.println("setTestrandomMember:" + redisTemplate.opsForSet().randomMember("setTest"));
        System.out.println("setTestrandomMember:" + redisTemplate.opsForSet().randomMember("setTest"));
        System.out.println("setTestrandomMember:" + redisTemplate.opsForSet().randomMember("setTest"));
        System.out.println("setTestrandomMember:" + redisTemplate.opsForSet().randomMember("setTest"));
    }

    /**
     * 获取多个key无序集合中的元素（去重），count表示个数
     * randomMembers:[aaa, bbb, ddd, ccc]
     */
    @Test
    public void distinctRandomMembers() {
        System.out.println("randomMembers:" + redisTemplate.opsForSet().distinctRandomMembers("setTest", 5));
    }

    /**
     * 获取多个key无序集合中的元素，count表示个数
     * randomMembers:[ccc, ddd, ddd, ddd, aaa]
     */
    @Test
    public void randomMembers() {
        System.out.println("randomMembers:" + redisTemplate.opsForSet().randomMembers("setTest", 5));
    }

    /**
     * 遍历set
     * ddd
     * bbb
     * aaa
     * ccc
     */
    @Test
    public void scan() {
        Cursor<Object> curosr = redisTemplate.opsForSet().scan("setTest", ScanOptions.NONE);
        while (curosr.hasNext()) {
            System.out.println(curosr.next());
        }
    }


}
