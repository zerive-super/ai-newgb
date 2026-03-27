package com.fxt.newgb.service.app.impl;

import com.fxt.newgb.dto.app.HelpDocQueryDTO;
import com.fxt.newgb.dto.app.HelpDocSearchDTO;
import com.fxt.newgb.entity.help.HelpDoc;
import com.fxt.newgb.entity.help.HelpDocContent;
import com.fxt.newgb.mapper.help.HelpDocContentMapper;
import com.fxt.newgb.mapper.help.HelpDocMapper;
import com.fxt.newgb.service.app.AppHelpDocService;
import com.fxt.newgb.vo.app.HelpDocContentVO;
import com.fxt.newgb.vo.app.HelpDocDetailVO;
import com.fxt.newgb.vo.app.HelpDocListVO;
import com.fxt.newgb.vo.app.HelpDocSearchVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppHelpDocServiceImpl implements AppHelpDocService {

    private final HelpDocMapper helpDocMapper;
    private final HelpDocContentMapper helpDocContentMapper;

    public AppHelpDocServiceImpl(HelpDocMapper helpDocMapper,
                                  HelpDocContentMapper helpDocContentMapper) {
        this.helpDocMapper = helpDocMapper;
        this.helpDocContentMapper = helpDocContentMapper;
    }

    @Override
    public List<HelpDocListVO> getList(HelpDocQueryDTO queryDTO) {
        return helpDocMapper.selectListVO(queryDTO.getFunctionType());
    }

    @Override
    public HelpDocDetailVO getDetail(Long id) {
        HelpDoc helpDoc = helpDocMapper.selectById(id);
        if (helpDoc == null || !"1".equals(helpDoc.getStatus())) {
            return null;
        }

        HelpDocDetailVO detailVO = new HelpDocDetailVO();
        BeanUtils.copyProperties(helpDoc, detailVO);

        List<HelpDocContentVO> contents = helpDocContentMapper.selectVOByDocId(id);
        detailVO.setContents(contents);

        return detailVO;
    }

    @Override
    public List<HelpDocSearchVO> search(HelpDocSearchDTO searchDTO) {
        return helpDocMapper.search(searchDTO.getKeyword());
    }
}
