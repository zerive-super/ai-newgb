package com.fxt.newgb.mapper.battery;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.battery.BatteryScheme;
import com.fxt.newgb.vo.battery.BatterySchemeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BatterySchemeMapper extends BaseMapper<BatteryScheme> {

    @Select("<script>" +
            "SELECT bs.id, bs.scheme_name AS schemeName, bs.battery_voltage AS batteryVoltage, " +
            "bs.battery_type AS batteryType, bs.status, bs.create_time AS createTime, " +
            "(SELECT COUNT(*) FROM battery_scheme_rule bsr WHERE bsr.scheme_id = bs.id AND bsr.deleted = 0) AS ruleCount, " +
            "0 AS referenceCount " +
            "FROM battery_scheme bs " +
            "WHERE bs.deleted = 0 " +
            "<if test='schemeName != null and schemeName != \"\"'>" +
            "AND bs.scheme_name LIKE CONCAT('%', #{schemeName}, '%') " +
            "</if>" +
            "ORDER BY bs.create_time DESC" +
            "</script>")
    List<BatterySchemeVO> selectPageList(@Param("schemeName") String schemeName);

    @Select("<script>" +
            "SELECT COUNT(*) FROM battery_scheme bs WHERE bs.deleted = 0 " +
            "<if test='schemeName != null and schemeName != \"\"'>" +
            "AND bs.scheme_name LIKE CONCAT('%', #{schemeName}, '%') " +
            "</if>" +
            "</script>")
    Long selectPageCount(@Param("schemeName") String schemeName);

    @Select("SELECT COUNT(*) FROM battery_scheme WHERE scheme_name = #{schemeName} AND deleted = 0")
    int countBySchemeName(@Param("schemeName") String schemeName);

    @Select("SELECT COUNT(*) FROM battery_scheme WHERE scheme_name = #{schemeName} AND id != #{excludeId} AND deleted = 0")
    int countBySchemeNameExcludeId(@Param("schemeName") String schemeName, @Param("excludeId") Long excludeId);
}
