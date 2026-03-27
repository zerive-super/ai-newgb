package com.fxt.newgb.enums.app;

public enum SyncStatusEnum {
    PENDING("0", "待同步"),
    SYNCED("1", "已同步"),
    FAILED("2", "同步失败");

    private final String code;
    private final String desc;

    SyncStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SyncStatusEnum getByCode(String code) {
        for (SyncStatusEnum status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
