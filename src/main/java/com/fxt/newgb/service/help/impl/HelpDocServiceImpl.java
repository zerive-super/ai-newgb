package com.fxt.newgb.service.help.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxt.newgb.common.exception.BusinessException;
import com.fxt.newgb.dto.help.HelpDocSaveDTO;
import com.fxt.newgb.dto.help.HelpDocUpdateDTO;
import com.fxt.newgb.entity.help.HelpDoc;
import com.fxt.newgb.entity.help.HelpDocContent;
import com.fxt.newgb.enums.help.HelpDocErrorCode;
import com.fxt.newgb.enums.help.HelpFunctionTypeEnum;
import com.fxt.newgb.mapper.help.HelpDocContentMapper;
import com.fxt.newgb.mapper.help.HelpDocMapper;
import com.fxt.newgb.service.help.HelpDocService;
import com.fxt.newgb.vo.help.HelpDocContentVO;
import com.fxt.newgb.vo.help.HelpDocDetailVO;
import com.fxt.newgb.vo.help.HelpDocSearchVO;
import com.fxt.newgb.vo.help.HelpDocVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpDocServiceImpl extends ServiceImpl<HelpDocMapper, HelpDoc> implements HelpDocService {

    private final HelpDocContentMapper helpDocContentMapper;

    public HelpDocServiceImpl(HelpDocContentMapper helpDocContentMapper) {
        this.helpDocContentMapper = helpDocContentMapper;
    }

    @Override
    public IPage<HelpDocVO> getPage(Page<HelpDocVO> page, String title, String functionType) {
        // 1. 查询列表数据
        List<HelpDocVO> records = baseMapper.selectPageList(title, functionType);
        
        // 2. 查询总数
        Long total = baseMapper.selectPageCount(title, functionType);
        
        // 3. 组装分页结果
        page.setTotal(total);
        page.setRecords(records);
        return page;
    }

    @Override
    public HelpDocDetailVO getDetail(Long id) {
        // 1. 查询文档是否存在
        HelpDoc doc = baseMapper.selectById(id);
        if (doc == null || doc.getDeleted() == 1) {
            throw new BusinessException(404, "文档不存在");
        }

        // 2. 查询说明内容列表
        List<HelpDocContent> contents = helpDocContentMapper.selectByDocId(id);

        // 3. 组装详情VO
        HelpDocDetailVO detailVO = new HelpDocDetailVO();
        detailVO.setId(doc.getId());
        detailVO.setFunctionType(doc.getFunctionType());
        detailVO.setTitle(doc.getTitle());
        detailVO.setSort(doc.getSort());
        detailVO.setDescription(doc.getDescription());

        List<HelpDocContentVO> contentVOList = contents.stream()
                .map(content -> {
                    HelpDocContentVO contentVO = new HelpDocContentVO();
                    contentVO.setId(content.getId());
                    contentVO.setSort(content.getSort());
                    contentVO.setContent(content.getContent());
                    contentVO.setImageUrl(content.getImageUrl());
                    return contentVO;
                })
                .collect(Collectors.toList());
        detailVO.setContents(contentVOList);

        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(HelpDocSaveDTO dto) {
        // 1. 校验功能类型是否有效
        HelpFunctionTypeEnum functionType = HelpFunctionTypeEnum.getByCode(dto.getFunctionType());
        if (functionType == null) {
            throw new BusinessException(HelpDocErrorCode.INVALID_FUNCTION_TYPE.getCode(), HelpDocErrorCode.INVALID_FUNCTION_TYPE.getMessage());
        }

        // 2. 校验说明内容列表不为空
        if (dto.getContents() == null || dto.getContents().isEmpty()) {
            throw new BusinessException(HelpDocErrorCode.EMPTY_CONTENT_LIST.getCode(), HelpDocErrorCode.EMPTY_CONTENT_LIST.getMessage());
        }

        // 3. 保存文档主表数据
        HelpDoc doc = new HelpDoc();
        BeanUtils.copyProperties(dto, doc);
        doc.setStatus("1");
        baseMapper.insert(doc);

        // 4. 批量保存说明内容数据
        saveContents(doc.getId(), dto.getContents());

        return doc.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, HelpDocUpdateDTO dto) {
        // 1. 校验文档是否存在
        HelpDoc doc = baseMapper.selectById(id);
        if (doc == null || doc.getDeleted() == 1) {
            throw new BusinessException(404, "文档不存在");
        }

        // 2. 校验功能类型是否有效
        HelpFunctionTypeEnum functionType = HelpFunctionTypeEnum.getByCode(dto.getFunctionType());
        if (functionType == null) {
            throw new BusinessException(HelpDocErrorCode.INVALID_FUNCTION_TYPE.getCode(), HelpDocErrorCode.INVALID_FUNCTION_TYPE.getMessage());
        }

        // 3. 校验说明内容列表不为空
        if (dto.getContents() == null || dto.getContents().isEmpty()) {
            throw new BusinessException(HelpDocErrorCode.EMPTY_CONTENT_LIST.getCode(), HelpDocErrorCode.EMPTY_CONTENT_LIST.getMessage());
        }

        // 4. 更新文档主表数据
        BeanUtils.copyProperties(dto, doc);
        baseMapper.updateById(doc);

        // 5. 删除原有说明内容数据
        LambdaQueryWrapper<HelpDocContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HelpDocContent::getDocId, id);
        List<HelpDocContent> oldContents = helpDocContentMapper.selectList(wrapper);
        for (HelpDocContent content : oldContents) {
            content.setDeleted(1);
            helpDocContentMapper.updateById(content);
        }

        // 6. 批量保存新说明内容数据
        saveContents(id, dto.getContents());

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        // 1. 校验文档是否存在
        HelpDoc doc = baseMapper.selectById(id);
        if (doc == null || doc.getDeleted() == 1) {
            throw new BusinessException(404, "文档不存在");
        }

        // 2. 逻辑删除文档主表
        doc.setDeleted(1);
        baseMapper.updateById(doc);

        // 3. 同时逻辑删除关联的说明内容
        LambdaQueryWrapper<HelpDocContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HelpDocContent::getDocId, id);
        List<HelpDocContent> contents = helpDocContentMapper.selectList(wrapper);
        for (HelpDocContent content : contents) {
            content.setDeleted(1);
            helpDocContentMapper.updateById(content);
        }

        return true;
    }

    @Override
    public List<HelpDocSearchVO> search(String keyword) {
        // 1. 对关键字进行分词处理（简化处理，直接使用LIKE查询）
        // 2. 在标题和说明文字字段中进行全文检索
        List<HelpDocVO> searchResults = baseMapper.searchByKeyword(keyword);

        // 3. 返回匹配的文档列表，高亮显示匹配内容片段
        return searchResults.stream()
                .map(result -> {
                    HelpDocSearchVO searchVO = new HelpDocSearchVO();
                    searchVO.setId(result.getId());
                    searchVO.setTitle(result.getTitle());
                    searchVO.setFunctionTypeName(result.getFunctionTypeName());
                    // 简化处理：高亮显示用空字符串，实际项目中可以使用HTML标签包裹
                    searchVO.setMatchSnippet("...");
                    return searchVO;
                })
                .collect(Collectors.toList());
    }

    private void saveContents(Long docId, List<?> contentDTOs) {
        if (contentDTOs == null || contentDTOs.isEmpty()) {
            return;
        }
        
        for (Object dto : contentDTOs) {
            HelpDocContent content = new HelpDocContent();
            content.setDocId(docId);
            if (dto instanceof HelpDocSaveDTO.HelpDocContentDTO) {
                HelpDocSaveDTO.HelpDocContentDTO contentDTO = (HelpDocSaveDTO.HelpDocContentDTO) dto;
                content.setSort(contentDTO.getSort());
                content.setContent(contentDTO.getContent());
                content.setImageUrl(contentDTO.getImageUrl());
            } else if (dto instanceof HelpDocUpdateDTO.HelpDocContentDTO) {
                HelpDocUpdateDTO.HelpDocContentDTO contentDTO = (HelpDocUpdateDTO.HelpDocContentDTO) dto;
                content.setSort(contentDTO.getSort());
                content.setContent(contentDTO.getContent());
                content.setImageUrl(contentDTO.getImageUrl());
            }
            helpDocContentMapper.insert(content);
        }
    }
}
