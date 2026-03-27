package com.fxt.newgb.service.help;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxt.newgb.dto.help.HelpDocSaveDTO;
import com.fxt.newgb.dto.help.HelpDocUpdateDTO;
import com.fxt.newgb.entity.help.HelpDoc;
import com.fxt.newgb.vo.help.HelpDocDetailVO;
import com.fxt.newgb.vo.help.HelpDocSearchVO;
import com.fxt.newgb.vo.help.HelpDocVO;

import java.util.List;

public interface HelpDocService extends IService<HelpDoc> {

    /**
     * 分页查询帮助文档列表
     *
     * @param page 分页参数
     * @param title 标题（模糊搜索）
     * @param functionType 功能类型（精确匹配）
     * @return 分页结果
     */
    IPage<HelpDocVO> getPage(Page<HelpDocVO> page, String title, String functionType);

    /**
     * 获取帮助文档详情
     *
     * @param id 文档ID
     * @return 文档详情
     */
    HelpDocDetailVO getDetail(Long id);

    /**
     * 新增帮助文档
     *
     * @param dto 保存DTO
     * @return 新增记录ID
     */
    Long save(HelpDocSaveDTO dto);

    /**
     * 修改帮助文档
     *
     * @param id 文档ID
     * @param dto 更新DTO
     * @return 更新结果
     */
    boolean update(Long id, HelpDocUpdateDTO dto);

    /**
     * 删除帮助文档
     *
     * @param id 文档ID
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 文档内容检索
     *
     * @param keyword 检索关键字
     * @return 检索结果列表
     */
    List<HelpDocSearchVO> search(String keyword);
}
