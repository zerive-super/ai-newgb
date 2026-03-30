package com.fxt.newgb.mapper.help;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.help.HelpDoc;
import com.fxt.newgb.vo.app.HelpDocListVO;
import com.fxt.newgb.vo.app.HelpDocSearchVO;
import com.fxt.newgb.vo.help.HelpDocVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HelpDocMapper extends BaseMapper<HelpDoc> {

    @Select("<script>" +
            "SELECT hd.id, hd.function_type AS functionType, hd.title, hd.sort, hd.description, " +
            "hd.status, hd.create_time AS createTime, hd.update_time AS updateTime, " +
            "(SELECT COUNT(*) FROM help_doc_content hdc WHERE hdc.doc_id = hd.id AND hdc.deleted = 0) AS contentCount " +
            "FROM help_doc hd " +
            "WHERE hd.deleted = 0 " +
            "<if test='title != null and title != \"\"'>" +
            "AND hd.title ILIKE CONCAT('%', #{title}::text, '%') " +
            "</if>" +
            "<if test='functionType != null and functionType != \"\"'>" +
            "AND hd.function_type = #{functionType} " +
            "</if>" +
            "ORDER BY hd.sort ASC, hd.create_time DESC" +
            "</script>")
    List<HelpDocVO> selectPageList(@Param("title") String title, @Param("functionType") String functionType);

    @Select("<script>" +
            "SELECT COUNT(*) FROM help_doc hd WHERE hd.deleted = 0 " +
            "<if test='title != null and title != \"\"'>" +
            "AND hd.title LIKE CONCAT('%', #{title}::text, '%') " +
            "</if>" +
            "<if test='functionType != null and functionType != \"\"'>" +
            "AND hd.function_type = #{functionType} " +
            "</if>" +
            "</script>")
    Long selectPageCount(@Param("title") String title, @Param("functionType") String functionType);

    @Select("<script>" +
            "SELECT hd.id, hd.title, hd.description, hd.function_type AS functionType, " +
            "CASE hd.function_type " +
            "WHEN 'VEHICLE_MANAGE' THEN '车辆管理' " +
            "WHEN 'TERMINAL_MODEL' THEN '终端型号管理' " +
            "WHEN 'TERMINAL' THEN '终端管理' " +
            "WHEN 'ASSET' THEN '资产列表' " +
            "WHEN 'ALARM' THEN '报警明细' " +
            "WHEN 'BATTERY_SCHEME' THEN '电池方案管理' " +
            "END AS functionTypeName " +
            "FROM help_doc hd " +
            "WHERE hd.deleted = 0 AND hd.status = '1' " +
            "AND (hd.title ILIKE CONCAT('%', #{keyword}::text, '%') " +
            "OR EXISTS (SELECT 1 FROM help_doc_content hdc WHERE hdc.doc_id = hd.id AND hdc.content ILIKE CONCAT('%', #{keyword}, '%'))) " +
            "LIMIT 20" +
            "</script>")
    List<HelpDocVO> searchByKeyword(@Param("keyword") String keyword);

    @Select("<script>" +
            "SELECT hd.id, hd.function_type AS functionType, " +
            "CASE hd.function_type " +
            "WHEN 'VEHICLE_MANAGE' THEN '车辆管理' " +
            "WHEN 'BATTERY_CONFIG' THEN '电池配置' " +
            "WHEN 'ALARM' THEN '报警提醒' " +
            "WHEN 'SHARE' THEN '用车人管理' " +
            "WHEN 'LOCATION' THEN '车辆位置' " +
            "WHEN 'RIDING' THEN '骑行轨迹' " +
            "END AS functionTypeName, " +
            "hd.title, hd.description " +
            "FROM help_doc hd " +
            "WHERE hd.deleted = 0 AND hd.status = '1' " +
            "<if test='functionType != null and functionType != \"\"'>" +
            "AND hd.function_type = #{functionType} " +
            "</if>" +
            "ORDER BY hd.sort ASC" +
            "</script>")
    List<HelpDocListVO> selectListVO(@Param("functionType") String functionType);

    @Select("<script>" +
            "SELECT hd.id, hd.title, " +
            "CASE hd.function_type " +
            "WHEN 'VEHICLE_MANAGE' THEN '车辆管理' " +
            "WHEN 'BATTERY_CONFIG' THEN '电池配置' " +
            "WHEN 'ALARM' THEN '报警提醒' " +
            "WHEN 'SHARE' THEN '用车人管理' " +
            "WHEN 'LOCATION' THEN '车辆位置' " +
            "WHEN 'RIDING' THEN '骑行轨迹' " +
            "END AS functionTypeName, " +
            "CASE " +
            "WHEN hdc.content ILIKE '%' || #{keyword}::text || '%' " +
            "THEN SUBSTRING(hdc.content, POSITION(#{keyword} IN hdc.content) - 20, 60) " +
            "ELSE '' " +
            "END AS matchSnippet " +
            "FROM help_doc hd " +
            "LEFT JOIN help_doc_content hdc ON hd.id = hdc.doc_id AND hdc.deleted = 0 " +
            "WHERE hd.deleted = 0 AND hd.status = '1' " +
            "AND (hd.title ILIKE '%' || #{keyword}::text || '%' " +
            "OR hd.description ILIKE '%' || #{keyword}::text || '%' " +
            "OR hdc.content ILIKE '%' || #{keyword}::text || '%') " +
            "ORDER BY hd.sort ASC" +
            "</script>")
    List<HelpDocSearchVO> search(@Param("keyword") String keyword);
}
