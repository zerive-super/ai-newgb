package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserVehicleUnbindDTO {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "imei不能为空")
    private String imei;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}

