package com.taocares.monitor.common;

import java.util.List;

/**
 * List工具类
 *
 * @Author drg
 * @date 2018/6/7 14:35
 */
public class ListUtils {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
