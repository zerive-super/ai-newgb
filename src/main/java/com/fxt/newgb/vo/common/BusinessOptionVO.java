package com.fxt.newgb.vo.common;

public class BusinessOptionVO {

    /**
     * 方案ID
     */
    private Long id;

    /**
     * 方案编码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 状态
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
