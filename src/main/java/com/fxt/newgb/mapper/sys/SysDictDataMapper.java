package com.fxt.newgb.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.sys.SysDictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 根据字典编码查询数据列表
     *
     * @param dictCode 字典编码
     * @return 数据列表
     */
    @Select("SELECT sd.* FROM sys_dict_data sd " +
            "INNER JOIN sys_dict_type sdt ON sd.dict_type_id = sdt.id " +
            "WHERE sdt.dict_code = #{dictCode} AND sd.status = '1' " +
            "ORDER BY sd.sort ASC")
    List<SysDictData> selectByDictCode(@Param("dictCode") String dictCode);

    /**
     * 根据字典编码和父级ID查询数据列表（用于级联）
     *
     * @param dictCode 字典编码
     * @param parentId 父级ID
     * @return 数据列表
     */
    @Select("SELECT sd.* FROM sys_dict_data sd " +
            "INNER JOIN sys_dict_type sdt ON sd.dict_type_id = sdt.id " +
            "WHERE sdt.dict_code = #{dictCode} AND sd.status = '1' " +
            "<if test='parentId != null'>AND sd.parent_id = #{parentId}</if> " +
            "ORDER BY sd.sort ASC")
    List<SysDictData> selectByDictCodeAndParentId(@Param("dictCode") String dictCode, @Param("parentId") Long parentId);
}
