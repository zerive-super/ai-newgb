package com.fxt.newgb.entity.app;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxt.newgb.common.entity.BaseEntity;

@TableName("user_vehicle")
public class UserVehicle extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 车辆ID
     */
    private Long vehicleId;

    /**
     * IMEI
     */
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

