package com.fxt.newgb.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fxt.newgb.common.exception.BusinessException;
import com.fxt.newgb.dto.app.UserVehicleBindDTO;
import com.fxt.newgb.dto.app.UserVehicleUnbindDTO;
import com.fxt.newgb.entity.app.UserVehicle;
import com.fxt.newgb.mapper.app.UserVehicleMapper;
import com.fxt.newgb.service.app.AppUserVehicleService;
import com.fxt.newgb.vo.app.UserVehicleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserVehicleServiceImpl implements AppUserVehicleService {

    private final UserVehicleMapper userVehicleMapper;

    public AppUserVehicleServiceImpl(UserVehicleMapper userVehicleMapper) {
        this.userVehicleMapper = userVehicleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bind(UserVehicleBindDTO dto) {
        // 同一用户同一IMEI已绑定: 幂等返回成功
        LambdaQueryWrapper<UserVehicle> sameBindingQw = new LambdaQueryWrapper<UserVehicle>()
                .eq(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei());
        Long sameCount = userVehicleMapper.selectCount(sameBindingQw);
        if (sameCount != null && sameCount > 0) {
            return true;
        }

        // IMEI已被其他用户绑定: 报错
        LambdaQueryWrapper<UserVehicle> otherUserQw = new LambdaQueryWrapper<UserVehicle>()
                .ne(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei());
        Long otherCount = userVehicleMapper.selectCount(otherUserQw);
        if (otherCount != null && otherCount > 0) {
            throw new BusinessException(409, "设备IMEI已绑定其他用户");
        }

        UserVehicle uv = new UserVehicle();
        uv.setUserId(dto.getUserId());
        uv.setUserAccount(dto.getUserAccount());
        uv.setVehicleId(dto.getVehicleId());
        uv.setImei(dto.getImei());
        return userVehicleMapper.insert(uv) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unbind(UserVehicleUnbindDTO dto) {
        LambdaQueryWrapper<UserVehicle> qw = new LambdaQueryWrapper<UserVehicle>()
                .eq(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei());
        UserVehicle existing = userVehicleMapper.selectOne(qw);
        if (existing == null) {
            return true;
        }
        return userVehicleMapper.deleteById(existing.getId()) > 0;
    }

    @Override
    public List<UserVehicleVO> list(Long userId) {
        if (userId == null) {
            throw new BusinessException(400, "userId不能为空");
        }
        return userVehicleMapper.selectListVO(userId);
    }
}
