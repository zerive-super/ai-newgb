package com.fxt.newgb.dto.help;

import com.fxt.newgb.common.dto.PageQuery;

public class HelpDocQueryDTO extends PageQuery {

    /**
     * 功能标题（模糊搜索）
     */
    private String title;

    /**
     * 功能类型（精确匹配）
     */
    private String functionType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }
}
