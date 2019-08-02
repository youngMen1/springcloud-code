package com.seal.comman.googleguava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 17:46
 * @description Google Collections一览
 * 显然一篇博文不能深入地覆盖Google Collections的方方面面，所以我决定把时间放在我日常编码中使用到的基础且不失强大的特性上
 * https://www.oschina.net/translate/beautiful-code-with-google-collections-guava-and-static-imports-part-1
 **/
public class CollectionsTestUtils {

    Map<String, Map<Long, List<String>>> map = new HashMap<String, Map<Long, List<String>>>();

    Map<String, Map<Long, List<String>>> getMap = Maps.newHashMap();

    public static void main(String[] args) {
        setList();
    }

    private static void createCollections() {
        // Map<String, Map<Long, List<String>>> map = new HashMap<String, Map<Long, List<String>>>();
        Map<String, Map<Long, List<String>>> setMap = Maps.newHashMap();
        List list = Lists.newArrayList();
        HashSet set = Sets.newHashSet();
    }

    private static void setList() {
        /*List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");*/
        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        System.out.println(of);
    }

}
