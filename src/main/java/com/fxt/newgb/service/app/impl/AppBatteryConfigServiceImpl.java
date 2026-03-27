package com.fxt.newgb.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxt.newgb.dto.app.BatteryConfigSaveDTO;
import com.fxt.newgb.entity.app.BatteryConfigLog;
import com.fxt.newgb.entity.app.VehicleBatteryConfig;
import com.fxt.newgb.entity.battery.BatteryScheme;
import com.fxt.newgb.enums.app.AppBatteryTypeEnum;
import com.fxt.newgb.enums.app.BatteryConfigErrorCode;
import com.fxt.newgb.enums.app.SyncStatusEnum;
import com.fxt.newgb.mapper.app.BatteryConfigLogMapper;
import com.fxt.newgb.mapper.app.VehicleBatteryConfigMapper;
import com.fxt.newgb.mapper.battery.BatterySchemeMapper;
import com.fxt.newgb.service.app.AppBatteryConfigService;
import com.fxt.newgb.vo.app.BatteryConfigVO;
import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.common.SchemeOptionVO;
import com.fxt.newgb.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppBatteryConfigServiceImpl implements AppBatteryConfigService {

    private final VehicleBatteryConfigMapper vehicleBatteryConfigMapper;
    private final BatteryConfigLogMapper batteryConfigLogMapper;
    private final BatterySchemeMapper batterySchemeMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public AppBatteryConfigServiceImpl(VehicleBatteryConfigMapper vehicleBatteryConfigMapper,
                                       BatteryConfigLogMapper batteryConfigLogMapper,
                                       BatterySchemeMapper batterySchemeMapper,
                                       ObjectMapper objectMapper) {
        this.vehicleBatteryConfigMapper = vehicleBatteryConfigMapper;
        this.batteryConfigLogMapper = batteryConfigLogMapper;
        this.batterySchemeMapper = batterySchemeMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public BatteryConfigVO getConfig(Long vehicleId) {
        return vehicleBatteryConfigMapper.selectConfigVO(vehicleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveConfig(BatteryConfigSaveDTO dto) {
        // 1. 校验电池类型有效性
        AppBatteryTypeEnum batteryTypeEnum = AppBatteryTypeEnum.getByCode(dto.getBatteryType());
        if (batteryTypeEnum == null) {
            throw new BusinessException(BatteryConfigErrorCode.INVALID_BATTERY_TYPE.getCode(),
                    BatteryConfigErrorCode.INVALID_BATTERY_TYPE.getMessage());
        }

        // 2. 校验电池电压有效性
        List<String> validVoltages = getValidVoltages(dto.getBatteryType());
        if (!validVoltages.contains(dto.getBatteryVoltage())) {
            throw new BusinessException(BatteryConfigErrorCode.INVALID_BATTERY_VOLTAGE.getCode(),
                    BatteryConfigErrorCode.INVALID_BATTERY_VOLTAGE.getMessage());
        }

        // 3. 校验电池方案是否存在
        BatteryScheme scheme = batterySchemeMapper.selectById(dto.getSchemeId());
        if (scheme == null || !"1".equals(scheme.getStatus())) {
            throw new BusinessException(BatteryConfigErrorCode.SCHEME_NOT_FOUND.getCode(),
                    BatteryConfigErrorCode.SCHEME_NOT_FOUND.getMessage());
        }

        // 4. 查询是否存在历史配置
        LambdaQueryWrapper<VehicleBatteryConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VehicleBatteryConfig::getVehicleId, dto.getVehicleId());
        VehicleBatteryConfig existingConfig = vehicleBatteryConfigMapper.selectOne(queryWrapper);

        // 5. 保存或更新配置
        VehicleBatteryConfig config = new VehicleBatteryConfig();
        config.setVehicleId(dto.getVehicleId());
        config.setBatteryType(dto.getBatteryType());
        config.setBatteryVoltage(Integer.parseInt(dto.getBatteryVoltage()));
        config.setSchemeId(dto.getSchemeId());
        config.setSyncStatus(SyncStatusEnum.PENDING.getCode());

        String operationType;
        if (existingConfig != null) {
            // 更新配置
            config.setId(existingConfig.getId());
            config.setUpdateTime(LocalDateTime.now());
            vehicleBatteryConfigMapper.updateById(config);
            operationType = "UPDATE";
        } else {
            // 新增配置
            config.setCreateTime(LocalDateTime.now());
            vehicleBatteryConfigMapper.insert(config);
            operationType = "CREATE";
        }

        // 6. 记录变更日志
        BatteryConfigLog log = new BatteryConfigLog();
        log.setVehicleId(dto.getVehicleId());
        log.setOperationType(operationType);
        log.setAfterData(toJson(config));
        if (existingConfig != null) {
            log.setBeforeData(toJson(existingConfig));
        }
        log.setOperatorId(1L);
        log.setOperationTime(LocalDateTime.now());
        log.setCreateTime(LocalDateTime.now());
        batteryConfigLogMapper.insert(log);

        return true;
    }

    @Override
    public List<OptionVO> getBatteryTypeOptions() {
        return Arrays.stream(AppBatteryTypeEnum.values())
                .map(e -> new OptionVO(e.getCode(), e.getDesc()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OptionVO> getBatteryVoltageOptions(String batteryType) {
        List<String> voltages = getValidVoltages(batteryType);
        return voltages.stream()
                .map(v -> new OptionVO(v, v + "V"))
                .collect(Collectors.toList());
    }

    @Override
    public List<SchemeOptionVO> getBatterySchemeOptions(String batteryType, String batteryVoltage) {
        return vehicleBatteryConfigMapper.selectSchemeOptions(batteryType, batteryVoltage);
    }

    private List<String> getValidVoltages(String batteryType) {
        if ("LITHIUM".equals(batteryType)) {
            return Arrays.asList("48", "60", "72");
        } else if ("LEAD_ACID".equals(batteryType)) {
            return Arrays.asList("48", "60");
        }
        return List.of();
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}
