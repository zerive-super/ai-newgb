package com.fxt.newgb.service.app;

import com.fxt.newgb.dto.app.HelpDocQueryDTO;
import com.fxt.newgb.dto.app.HelpDocSearchDTO;
import com.fxt.newgb.vo.app.HelpDocDetailVO;
import com.fxt.newgb.vo.app.HelpDocListVO;
import com.fxt.newgb.vo.app.HelpDocSearchVO;

import java.util.List;

public interface AppHelpDocService {

    /**
     * 获取帮助文档列表
     *
     * @param queryDTO 查询参数
     * @return 文档列表
     */
    List<HelpDocListVO> getList(HelpDocQueryDTO queryDTO);

    /**
     * 获取帮助文档详情
     *
     * @param id 文档ID
     * @return 文档详情
     */
    HelpDocDetailVO getDetail(Long id);

    /**
     * 搜索帮助文档
     *
     * @param searchDTO 搜索参数
     * @return 搜索结果列表
     */
    List<HelpDocSearchVO> search(HelpDocSearchDTO searchDTO);
}
