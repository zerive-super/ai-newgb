package com.fxt.newgb.dto.battery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class BatterySchemeVerifyDTO {

    /**
     * 电池方案ID
     */
    @NotNull(message = "方案ID不能为空")
    private Long schemeId;

    /**
     * 输入电压值
     */
    @NotNull(message = "电池电压不能为空")
    @Positive(message = "电池电压必须为正数")
    private BigDecimal batteryVoltage;

    /**
     * 电压类型
     */
    @NotBlank(message = "电压类型不能为空")
    private String voltageType;

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public BigDecimal getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(BigDecimal batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getVoltageType() {
        return voltageType;
    }

    public void setVoltageType(String voltageType) {
        this.voltageType = voltageType;
    }
}
