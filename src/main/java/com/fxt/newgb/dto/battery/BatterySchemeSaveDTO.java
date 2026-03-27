package com.fxt.newgb.dto.battery;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class BatterySchemeSaveDTO {

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空")
    @Size(max = 50, message = "方案名称最大50字符")
    private String schemeName;

    /**
     * 电池电压
     */
    @NotNull(message = "电池电压不能为空")
    @Positive(message = "电池电压必须为正数")
    @DecimalMax(value = "999", message = "电池电压最大值999")
    private BigDecimal batteryVoltage;

    /**
     * 电池类型
     */
    @NotBlank(message = "电池类型不能为空")
    private String batteryType;

    /**
     * 方案描述
     */
    @Size(max = 500, message = "描述最大500字符")
    private String description;

    /**
     * 规则列表
     */
    @NotEmpty(message = "规则列表不能为空")
    private List<BatterySchemeRuleDTO> rules;

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

    public List<BatterySchemeRuleDTO> getRules() {
        return rules;
    }

    public void setRules(List<BatterySchemeRuleDTO> rules) {
        this.rules = rules;
    }

    public static class BatterySchemeRuleDTO {
        /**
         * 电量百分比
         */
        @NotNull(message = "电量百分比不能为空")
        @Min(value = 0, message = "电量百分比最小0")
        @Max(value = 100, message = "电量百分比最大100")
        private Integer batteryPercent;

        /**
         * 静置电压
         */
        @NotNull(message = "静置电压不能为空")
        @Positive(message = "静置电压必须为正数")
        private BigDecimal staticVoltage;

        /**
         * 负载电压
         */
        @NotNull(message = "负载电压不能为空")
        @Positive(message = "负载电压必须为正数")
        private BigDecimal loadVoltage;

        /**
         * 续航里程
         */
        @NotNull(message = "续航里程不能为空")
        @Positive(message = "续航里程必须为正数")
        private BigDecimal mileage;

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
    }
}
