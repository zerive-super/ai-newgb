package com.fxt.newgb.service.app;

import com.fxt.newgb.dto.app.BatteryConfigSaveDTO;
import com.fxt.newgb.vo.app.BatteryConfigVO;
import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.common.SchemeOptionVO;

import java.util.List;

public interface AppBatteryConfigService {

    /**
     * 获取车辆电池配置
     *
     * @param vehicleId 车辆ID
     * @return 电池配置详情
     */
    BatteryConfigVO getConfig(Long vehicleId);

    /**
     * 保存电池配置
     *
     * @param dto 保存DTO
     * @return 保存结果
     */
    boolean saveConfig(BatteryConfigSaveDTO dto);

    /**
     * 获取电池类型选项列表
     *
     * @return 类型选项列表
     */
    List<OptionVO> getBatteryTypeOptions();

    /**
     * 获取电池电压选项列表
     *
     * @param batteryType 电池类型编码
     * @return 电压选项列表
     */
    List<OptionVO> getBatteryVoltageOptions(String batteryType);

    /**
     * 获取电池方案选项列表
     *
     * @param batteryType 电池类型编码
     * @param batteryVoltage 电池电压值
     * @return 方案选项列表
     */
    List<SchemeOptionVO> getBatterySchemeOptions(String batteryType, String batteryVoltage);
}
