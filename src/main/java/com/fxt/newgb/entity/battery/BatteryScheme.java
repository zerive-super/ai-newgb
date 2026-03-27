package com.fxt.newgb.entity.battery;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

import java.math.BigDecimal;

@TableName("battery_scheme")
public class BatteryScheme extends BaseEntity {

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 电池电压参数（V）
     */
    private BigDecimal batteryVoltage;

    /**
     * 电池类型编码
     */
    private String batteryType;

    /**
     * 方案描述
     */
    private String description;

    /**
     * 状态（1:启用 0:停用）
     */
    private String status;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public BigDecimal getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(BigDecimal batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
