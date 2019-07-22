package com.taocares.monitor.common;

/**
 * 返回的utils
 *
 * @author lin
 * @date 2018年11月13日
 */
public class RespUtils {

    public static String respStrByBoolean(boolean flag,String message) {
        if (flag) {
            return message+"成功";
        } else {
            return message+"失败";
        }
    }
}
