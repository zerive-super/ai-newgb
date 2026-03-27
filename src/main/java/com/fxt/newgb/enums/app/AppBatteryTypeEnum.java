package com.fxt.newgb.enums.app;

public enum AppBatteryTypeEnum {
    LITHIUM("LITHIUM", "锂电池"),
    LEAD_ACID("LEAD_ACID", "铅酸电池");

    private final String code;
    private final String desc;

    AppBatteryTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppBatteryTypeEnum getByCode(String code) {
        for (AppBatteryTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
