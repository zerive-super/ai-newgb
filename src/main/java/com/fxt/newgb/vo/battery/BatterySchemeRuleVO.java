package com.fxt.newgb.vo.battery;

import java.math.BigDecimal;

public class BatterySchemeRuleVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 电量百分比
     */
    private Integer batteryPercent;

    /**
     * 静置电压
     */
    private BigDecimal staticVoltage;

    /**
     * 负载电压
     */
    private BigDecimal loadVoltage;

    /**
     * 续航里程
     */
    private BigDecimal mileage;

    /**
     * 排序号
     */
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(Integer batteryPercent) {
        this.batteryPercent = batteryPercent;
    }

    public BigDecimal getStaticVoltage() {
        return staticVoltage;
    }

    public void setStaticVoltage(BigDecimal staticVoltage) {
        this.staticVoltage = staticVoltage;
    }

    public BigDecimal getLoadVoltage() {
        return loadVoltage;
    }

    public void setLoadVoltage(BigDecimal loadVoltage) {
        this.loadVoltage = loadVoltage;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
