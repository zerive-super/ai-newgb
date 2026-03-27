package com.fxt.newgb.mapper.app;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.app.VehicleBatteryConfig;
import com.fxt.newgb.vo.app.BatteryConfigVO;
import com.fxt.newgb.vo.common.SchemeOptionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VehicleBatteryConfigMapper extends BaseMapper<VehicleBatteryConfig> {

    /**
     * 查询车辆电池配置详情
     *
     * @param vehicleId 车辆ID
     * @return 电池配置详情
     */
    BatteryConfigVO selectConfigVO(@Param("vehicleId") Long vehicleId);

    /**
     * 查询电池方案选项列表
     *
     * @param batteryType 电池类型
     * @param batteryVoltage 电池电压
     * @return 方案选项列表
     */
    List<SchemeOptionVO> selectSchemeOptions(@Param("batteryType") String batteryType,
                                            @Param("batteryVoltage") String batteryVoltage);
}
