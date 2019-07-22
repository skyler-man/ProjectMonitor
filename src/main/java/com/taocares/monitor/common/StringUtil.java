package com.taocares.monitor.common;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Collection;

@Slf4j
public class StringUtil {

    /**
     * 判断字符串是否为空，当字符串为null或者为""、" "都认为是空字符串
     *
     * @param str
     * @return
     */
    public static boolean empty(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 如果输入字符串为空（null），返回空字符("")，如果不为空（null）， 则返回字符串本身
     *
     * @param str
     * @return
     */
    public static String valueOrEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String valueOrEmptyObject(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String capitalFirst(String str) {
        if (empty(str)) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 去除头尾字符
     *
     * @param str
     * @return
     */
    public static String removeFirstAndLast(String str) {
        if (empty(str)) {
            return str;
        }
        return str.substring(1, str.length() - 1);
    }

    /**
     * 去除尾字符
     *
     * @param str
     * @return
     */
    public static String removeLast(String str) {
        if (empty(str)) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * 如果字符串结尾为end字串，那么裁剪掉最后一个字符
     *
     * @param str
     * @param end
     * @return
     */
    public static String removeLastIfEndIs(String str, String end) {
        if (empty(str)) {
            return str;
        }
        if (str.endsWith(end)) {
            return removeLast(str);
        }
        return str;
    }

    /**
     * 字符串相等比较（包含null对null）
     *
     * @param str1
     * @param str2
     * @param ignore
     * @return
     */
    public static boolean equal(String str1, String str2, boolean ignore) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 != null) {
            if (ignore) {
                return str1.equalsIgnoreCase(str2);
            } else {
                return str1.equals(str2);
            }
        } else {
            if (ignore) {
                return str2.equalsIgnoreCase(str1);
            } else {
                return str2.equals(str1);
            }
        }
    }

    public static boolean equal(String str1, String str2) {
        return equal(str1, str2, false);
    }


    /**
     * 对象集合转JSON
     *
     * @param objectList
     * @return
     */
    public static String objectListToJson(Collection objectList, boolean isParent) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if (objectList.size() > 0) {
            for (Object o : objectList) {
                String s = objectToJson(o);
                stringBuilder.append(s).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        stringBuilder.append("]");
        if (!isParent) {
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    /**
     * 对象转JSON
     *
     * @param object
     * @return
     */
    private static String objectToJson(Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Class<?> type = field.getType();
                Object value = field.get(object);
                stringBuilder.append("\"")
                        .append(name).append("\":");
                if (value instanceof Collection) {
                    String s = objectListToJson((Collection) value, false);
                    stringBuilder.append(s);
                } else if (value != null && type.getClassLoader() != null) {
                    String s = objectToJsonTemp(value);
                    stringBuilder.append(s);
                } else {
                    stringBuilder.append("\"")
                            .append(StringUtil.valueOrEmptyObject(value))
                            .append("\",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String objectToJsonTemp(Object object) {
        return objectToJson(object);
    }

    /**
     * 过滤掉转义字符，例如空格、换行符等
     *
     * @param str
     * @return
     */
    public static String filterEscapeCharacter(String str) {
        if (str != null) {
            String s = str.replaceAll("[\\r\\n\\t ]+", "");
            return s.replace((char) 12288, ' ').trim();
        }
        return null;
    }

    /**
     * MD5加密
     * @param inStr
     * @author thf
     * @return
     */
    public static String md5(String inStr){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.error("An exception occurs",e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
