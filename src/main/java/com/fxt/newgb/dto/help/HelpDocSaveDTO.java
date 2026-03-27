package com.fxt.newgb.dto.help;

import jakarta.validation.constraints.*;
import java.util.List;

public class HelpDocSaveDTO {

    /**
     * 功能类型编码
     */
    @NotBlank(message = "功能类型不能为空")
    private String functionType;

    /**
     * 文档标题
     */
    @NotBlank(message = "功能标题不能为空")
    @Size(max = 100, message = "功能标题最大100字符")
    private String title;

    /**
     * 排序号
     */
    @NotNull(message = "排序号不能为空")
    @Positive(message = "排序号必须为正整数")
    private Integer sort;

    /**
     * 功能简要描述
     */
    @NotBlank(message = "功能描述不能为空")
    @Size(max = 500, message = "功能描述最大500字符")
    private String description;

    /**
     * 说明内容列表
     */
    @NotEmpty(message = "说明内容列表不能为空")
    private List<HelpDocContentDTO> contents;

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

    public List<HelpDocContentDTO> getContents() {
        return contents;
    }

    public void setContents(List<HelpDocContentDTO> contents) {
        this.contents = contents;
    }

    public static class HelpDocContentDTO {
        /**
         * 内容排序号
         */
        @NotNull(message = "排序号不能为空")
        private Integer sort;

        /**
         * 详细说明文字
         */
        @NotBlank(message = "说明文字不能为空")
        @Size(max = 2000, message = "说明文字最大2000字符")
        private String content;

        /**
         * 配图URL
         */
        @NotBlank(message = "说明图片不能为空")
        private String imageUrl;

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
}
