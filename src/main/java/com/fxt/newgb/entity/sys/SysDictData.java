package com.fxt.newgb.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

import java.time.LocalDateTime;

@TableName("sys_dict_data")
public class SysDictData extends BaseEntity {

    /**
     * 关联字典类型ID
     */
    private Long dictTypeId;

    /**
     * 数据标签
     */
    private String dataLabel;

    /**
     * 数据值
     */
    private String dataValue;

    /**
     * 类型编码
     */
    private String dataCode;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 状态（1:启用 0:停用）
     */
    private String status;

    /**
     * 父级ID（用于级联）
     */
    private Long parentId;

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDataLabel() {
        return dataLabel;
    }

    public void setDataLabel(String dataLabel) {
        this.dataLabel = dataLabel;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
