package com.fxt.newgb.enums.battery;

public enum VoltageTypeEnum {
    STATIC("STATIC", "静置电压"),
    LOAD("LOAD", "负载电压");

    private final String code;
    private final String desc;

    VoltageTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static VoltageTypeEnum getByCode(String code) {
        for (VoltageTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
