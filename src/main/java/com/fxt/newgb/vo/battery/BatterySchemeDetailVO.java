package com.fxt.newgb.vo.battery;

import java.math.BigDecimal;
import java.util.List;

public class BatterySchemeDetailVO {

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
     * 方案描述
     */
    private String description;

    /**
     * 规则列表
     */
    private List<BatterySchemeRuleVO> rules;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BatterySchemeRuleVO> getRules() {
        return rules;
    }

    public void setRules(List<BatterySchemeRuleVO> rules) {
        this.rules = rules;
    }
}
