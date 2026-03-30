package com.fxt.newgb.dto.app;

import com.fxt.newgb.common.dto.PageQuery;

public class UserVehiclePageQueryDTO extends PageQuery {

    /**
     * 用户账号（模糊）
     */
    private String userAccount;

    /**
     * IMEI（精确）
     */
    private String imei;

    /**
     * 绑定状态：1-已绑定，0-已解绑（精确）
     */
    private String bindStatus;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }
}

