package com.taocares.monitor.dto;

import com.taocares.monitor.common.RestStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 前端返回类
 *
 * @author lin
 * @since 2019年7月21日
 */
@Data
public class Json<T> implements Serializable {

    @ApiModelProperty("状态码")
    protected int code = 0;

    @ApiModelProperty("是否成功")
    protected boolean success = true;

    @ApiModelProperty("实体对象")
    protected T obj = null;

    @ApiModelProperty("错误信息")
    protected String error = null;

    public Json() {
        super();
    }

    public Json(RestStatusEnum restStatusEnum) {
        this.success = false;
        this.code = restStatusEnum.code();
        this.error = restStatusEnum.value();
    }

    public Json(T obj) {
        this.success = true;
        this.obj = obj;
    }

    public Json(T obj, String error) {
        this.success = false;
        this.obj = obj;
        this.error = error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    public void setError(int code, String error) {
        this.success = false;
        this.code = code;
        this.error = error;
    }

    public void setError(RestStatusEnum restStatusEnum) {
        this.success = false;
        this.code = restStatusEnum.code();
        this.error = restStatusEnum.value();
    }

}
