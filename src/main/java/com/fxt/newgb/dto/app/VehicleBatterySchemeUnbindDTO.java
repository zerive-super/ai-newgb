package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;

public class VehicleBatterySchemeUnbindDTO {

    @NotBlank(message = "imei不能为空")
    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}

