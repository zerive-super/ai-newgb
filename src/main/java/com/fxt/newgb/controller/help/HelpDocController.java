package com.fxt.newgb.controller.help;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.help.HelpDocQueryDTO;
import com.fxt.newgb.dto.help.HelpDocSaveDTO;
import com.fxt.newgb.dto.help.HelpDocUpdateDTO;
import com.fxt.newgb.service.help.HelpDocService;
import com.fxt.newgb.vo.help.HelpDocDetailVO;
import com.fxt.newgb.vo.help.HelpDocSearchVO;
import com.fxt.newgb.vo.help.HelpDocVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/help-doc")
public class HelpDocController {

    private final HelpDocService helpDocService;

    public HelpDocController(HelpDocService helpDocService) {
        this.helpDocService = helpDocService;
    }

    @GetMapping("/page")
    public R<IPage<HelpDocVO>> page(@Valid HelpDocQueryDTO query) {
        Page<HelpDocVO> page = new Page<>(query.getCurrent(), query.getSize());
        IPage<HelpDocVO> result = helpDocService.getPage(page, query.getTitle(), query.getFunctionType());
        return R.success(result);
    }

    @GetMapping("/{id}")
    public R<HelpDocDetailVO> detail(@PathVariable Long id) {
        HelpDocDetailVO detail = helpDocService.getDetail(id);
        return R.success(detail);
    }

    @PostMapping
    public R<Long> save(@Valid @RequestBody HelpDocSaveDTO dto) {
        Long id = helpDocService.save(dto);
        return R.success(id);
    }

    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @Valid @RequestBody HelpDocUpdateDTO dto) {
        boolean result = helpDocService.update(id, dto);
        return R.success(result);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean result = helpDocService.delete(id);
        return R.success(result);
    }

    @GetMapping("/search")
    public R<List<HelpDocSearchVO>> search(@RequestParam String keyword) {
        List<HelpDocSearchVO> results = helpDocService.search(keyword);
        return R.success(results);
    }
}
