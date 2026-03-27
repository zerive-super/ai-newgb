package com.fxt.newgb.vo.battery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BatterySchemeVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 电池电压（V）
     */
    private BigDecimal batteryVoltage;

    /**
     * 电池类型编码
     */
    private String batteryType;

    /**
     * 电池类型名称
     */
    private String batteryTypeName;

    /**
     * 规则数量
     */
    private Integer ruleCount;

    /**
     * 被引用数量
     */
    private Integer referenceCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getBatteryTypeName() {
        return batteryTypeName;
    }

    public void setBatteryTypeName(String batteryTypeName) {
        this.batteryTypeName = batteryTypeName;
    }

    public Integer getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(Integer ruleCount) {
        this.ruleCount = ruleCount;
    }

    public Integer getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
