package com.fxt.newgb.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fxt.newgb.entity.sys.SysDictData;
import com.fxt.newgb.entity.sys.SysDictType;
import com.fxt.newgb.mapper.sys.SysDictDataMapper;
import com.fxt.newgb.mapper.sys.SysDictTypeMapper;
import com.fxt.newgb.service.sys.SysDictService;
import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.sys.SysDictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysDictServiceImpl implements SysDictService {

    private final SysDictTypeMapper sysDictTypeMapper;
    private final SysDictDataMapper sysDictDataMapper;

    @Autowired
    public SysDictServiceImpl(SysDictTypeMapper sysDictTypeMapper, SysDictDataMapper sysDictDataMapper) {
        this.sysDictTypeMapper = sysDictTypeMapper;
        this.sysDictDataMapper = sysDictDataMapper;
    }

    @Override
    public List<OptionVO> getDictData(String dictCode) {
        List<SysDictData> dataList = sysDictDataMapper.selectByDictCode(dictCode);
        return dataList.stream()
                .map(data -> new OptionVO(data.getDataValue(), data.getDataLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OptionVO> getDictData(String dictCode, Long parentId) {
        List<SysDictData> dataList = sysDictDataMapper.selectByDictCodeAndParentId(dictCode, parentId);
        return dataList.stream()
                .map(data -> new OptionVO(data.getDataValue(), data.getDataLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public SysDictVO getDict(String dictCode) {
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictType::getDictCode, dictCode);
        SysDictType dictType = sysDictTypeMapper.selectOne(queryWrapper);

        if (dictType == null) {
            return null;
        }

        List<OptionVO> options = getDictData(dictCode);
        return new SysDictVO(dictType.getDictCode(), dictType.getDictName(), options);
    }

    @Override
    public List<SysDictVO> getDicts(List<String> dictCodes) {
        List<SysDictVO> result = new ArrayList<>();
        for (String dictCode : dictCodes) {
            SysDictVO dict = getDict(dictCode);
            if (dict != null) {
                result.add(dict);
            }
        }
        return result;
    }
}
