package com.fxt.newgb.vo.app;

import java.util.List;

public class HelpDocDetailVO {

    /**
     * 文档ID
     */
    private Long id;

    /**
     * 功能标题
     */
    private String title;

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

    public List<HelpDocContentVO> getContents() {
        return contents;
    }

    public void setContents(List<HelpDocContentVO> contents) {
        this.contents = contents;
    }
}
