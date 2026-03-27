package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleBatterySchemeBindDTO {

    @NotBlank(message = "imei不能为空")
    private String imei;

    @NotNull(message = "电池方案id不能为空")
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

