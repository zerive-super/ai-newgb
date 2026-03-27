package com.fxt.newgb.vo.app;

public class HelpDocListVO {

    /**
     * 文档ID
     */
    private Long id;

    /**
     * 功能类型编码
     */
    private String functionType;

    /**
     * 功能类型名称
     */
    private String functionTypeName;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 功能描述
     */
    private String description;

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

    public String getFunctionTypeName() {
        return functionTypeName;
    }

    public void setFunctionTypeName(String functionTypeName) {
        this.functionTypeName = functionTypeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
