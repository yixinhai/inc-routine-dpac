package com.xh.routine.dpac.enums;

public enum DpacTicketStatusEnum {
    CREATE("1", "创建"),
    FILE("2", "归档");


    private String code;
    private String value;

    DpacTicketStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
