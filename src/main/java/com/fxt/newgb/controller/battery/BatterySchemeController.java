package com.fxt.newgb.controller.battery;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.battery.BatterySchemeSaveDTO;
import com.fxt.newgb.dto.battery.BatterySchemeUpdateDTO;
import com.fxt.newgb.dto.battery.BatterySchemeVerifyDTO;
import com.fxt.newgb.dto.battery.BatterySchemeQueryDTO;
import com.fxt.newgb.service.battery.BatterySchemeService;
import com.fxt.newgb.vo.battery.BatterySchemeDetailVO;
import com.fxt.newgb.vo.battery.BatterySchemeVerifyVO;
import com.fxt.newgb.vo.battery.BatterySchemeVO;
import com.fxt.newgb.vo.common.BusinessOptionVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/battery-scheme")
public class BatterySchemeController {

    private final BatterySchemeService batterySchemeService;

    public BatterySchemeController(BatterySchemeService batterySchemeService) {
        this.batterySchemeService = batterySchemeService;
    }

    @GetMapping("/page")
    public R<IPage<BatterySchemeVO>> page(@Valid BatterySchemeQueryDTO query) {
        Page<BatterySchemeVO> page = new Page<>(query.getCurrent(), query.getSize());
        IPage<BatterySchemeVO> result = batterySchemeService.getPage(page, query.getSchemeName());
        return R.success(result);
    }

    @GetMapping("/{id}")
    public R<BatterySchemeDetailVO> detail(@PathVariable Long id) {
        BatterySchemeDetailVO detail = batterySchemeService.getDetail(id);
        return R.success(detail);
    }

    @PostMapping
    public R<Long> save(@Valid @RequestBody BatterySchemeSaveDTO dto) {
        Long id = batterySchemeService.save(dto);
        return R.success(id);
    }

    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @Valid @RequestBody BatterySchemeUpdateDTO dto) {
        boolean result = batterySchemeService.update(id, dto);
        return R.success(result);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean result = batterySchemeService.delete(id);
        return R.success(result);
    }

    @GetMapping("/options")
    public R<List<BusinessOptionVO>> options(@RequestParam(required = false) String keyword) {
        List<BusinessOptionVO> options = batterySchemeService.getOptions(keyword);
        return R.success(options);
    }

    @PostMapping("/verify")
    public R<BatterySchemeVerifyVO> verify(@Valid @RequestBody BatterySchemeVerifyDTO dto) {
        BatterySchemeVerifyVO result = batterySchemeService.verify(dto);
        return R.success(result);
    }
}
