package com.xh.routine.dpac.enums;

public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    DEFAULT_FAIL(-1, "fail"),
    USER_NOT_FOUND(10001, "未找到用户登录信息")
    ;

    private Integer code;
    private String value;

    ResultCodeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
