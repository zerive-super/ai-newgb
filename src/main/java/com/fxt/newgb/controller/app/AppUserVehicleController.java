package com.fxt.newgb.controller.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.app.UserVehicleBindDTO;
import com.fxt.newgb.dto.app.UserVehiclePageQueryDTO;
import com.fxt.newgb.dto.app.UserVehicleUnbindDTO;
import com.fxt.newgb.service.app.AppUserVehicleService;
import com.fxt.newgb.vo.app.UserVehicleVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/api/v1")
public class AppUserVehicleController {

    private final AppUserVehicleService appUserVehicleService;

    public AppUserVehicleController(AppUserVehicleService appUserVehicleService) {
        this.appUserVehicleService = appUserVehicleService;
    }

    /**
     * 用户绑定车辆
     *
     * @param dto 绑定参数（用户id、用户账号、车辆id、imei）
     * @return 绑定结果
     */
    @PostMapping("/user-vehicle/bind")
    public R<Boolean> bind(@Valid @RequestBody UserVehicleBindDTO dto) {
        return R.success(appUserVehicleService.bind(dto));
    }

    /**
     * 用户解绑车辆
     *
     * @param dto 解绑参数（用户id、imei）
     * @return 解绑结果
     */
    @PostMapping("/user-vehicle/unbind")
    public R<Boolean> unbind(@Valid @RequestBody UserVehicleUnbindDTO dto) {
        return R.success(appUserVehicleService.unbind(dto));
    }

    /**
     * 用户-车辆绑定列表
     *
     * @param userId 用户id
     * @return 绑定列表
     */
    @GetMapping("/user-vehicle/list")
    public R<List<UserVehicleVO>> list(@RequestParam Long userId) {
        return R.success(appUserVehicleService.list(userId));
    }

    /**
     * 用户-车辆绑定分页查询
     *
     * @param query 分页与查询参数（用户账号、imei、绑定状态）
     * @return 分页结果
     */
    @GetMapping("/user-vehicle/page")
    public R<IPage<UserVehicleVO>> page(@Valid UserVehiclePageQueryDTO query) {
        Page<UserVehicleVO> page = new Page<>(query.getCurrent(), query.getSize());
        return R.success(appUserVehicleService.page(page, query));
    }
}
