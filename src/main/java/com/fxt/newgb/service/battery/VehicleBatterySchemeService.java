package com.fxt.newgb.service.battery;

import com.fxt.newgb.dto.app.VehicleBatterySchemeBindDTO;
import com.fxt.newgb.dto.app.VehicleBatterySchemeUnbindDTO;
import com.fxt.newgb.vo.app.VehicleBatterySchemeVO;

import java.util.List;

public interface VehicleBatterySchemeService {

    boolean bind(VehicleBatterySchemeBindDTO dto);

    boolean unbind(VehicleBatterySchemeUnbindDTO dto);

    List<VehicleBatterySchemeVO> list(String imei);
}

