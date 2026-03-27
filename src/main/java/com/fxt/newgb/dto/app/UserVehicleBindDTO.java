package com.fxt.newgb.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserVehicleBindDTO {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "用户账号不能为空")
    private String userAccount;

    // @NotNull(message = "车辆id不能为空")
    private Long vehicleId;

    @NotBlank(message = "imei不能为空")
    private String imei;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}

