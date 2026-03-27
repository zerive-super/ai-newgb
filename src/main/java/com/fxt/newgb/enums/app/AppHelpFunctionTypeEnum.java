package com.fxt.newgb.enums.app;

public enum AppHelpFunctionTypeEnum {
    VEHICLE_MANAGE("VEHICLE_MANAGE", "车辆管理"),
    BATTERY_CONFIG("BATTERY_CONFIG", "电池配置"),
    ALARM("ALARM", "报警提醒"),
    SHARE("SHARE", "用车人管理"),
    LOCATION("LOCATION", "车辆位置"),
    RIDING("RIDING", "骑行轨迹");

    private final String code;
    private final String desc;

    AppHelpFunctionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AppHelpFunctionTypeEnum getByCode(String code) {
        for (AppHelpFunctionTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
