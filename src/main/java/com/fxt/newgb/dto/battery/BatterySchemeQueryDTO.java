package com.fxt.newgb.dto.battery;

import com.fxt.newgb.common.dto.PageQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class BatterySchemeQueryDTO extends PageQuery {

    /**
     * 方案名称（模糊搜索）
     */
    private String schemeName;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
}
