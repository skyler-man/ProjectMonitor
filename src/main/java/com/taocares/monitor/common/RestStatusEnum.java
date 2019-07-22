package com.taocares.monitor.common;

import com.google.common.collect.ImmutableMap;

/**
 * 自定义异常enum
 *
 * @author lin
 * @date 2019年7月21日
 */
public enum RestStatusEnum {

    USER_NOT_FOUND(5001, "用户不存在，请注册"),

    ID_NOTNULL(501, "ID不存在");

    private Integer code;

    private String value;

    private static final ImmutableMap<Integer, RestStatusEnum> CACHE;

    static {
        final ImmutableMap.Builder<Integer, RestStatusEnum> builder = ImmutableMap.builder();
        for (RestStatusEnum restStatusEnum : values()) {
            builder.put(restStatusEnum.code(), restStatusEnum);
        }
        CACHE = builder.build();
    }

    RestStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer code() {
        return code;
    }

    public String value() {
        return value;
    }

    public static RestStatusEnum valueOfCode(int code) {
        final RestStatusEnum status = CACHE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        }
        return status;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
