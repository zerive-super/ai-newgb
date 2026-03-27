package com.fxt.newgb.service.app;

import com.fxt.newgb.dto.app.UserVehicleBindDTO;
import com.fxt.newgb.dto.app.UserVehiclePageQueryDTO;
import com.fxt.newgb.dto.app.UserVehicleUnbindDTO;
import com.fxt.newgb.vo.app.UserVehicleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface AppUserVehicleService {

    boolean bind(UserVehicleBindDTO dto);

    boolean unbind(UserVehicleUnbindDTO dto);

    List<UserVehicleVO> list(Long userId);

    IPage<UserVehicleVO> page(Page<UserVehicleVO> page, UserVehiclePageQueryDTO query);
}
