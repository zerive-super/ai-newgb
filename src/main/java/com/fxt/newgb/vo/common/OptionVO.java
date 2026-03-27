package com.fxt.newgb.vo.common;

public class OptionVO {

    /**
     * 选项值
     */
    private String value;

    /**
     * 显示文本
     */
    private String label;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    public OptionVO() {
    }

    public OptionVO(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public OptionVO(String value, String label, Boolean disabled) {
        this.value = value;
        this.label = label;
        this.disabled = disabled;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
