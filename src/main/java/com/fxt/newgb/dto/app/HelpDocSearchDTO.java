package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;

public class HelpDocSearchDTO {

    /**
     * 搜索关键字
     */
    @NotBlank(message = "搜索关键字不能为空")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
