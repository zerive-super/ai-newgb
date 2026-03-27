package com.fxt.newgb.enums.battery;

public enum BatterySchemeErrorCode {
    SCHEME_NAME_EXISTS(102001001, "方案名称已存在"),
    SCHEME_REFERENCED(102001002, "方案已被引用，无法删除"),
    SCHEME_NO_RULES(102001003, "方案无规则数据");

    private final Integer code;
    private final String message;

    BatterySchemeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
