package com.fxt.newgb.controller.battery;

import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.app.VehicleBatterySchemeBindDTO;
import com.fxt.newgb.dto.app.VehicleBatterySchemeUnbindDTO;
import com.fxt.newgb.service.app.AppVehicleBatterySchemeService;
import com.fxt.newgb.vo.app.VehicleBatterySchemeVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VehicleBatterySchemeController {

    private final AppVehicleBatterySchemeService appVehicleBatterySchemeService;

    public VehicleBatterySchemeController(AppVehicleBatterySchemeService appVehicleBatterySchemeService) {
        this.appVehicleBatterySchemeService = appVehicleBatterySchemeService;
    }

    /**
     * 车辆（IMEI）绑定电池方案
     *
     * @param dto 绑定参数（imei、电池方案id）
     * @return 绑定结果（已存在则更新为最新方案）
     */
    @PostMapping("/vehicle-battery-scheme/bind")
    public R<Boolean> bind(@Valid @RequestBody VehicleBatterySchemeBindDTO dto) {
        return R.success(appVehicleBatterySchemeService.bind(dto));
    }

    /**
     * 车辆（IMEI）解绑电池方案
     *
     * @param dto 解绑参数（imei）
     * @return 解绑结果
     */
    @PostMapping("/vehicle-battery-scheme/unbind")
    public R<Boolean> unbind(@Valid @RequestBody VehicleBatterySchemeUnbindDTO dto) {
        return R.success(appVehicleBatterySchemeService.unbind(dto));
    }

    /**
     * 车辆-电池方案绑定列表
     *
     * @param imei 设备imei（可选，不传则返回全部）
     * @return 绑定列表
     */
    @GetMapping("/vehicle-battery-scheme/list")
    public R<List<VehicleBatterySchemeVO>> list(@RequestParam(required = false) String imei) {
        return R.success(appVehicleBatterySchemeService.list(imei));
    }
}
