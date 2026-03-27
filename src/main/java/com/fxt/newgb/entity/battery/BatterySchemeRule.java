package com.fxt.newgb.entity.battery;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

import java.math.BigDecimal;

@TableName("battery_scheme_rule")
public class BatterySchemeRule extends BaseEntity {

    /**
     * 关联电池方案ID
     */
    private Long schemeId;

    /**
     * 电量百分比（0-100）
     */
    private Integer batteryPercent;

    /**
     * 静置电压值（V）
     */
    private BigDecimal staticVoltage;

    /**
     * 负载电压值（V）
     */
    private BigDecimal loadVoltage;

    /**
     * 续航里程（km）
     */
    private BigDecimal mileage;

    /**
     * 排序号
     */
    private Integer sort;

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
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
