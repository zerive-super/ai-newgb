package com.fxt.newgb.controller.app;

import com.fxt.newgb.common.response.R;
import com.fxt.newgb.dto.app.HelpDocQueryDTO;
import com.fxt.newgb.dto.app.HelpDocSearchDTO;
import com.fxt.newgb.service.app.AppHelpDocService;
import com.fxt.newgb.vo.app.HelpDocDetailVO;
import com.fxt.newgb.vo.app.HelpDocListVO;
import com.fxt.newgb.vo.app.HelpDocSearchVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/api/v1/help-doc")
public class AppHelpDocController {

    private final AppHelpDocService appHelpDocService;

    public AppHelpDocController(AppHelpDocService appHelpDocService) {
        this.appHelpDocService = appHelpDocService;
    }

    /**
     * 获取帮助文档列表
     *
     * @param functionType 功能类型编码，不传查询全部
     * @return 文档列表
     */
    @GetMapping("/list")
    public R<List<HelpDocListVO>> list(@RequestParam(required = false) String functionType) {
        HelpDocQueryDTO queryDTO = new HelpDocQueryDTO();
        queryDTO.setFunctionType(functionType);
        List<HelpDocListVO> list = appHelpDocService.getList(queryDTO);
        return R.success(list);
    }

    /**
     * 获取帮助文档详情
     *
     * @param id 文档ID
     * @return 文档详情
     */
    @GetMapping("/{id}")
    public R<HelpDocDetailVO> detail(@PathVariable Long id) {
        HelpDocDetailVO detail = appHelpDocService.getDetail(id);
        return R.success(detail);
    }

    /**
     * 搜索帮助文档
     *
     * @param keyword 搜索关键字
     * @return 搜索结果列表
     */
    @GetMapping("/search")
    public R<List<HelpDocSearchVO>> search(@Valid HelpDocSearchDTO searchDTO) {
        List<HelpDocSearchVO> result = appHelpDocService.search(searchDTO);
        return R.success(result);
    }
}
