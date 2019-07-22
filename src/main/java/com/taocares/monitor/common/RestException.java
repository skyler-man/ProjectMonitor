package com.taocares.monitor.common;

import com.taocares.monitor.common.RestStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author lin
 * @date 2018年11月22日
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RestException extends RuntimeException {

    private RestStatusEnum restStatusEnum;

    public RestException(RestStatusEnum restStatusEnum) {
        this(String.valueOf(restStatusEnum.code()));
        this.restStatusEnum = restStatusEnum;
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    protected RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
