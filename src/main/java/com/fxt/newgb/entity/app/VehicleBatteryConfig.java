package com.fxt.newgb.entity.app;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

import java.time.LocalDateTime;

@TableName("vehicle_battery_config")
public class VehicleBatteryConfig extends BaseEntity {

    /**
     * 车辆ID
     */
    private Long vehicleId;

    /**
     * 电池类型编码
     */
    private String batteryType;

    /**
     * 电池电压值（V）
     */
    private Integer batteryVoltage;

    /**
     * 关联电池方案ID
     */
    private Long schemeId;

    /**
     * 同步状态（0:待同步 1:已同步 2:同步失败）
     */
    private String syncStatus;

    /**
     * 同步时间
     */
    private LocalDateTime syncTime;

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

    public Integer getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Integer batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public LocalDateTime getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(LocalDateTime syncTime) {
        this.syncTime = syncTime;
    }
}
