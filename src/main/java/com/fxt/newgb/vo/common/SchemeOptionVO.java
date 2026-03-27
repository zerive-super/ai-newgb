package com.fxt.newgb.vo.common;

public class SchemeOptionVO {

    /**
     * 方案ID
     */
    private Long id;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 方案描述
     */
    private String description;

    public SchemeOptionVO() {
    }

    public SchemeOptionVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SchemeOptionVO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
