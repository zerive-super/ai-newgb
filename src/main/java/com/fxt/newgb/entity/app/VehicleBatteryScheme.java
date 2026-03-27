package com.fxt.newgb.entity.app;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

@TableName("vehicle_battery_scheme")
public class VehicleBatteryScheme extends BaseEntity {

    /**
     * 设备IMEI
     */
    private String imei;

    /**
     * 电池方案ID
     */
    private Long schemeId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }
}

