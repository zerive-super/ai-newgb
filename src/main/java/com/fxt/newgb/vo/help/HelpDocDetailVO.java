package com.fxt.newgb.vo.help;

import java.util.List;

public class HelpDocDetailVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 功能类型编码
     */
    private String functionType;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 功能描述
     */
    private String description;

    /**
     * 说明内容列表
     */
    private List<HelpDocContentVO> contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<HelpDocContentVO> getContents() {
        return contents;
    }

    public void setContents(List<HelpDocContentVO> contents) {
        this.contents = contents;
    }
}
