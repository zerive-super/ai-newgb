package com.fxt.newgb.enums;

public enum BatteryTypeEnum {
    LITHIUM("LITHIUM", "锂电池"),
    LEAD_ACID("LEAD_ACID", "铅酸电池");

    private final String code;
    private final String desc;

    BatteryTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static BatteryTypeEnum getByCode(String code) {
        for (BatteryTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
