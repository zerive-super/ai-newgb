package com.fxt.newgb.vo.app;

public class HelpDocSearchVO {

    /**
     * 文档ID
     */
    private Long id;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 匹配内容片段（高亮显示）
     */
    private String matchSnippet;

    /**
     * 功能类型名称
     */
    private String functionTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMatchSnippet() {
        return matchSnippet;
    }

    public void setMatchSnippet(String matchSnippet) {
        this.matchSnippet = matchSnippet;
    }

    public String getFunctionTypeName() {
        return functionTypeName;
    }

    public void setFunctionTypeName(String functionTypeName) {
        this.functionTypeName = functionTypeName;
    }
}
