package com.fxt.newgb.vo.app;

public class HelpDocContentVO {

    private Long id;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 说明文字
     */
    private String content;

    /**
     * 说明图片URL
     */
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
