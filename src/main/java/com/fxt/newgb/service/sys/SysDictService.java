package com.fxt.newgb.service.sys;

import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.sys.SysDictVO;

import java.util.List;

public interface SysDictService {

    /**
     * 根据字典编码获取字典数据
     *
     * @param dictCode 字典编码
     * @return 字典数据列表
     */
    List<OptionVO> getDictData(String dictCode);

    /**
     * 根据字典编码和父级ID获取字典数据（用于级联选择）
     *
     * @param dictCode 字典编码
     * @param parentId 父级ID
     * @return 字典数据列表
     */
    List<OptionVO> getDictData(String dictCode, Long parentId);

    /**
     * 获取字典完整信息
     *
     * @param dictCode 字典编码
     * @return 字典完整信息
     */
    SysDictVO getDict(String dictCode);

    /**
     * 批量获取多个字典的数据
     *
     * @param dictCodes 字典编码列表
     * @return 字典信息列表
     */
    List<SysDictVO> getDicts(List<String> dictCodes);
}
