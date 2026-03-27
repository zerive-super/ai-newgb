package com.fxt.newgb.controller.app;

import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.app.BatteryConfigSaveDTO;
import com.fxt.newgb.service.app.AppBatteryConfigService;
import com.fxt.newgb.vo.app.BatteryConfigVO;
import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.common.SchemeOptionVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/api/v1")
public class AppBatteryConfigController {

    private final AppBatteryConfigService appBatteryConfigService;

    public AppBatteryConfigController(AppBatteryConfigService appBatteryConfigService) {
        this.appBatteryConfigService = appBatteryConfigService;
    }

    /**
     * 获取车辆电池配置
     *
     * @param vehicleId 车辆ID
     * @return 电池配置详情
     */
    @GetMapping("/battery-config")
    public R<BatteryConfigVO> getConfig(@RequestParam Long vehicleId) {
        BatteryConfigVO config = appBatteryConfigService.getConfig(vehicleId);
        return R.success(config);
    }

    /**
     * 保存电池配置
     *
     * @param dto 保存DTO
     * @return 保存结果
     */
    @PostMapping("/battery-config")
    public R<Boolean> saveConfig(@Valid @RequestBody BatteryConfigSaveDTO dto) {
        boolean result = appBatteryConfigService.saveConfig(dto);
        return R.success(result);
    }

    /**
     * 获取电池类型选项列表
     *
     * @return 类型选项列表
     */
    @GetMapping("/battery-type/options")
    public R<List<OptionVO>> getBatteryTypeOptions() {
        List<OptionVO> options = appBatteryConfigService.getBatteryTypeOptions();
        return R.success(options);
    }

    /**
     * 获取电池电压选项列表
     *
     * @param batteryType 电池类型编码
     * @return 电压选项列表
     */
    @GetMapping("/battery-voltage/options")
    public R<List<OptionVO>> getBatteryVoltageOptions(@RequestParam String batteryType) {
        List<OptionVO> options = appBatteryConfigService.getBatteryVoltageOptions(batteryType);
        return R.success(options);
    }

    /**
     * 获取电池方案选项列表
     *
     * @param batteryType 电池类型编码
     * @param batteryVoltage 电池电压值
     * @return 方案选项列表
     */
    @GetMapping("/battery-scheme/options")
    public R<List<SchemeOptionVO>> getBatterySchemeOptions(
            @RequestParam(required = false) String batteryType,
            @RequestParam(required = false) String batteryVoltage) {
        List<SchemeOptionVO> options = appBatteryConfigService.getBatterySchemeOptions(batteryType, batteryVoltage);
        return R.success(options);
    }
}
