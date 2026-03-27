package com.fxt.newgb.entity.help;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

@TableName("help_doc")
public class HelpDoc extends BaseEntity {

    /**
     * 功能类型编码
     */
    private String functionType;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 功能简要描述
     */
    private String description;

    /**
     * 状态（1:启用 0:停用）
     */
    private String status;

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
