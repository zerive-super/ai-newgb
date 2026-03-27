package com.fxt.newgb.enums.help;

public enum HelpDocErrorCode {
    INVALID_FUNCTION_TYPE(103001001, "功能类型无效"),
    EMPTY_CONTENT_LIST(103001002, "说明内容列表为空");

    private final Integer code;
    private final String message;

    HelpDocErrorCode(Integer code, String message) {
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
