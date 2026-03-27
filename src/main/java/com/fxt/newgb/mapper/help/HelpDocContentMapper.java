package com.fxt.newgb.mapper.help;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.help.HelpDocContent;
import com.fxt.newgb.vo.app.HelpDocContentVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HelpDocContentMapper extends BaseMapper<HelpDocContent> {

    @Select("SELECT * FROM help_doc_content WHERE doc_id = #{docId} AND deleted = 0 ORDER BY sort ASC")
    List<HelpDocContent> selectByDocId(@Param("docId") Long docId);

    @Select("SELECT id, sort, content, image_url AS imageUrl FROM help_doc_content WHERE doc_id = #{docId} AND deleted = 0 ORDER BY sort ASC")
    List<HelpDocContentVO> selectVOByDocId(@Param("docId") Long docId);

    @Select("SELECT COUNT(*) FROM help_doc_content WHERE doc_id = #{docId} AND deleted = 0")
    int countByDocId(@Param("docId") Long docId);
}
