package com.fxt.newgb.vo.sys;

import com.fxt.newgb.vo.common.OptionVO;

import java.util.List;

public class SysDictVO {

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 数据列表
     */
    private List<OptionVO> options;

    public SysDictVO() {
    }

    public SysDictVO(String dictCode, String dictName, List<OptionVO> options) {
        this.dictCode = dictCode;
        this.dictName = dictName;
        this.options = options;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public List<OptionVO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVO> options) {
        this.options = options;
    }
}
