package com.fxt.newgb.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fxt.newgb.common.exception.BusinessException;
import com.fxt.newgb.dto.app.UserVehicleBindDTO;
import com.fxt.newgb.dto.app.UserVehiclePageQueryDTO;
import com.fxt.newgb.dto.app.UserVehicleUnbindDTO;
import com.fxt.newgb.entity.app.UserVehicle;
import com.fxt.newgb.mapper.app.UserVehicleMapper;
import com.fxt.newgb.service.app.AppUserVehicleService;
import com.fxt.newgb.vo.app.UserVehicleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserVehicleServiceImpl implements AppUserVehicleService {

    private final UserVehicleMapper userVehicleMapper;
    private static final String BIND_STATUS_BOUND = "1";
    private static final String BIND_STATUS_UNBOUND = "0";

    public AppUserVehicleServiceImpl(UserVehicleMapper userVehicleMapper) {
        this.userVehicleMapper = userVehicleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bind(UserVehicleBindDTO dto) {
        // IMEI已被其他用户“已绑定”: 报错（未绑定的历史记录不影响）
        LambdaQueryWrapper<UserVehicle> otherUserBoundQw = new LambdaQueryWrapper<UserVehicle>()
                .ne(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei())
                .eq(UserVehicle::getBindStatus, BIND_STATUS_BOUND);
        Long otherBoundCount = userVehicleMapper.selectCount(otherUserBoundQw);
        if (otherBoundCount != null && otherBoundCount > 0) {
            throw new BusinessException(409, "设备IMEI已绑定其他用户");
        }

        // 同一用户同一IMEI存在则更新为“已绑定”，否则新增
        LambdaQueryWrapper<UserVehicle> sameQw = new LambdaQueryWrapper<UserVehicle>()
                .eq(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei());

        Long sameCount = userVehicleMapper.selectCount(sameQw);
        if (sameCount != null && sameCount > 0) {
            UserVehicle update = new UserVehicle();
            update.setUserAccount(dto.getUserAccount());
            update.setVehicleId(dto.getVehicleId());
            update.setBindStatus(BIND_STATUS_BOUND);
            userVehicleMapper.update(update, sameQw);
            return true;
        }

        UserVehicle uv = new UserVehicle();
        uv.setUserId(dto.getUserId());
        uv.setUserAccount(dto.getUserAccount());
        uv.setVehicleId(dto.getVehicleId());
        uv.setImei(dto.getImei());
        uv.setBindStatus(BIND_STATUS_BOUND);
        return userVehicleMapper.insert(uv) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unbind(UserVehicleUnbindDTO dto) {
        LambdaQueryWrapper<UserVehicle> qw = new LambdaQueryWrapper<UserVehicle>()
                .eq(UserVehicle::getUserId, dto.getUserId())
                .eq(UserVehicle::getImei, dto.getImei())
                .eq(UserVehicle::getBindStatus, BIND_STATUS_BOUND);

        Long count = userVehicleMapper.selectCount(qw);
        if (count == null || count == 0) {
            return true;
        }

        UserVehicle update = new UserVehicle();
        update.setBindStatus(BIND_STATUS_UNBOUND);
        userVehicleMapper.update(update, qw);
        return true;
    }

    @Override
    public List<UserVehicleVO> list(Long userId) {
        if (userId == null) {
            throw new BusinessException(400, "userId不能为空");
        }
        return userVehicleMapper.selectListVO(userId);
    }

    @Override
    public IPage<UserVehicleVO> page(Page<UserVehicleVO> page, UserVehiclePageQueryDTO query) {
        return userVehicleMapper.selectPageVO(page, query.getUserAccount(), query.getImei(), query.getBindStatus());
    }
}
