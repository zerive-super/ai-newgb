package com.fxt.newgb.service.battery;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxt.newgb.dto.battery.BatterySchemeSaveDTO;
import com.fxt.newgb.dto.battery.BatterySchemeUpdateDTO;
import com.fxt.newgb.dto.battery.BatterySchemeVerifyDTO;
import com.fxt.newgb.entity.battery.BatteryScheme;
import com.fxt.newgb.vo.battery.BatterySchemeDetailVO;
import com.fxt.newgb.vo.battery.BatterySchemeVerifyVO;
import com.fxt.newgb.vo.battery.BatterySchemeVO;
import com.fxt.newgb.vo.common.BusinessOptionVO;

import java.util.List;

public interface BatterySchemeService extends IService<BatteryScheme> {

    /**
     * 分页查询电池方案列表
     *
     * @param page 分页参数
     * @param schemeName 方案名称（模糊搜索）
     * @return 分页结果
     */
    IPage<BatterySchemeVO> getPage(Page<BatterySchemeVO> page, String schemeName);

    /**
     * 获取电池方案详情
     *
     * @param id 方案ID
     * @return 方案详情
     */
    BatterySchemeDetailVO getDetail(Long id);

    /**
     * 新增电池方案
     *
     * @param dto 保存DTO
     * @return 新增记录ID
     */
    Long save(BatterySchemeSaveDTO dto);

    /**
     * 修改电池方案
     *
     * @param id 方案ID
     * @param dto 更新DTO
     * @return 更新结果
     */
    boolean update(Long id, BatterySchemeUpdateDTO dto);

    /**
     * 删除电池方案
     *
     * @param id 方案ID
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 获取电池方案选项列表
     *
     * @param keyword 方案名称关键字
     * @return 选项列表
     */
    List<BusinessOptionVO> getOptions(String keyword);

    /**
     * 验证电池方案
     *
     * @param dto 验证DTO
     * @return 验证结果
     */
    BatterySchemeVerifyVO verify(BatterySchemeVerifyDTO dto);
}
