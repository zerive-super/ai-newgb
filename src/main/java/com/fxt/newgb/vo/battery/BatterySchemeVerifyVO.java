package com.fxt.newgb.vo.battery;

import java.math.BigDecimal;

public class BatterySchemeVerifyVO {

    /**
     * 电量百分比
     */
    private Integer batteryPercent;

    /**
     * 续航里程
     */
    private BigDecimal mileage;

    public Integer getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(Integer batteryPercent) {
        this.batteryPercent = batteryPercent;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}
