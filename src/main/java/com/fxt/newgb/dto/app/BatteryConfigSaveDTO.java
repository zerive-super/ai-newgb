package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BatteryConfigSaveDTO {

    /**
     * 车辆ID
     */
    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;

    /**
     * 电池类型编码
     */
    @NotBlank(message = "电池类型不能为空")
    private String batteryType;

    /**
     * 电池电压值
     */
    @NotBlank(message = "电池电压不能为空")
    private String batteryVoltage;

    /**
     * 电池方案ID
     */
    @NotNull(message = "电池方案不能为空")
    private Long schemeId;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }
}
