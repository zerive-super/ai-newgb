package com.fxt.newgb.mapper.battery;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.battery.BatterySchemeRule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BatterySchemeRuleMapper extends BaseMapper<BatterySchemeRule> {

    @Select("SELECT * FROM battery_scheme_rule WHERE scheme_id = #{schemeId} AND deleted = 0 ORDER BY sort ASC")
    List<BatterySchemeRule> selectBySchemeId(@Param("schemeId") Long schemeId);

    @Select("SELECT COUNT(*) FROM battery_scheme_rule WHERE scheme_id = #{schemeId} AND deleted = 0")
    int countBySchemeId(@Param("schemeId") Long schemeId);
}
