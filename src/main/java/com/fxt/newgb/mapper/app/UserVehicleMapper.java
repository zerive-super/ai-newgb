package com.fxt.newgb.mapper.app;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.app.UserVehicle;
import com.fxt.newgb.vo.app.UserVehicleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserVehicleMapper extends BaseMapper<UserVehicle> {

    List<UserVehicleVO> selectListVO(@Param("userId") Long userId);
}

