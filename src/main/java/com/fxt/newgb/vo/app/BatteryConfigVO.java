package com.fxt.newgb.vo.app;

public class BatteryConfigVO {

    /**
     * 电池类型编码
     */
    private String batteryType;

    /**
     * 电池类型名称
     */
    private String batteryTypeName;

    /**
     * 电池电压值
     */
    private Integer batteryVoltage;

    /**
     * 电池方案ID
     */
    private Long schemeId;

    /**
     * 电池方案名称
     */
    private String schemeName;

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

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
}
