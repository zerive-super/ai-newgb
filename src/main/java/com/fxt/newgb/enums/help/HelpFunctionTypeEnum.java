package com.fxt.newgb.enums.help;

public enum HelpFunctionTypeEnum {
    VEHICLE_MANAGE("VEHICLE_MANAGE", "车辆管理"),
    TERMINAL_MODEL("TERMINAL_MODEL", "终端型号管理"),
    TERMINAL("TERMINAL", "终端管理"),
    ASSET("ASSET", "资产列表"),
    ALARM("ALARM", "报警明细"),
    BATTERY_SCHEME("BATTERY_SCHEME", "电池方案管理");

    private final String code;
    private final String desc;

    HelpFunctionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static HelpFunctionTypeEnum getByCode(String code) {
        for (HelpFunctionTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
