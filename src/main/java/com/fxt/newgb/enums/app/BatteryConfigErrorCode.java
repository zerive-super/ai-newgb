package com.fxt.newgb.enums.app;

public enum BatteryConfigErrorCode {
    INVALID_BATTERY_TYPE(104001001, "电池类型无效"),
    INVALID_BATTERY_VOLTAGE(104001002, "电池电压无效"),
    SCHEME_NOT_FOUND(104001003, "电池方案不存在");

    private final Integer code;
    private final String message;

    BatteryConfigErrorCode(Integer code, String message) {
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
