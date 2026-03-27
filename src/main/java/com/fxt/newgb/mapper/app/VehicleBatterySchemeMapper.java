package com.fxt.newgb.mapper.app;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxt.newgb.entity.app.VehicleBatteryScheme;
import com.fxt.newgb.vo.app.VehicleBatterySchemeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VehicleBatterySchemeMapper extends BaseMapper<VehicleBatteryScheme> {

    List<VehicleBatterySchemeVO> selectListVO(@Param("imei") String imei);
}

