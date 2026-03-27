package com.fxt.newgb.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fxt.newgb.common.exception.BusinessException;
import com.fxt.newgb.dto.app.VehicleBatterySchemeBindDTO;
import com.fxt.newgb.dto.app.VehicleBatterySchemeUnbindDTO;
import com.fxt.newgb.entity.app.VehicleBatteryScheme;
import com.fxt.newgb.entity.battery.BatteryScheme;
import com.fxt.newgb.mapper.app.VehicleBatterySchemeMapper;
import com.fxt.newgb.mapper.battery.BatterySchemeMapper;
import com.fxt.newgb.service.app.AppVehicleBatterySchemeService;
import com.fxt.newgb.vo.app.VehicleBatterySchemeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppVehicleBatterySchemeServiceImpl implements AppVehicleBatterySchemeService {

    private final VehicleBatterySchemeMapper vehicleBatterySchemeMapper;
    private final BatterySchemeMapper batterySchemeMapper;

    public AppVehicleBatterySchemeServiceImpl(VehicleBatterySchemeMapper vehicleBatterySchemeMapper,
                                              BatterySchemeMapper batterySchemeMapper) {
        this.vehicleBatterySchemeMapper = vehicleBatterySchemeMapper;
        this.batterySchemeMapper = batterySchemeMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bind(VehicleBatterySchemeBindDTO dto) {
        BatteryScheme scheme = batterySchemeMapper.selectById(dto.getSchemeId());
        if (scheme == null || !"1".equals(scheme.getStatus())) {
            throw new BusinessException(404, "电池方案不存在或未启用");
        }

        LambdaQueryWrapper<VehicleBatteryScheme> qw = new LambdaQueryWrapper<VehicleBatteryScheme>()
                .eq(VehicleBatteryScheme::getImei, dto.getImei());
        VehicleBatteryScheme existing = vehicleBatterySchemeMapper.selectOne(qw);

        if (existing != null) {
            VehicleBatteryScheme update = new VehicleBatteryScheme();
            update.setId(existing.getId());
            update.setImei(existing.getImei());
            update.setSchemeId(dto.getSchemeId());
            return vehicleBatterySchemeMapper.updateById(update) > 0;
        }

        VehicleBatteryScheme vbs = new VehicleBatteryScheme();
        vbs.setImei(dto.getImei());
        vbs.setSchemeId(dto.getSchemeId());
        return vehicleBatterySchemeMapper.insert(vbs) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unbind(VehicleBatterySchemeUnbindDTO dto) {
        LambdaQueryWrapper<VehicleBatteryScheme> qw = new LambdaQueryWrapper<VehicleBatteryScheme>()
                .eq(VehicleBatteryScheme::getImei, dto.getImei());
        VehicleBatteryScheme existing = vehicleBatterySchemeMapper.selectOne(qw);
        if (existing == null) {
            return true;
        }
        return vehicleBatterySchemeMapper.deleteById(existing.getId()) > 0;
    }

    @Override
    public List<VehicleBatterySchemeVO> list(String imei) {
        return vehicleBatterySchemeMapper.selectListVO(imei);
    }
}

