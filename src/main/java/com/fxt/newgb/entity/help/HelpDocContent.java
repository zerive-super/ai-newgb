package com.fxt.newgb.entity.help;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

@TableName("help_doc_content")
public class HelpDocContent extends BaseEntity {

    /**
     * 关联帮助文档ID
     */
    private Long docId;

    /**
     * 内容排序号
     */
    private Integer sort;

    /**
     * 详细说明文字
     */
    private String content;

    /**
     * 配图URL
     */
    private String imageUrl;

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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
