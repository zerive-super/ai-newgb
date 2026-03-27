package com.fxt.newgb.controller.sys;

import com.fxt.newgb.common.response.R;
import com.fxt.newgb.service.sys.SysDictService;
import com.fxt.newgb.vo.common.OptionVO;
import com.fxt.newgb.vo.sys.SysDictVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dict")
public class SysDictController {

    private final SysDictService sysDictService;

    public SysDictController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 根据字典编码获取字典数据
     *
     * @param dictCode 字典编码
     * @return 字典数据列表
     */
    @GetMapping("/{dictCode}")
    public R<List<OptionVO>> getDictData(@PathVariable String dictCode) {
        List<OptionVO> options = sysDictService.getDictData(dictCode);
        return R.success(options);
    }

    /**
     * 根据字典编码和父级ID获取字典数据（用于级联选择）
     *
     * @param dictCode 字典编码
     * @param parentId 父级ID
     * @return 字典数据列表
     */
    @GetMapping("/{dictCode}/children")
    public R<List<OptionVO>> getDictDataWithParent(
            @PathVariable String dictCode,
            @RequestParam(required = false) Long parentId) {
        List<OptionVO> options = sysDictService.getDictData(dictCode, parentId);
        return R.success(options);
    }

    /**
     * 获取字典完整信息
     *
     * @param dictCode 字典编码
     * @return 字典完整信息
     */
    @GetMapping("/info/{dictCode}")
    public R<SysDictVO> getDict(@PathVariable String dictCode) {
        SysDictVO dict = sysDictService.getDict(dictCode);
        return R.success(dict);
    }

    /**
     * 批量获取多个字典的数据
     *
     * @param dictCodes 字典编码，多个用逗号分隔
     * @return 字典信息列表
     */
    @GetMapping("/batch")
    public R<List<SysDictVO>> getDicts(@RequestParam String dictCodes) {
        List<String> codeList = List.of(dictCodes.split(","));
        List<SysDictVO> dicts = sysDictService.getDicts(codeList);
        return R.success(dicts);
    }
}
