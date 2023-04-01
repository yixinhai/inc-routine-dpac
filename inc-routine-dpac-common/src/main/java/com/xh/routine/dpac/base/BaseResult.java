package com.xh.routine.dpac.base;

import com.xh.routine.dpac.enums.ResultCodeEnum;

public class BaseResult<T> {

    private Integer code;
    private String message;
    private T data;


    public BaseResult() {
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResult success() {
        return new BaseResult(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getValue());
    }

    public static BaseResult success(String message) {
        return new BaseResult(ResultCodeEnum.SUCCESS.getCode(), message);
    }

    public BaseResult data(T data) {
        this.data = data;
        return this;
    }

    public static BaseResult defaultFail() {
        return new BaseResult(ResultCodeEnum.DEFAULT_FAIL.getCode(), ResultCodeEnum.DEFAULT_FAIL.getValue());
    }

    public static BaseResult defaultFail(String message) {
        return new BaseResult(ResultCodeEnum.DEFAULT_FAIL.getCode(), message);
    }

    public static BaseResult fail(Integer code, String message) {
        return new BaseResult(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
