package com.fxt.newgb.service.battery.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxt.newgb.common.exception.BusinessException;
import com.fxt.newgb.dto.battery.BatterySchemeSaveDTO;
import com.fxt.newgb.dto.battery.BatterySchemeUpdateDTO;
import com.fxt.newgb.dto.battery.BatterySchemeVerifyDTO;
import com.fxt.newgb.entity.battery.BatteryScheme;
import com.fxt.newgb.entity.battery.BatterySchemeRule;
import com.fxt.newgb.enums.battery.BatterySchemeErrorCode;
import com.fxt.newgb.mapper.battery.BatterySchemeMapper;
import com.fxt.newgb.mapper.battery.BatterySchemeRuleMapper;
import com.fxt.newgb.service.battery.BatterySchemeService;
import com.fxt.newgb.vo.battery.BatterySchemeDetailVO;
import com.fxt.newgb.vo.battery.BatterySchemeRuleVO;
import com.fxt.newgb.vo.battery.BatterySchemeVerifyVO;
import com.fxt.newgb.vo.battery.BatterySchemeVO;
import com.fxt.newgb.vo.common.BusinessOptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class BatterySchemeServiceImpl extends ServiceImpl<BatterySchemeMapper, BatteryScheme> implements BatterySchemeService {

    private final BatterySchemeRuleMapper batterySchemeRuleMapper;

    public BatterySchemeServiceImpl(BatterySchemeRuleMapper batterySchemeRuleMapper) {
        this.batterySchemeRuleMapper = batterySchemeRuleMapper;
    }

    @Override
    public IPage<BatterySchemeVO> getPage(Page<BatterySchemeVO> page, String schemeName) {
        // 1. 查询列表数据
        List<BatterySchemeVO> records = baseMapper.selectPageList(schemeName);
        
        // 2. 查询总数
        Long total = baseMapper.selectPageCount(schemeName);
        
        // 3. 组装分页结果
        page.setTotal(total);
        page.setRecords(records);
        return page;
    }

    @Override
    public BatterySchemeDetailVO getDetail(Long id) {
        // 1. 查询方案是否存在
        BatteryScheme scheme = baseMapper.selectById(id);
        if (scheme == null || scheme.getDeleted() == 1) {
            throw new BusinessException(404, "方案不存在");
        }

        // 2. 查询规则列表
        List<BatterySchemeRule> rules = batterySchemeRuleMapper.selectBySchemeId(id);

        // 3. 组装详情VO
        BatterySchemeDetailVO detailVO = new BatterySchemeDetailVO();
        detailVO.setId(scheme.getId());
        detailVO.setSchemeName(scheme.getSchemeName());
        detailVO.setBatteryVoltage(scheme.getBatteryVoltage());
        detailVO.setBatteryType(scheme.getBatteryType());
        detailVO.setDescription(scheme.getDescription());

        List<BatterySchemeRuleVO> ruleVOList = rules.stream()
                .map(rule -> {
                    BatterySchemeRuleVO ruleVO = new BatterySchemeRuleVO();
                    ruleVO.setId(rule.getId());
                    ruleVO.setBatteryPercent(rule.getBatteryPercent());
                    ruleVO.setStaticVoltage(rule.getStaticVoltage());
                    ruleVO.setLoadVoltage(rule.getLoadVoltage());
                    ruleVO.setMileage(rule.getMileage());
                    ruleVO.setSort(rule.getSort());
                    return ruleVO;
                })
                .collect(Collectors.toList());
        detailVO.setRules(ruleVOList);

        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(BatterySchemeSaveDTO dto) {
        // 1. 校验方案名称唯一性
        int count = baseMapper.countBySchemeName(dto.getSchemeName());
        if (count > 0) {
            throw new BusinessException(
                BatterySchemeErrorCode.SCHEME_NAME_EXISTS.getCode(),
                BatterySchemeErrorCode.SCHEME_NAME_EXISTS.getMessage()
            );
        }

        // 2. 保存方案主表数据
        BatteryScheme scheme = new BatteryScheme();
        BeanUtils.copyProperties(dto, scheme);
        scheme.setStatus("1");
        baseMapper.insert(scheme);

        // 3. 批量保存规则数据
        saveRules(scheme.getId(), dto.getRules());

        return scheme.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, BatterySchemeUpdateDTO dto) {
        // 1. 校验方案是否存在
        BatteryScheme scheme = baseMapper.selectById(id);
        if (scheme == null || scheme.getDeleted() == 1) {
            throw new BusinessException(404, "方案不存在");
        }

        // 2. 校验方案名称唯一性（排除自身）
        int count = baseMapper.countBySchemeNameExcludeId(dto.getSchemeName(), id);
        if (count > 0) {
            throw new BusinessException(
                BatterySchemeErrorCode.SCHEME_NAME_EXISTS.getCode(),
                BatterySchemeErrorCode.SCHEME_NAME_EXISTS.getMessage()
            );
        }

        // 3. 更新方案主表数据
        BeanUtils.copyProperties(dto, scheme);
        baseMapper.updateById(scheme);

        // 4. 删除原有规则数据
        LambdaQueryWrapper<BatterySchemeRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BatterySchemeRule::getSchemeId, id);
        batterySchemeRuleMapper.delete(wrapper);

        // 5. 批量保存新规则数据
        saveUpdateRules(id, dto.getRules());

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        // 1. 校验方案是否存在
        BatteryScheme scheme = baseMapper.selectById(id);
        if (scheme == null || scheme.getDeleted() == 1) {
            throw new BusinessException(404, "方案不存在");
        }

        // 2. 检查方案是否被车辆引用（暂略，未来调用车辆管理模块）
        // 3. 执行逻辑删除
        scheme.setDeleted(1);
        baseMapper.updateById(scheme);

        // 4. 同时删除关联的规则数据
        LambdaQueryWrapper<BatterySchemeRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BatterySchemeRule::getSchemeId, id);
        List<BatterySchemeRule> rules = batterySchemeRuleMapper.selectList(wrapper);
        for (BatterySchemeRule rule : rules) {
            rule.setDeleted(1);
            batterySchemeRuleMapper.updateById(rule);
        }

        return true;
    }

    @Override
    public List<BusinessOptionVO> getOptions(String keyword) {
        // 查询启用的方案列表
        LambdaQueryWrapper<BatteryScheme> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BatteryScheme::getStatus, "1");
        wrapper.eq(BatteryScheme::getDeleted, 0);
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(BatteryScheme::getSchemeName, keyword);
        }
        wrapper.orderByAsc(BatteryScheme::getCreateTime);
        
        List<BatteryScheme> schemes = baseMapper.selectList(wrapper);
        
        return schemes.stream()
                .map(scheme -> {
                    BusinessOptionVO option = new BusinessOptionVO();
                    option.setId(scheme.getId());
                    option.setCode(scheme.getSchemeName());
                    option.setName(scheme.getSchemeName());
                    option.setStatus(scheme.getStatus());
                    return option;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BatterySchemeVerifyVO verify(BatterySchemeVerifyDTO dto) {
        // 1. 校验方案ID是否存在
        BatteryScheme scheme = baseMapper.selectById(dto.getSchemeId());
        if (scheme == null || scheme.getDeleted() == 1) {
            throw new BusinessException(404, "方案不存在");
        }

        // 2. 查询方案及规则数据
        List<BatterySchemeRule> rules = batterySchemeRuleMapper.selectBySchemeId(dto.getSchemeId());
        if (rules == null || rules.isEmpty()) {
            throw new BusinessException(
                BatterySchemeErrorCode.SCHEME_NO_RULES.getCode(),
                BatterySchemeErrorCode.SCHEME_NO_RULES.getMessage()
            );
        }

        // 3. 根据电压类型选择匹配字段
        BigDecimal inputVoltage = dto.getBatteryVoltage();
        String voltageType = dto.getVoltageType();
        
        // 4. 遍历规则列表，找到与输入电压最接近的规则项
        BatterySchemeRule matchedRule = null;
        BigDecimal minDiff = null;
        
        for (BatterySchemeRule rule : rules) {
            BigDecimal voltage = "STATIC".equals(voltageType) ? rule.getStaticVoltage() : rule.getLoadVoltage();
            BigDecimal diff = voltage.subtract(inputVoltage).abs();
            
            if (minDiff == null || diff.compareTo(minDiff) < 0) {
                minDiff = diff;
                matchedRule = rule;
            }
        }

        // 5. 返回该规则项的电量百分比和续航里程
        BatterySchemeVerifyVO result = new BatterySchemeVerifyVO();
        result.setBatteryPercent(matchedRule.getBatteryPercent());
        result.setMileage(matchedRule.getMileage());
        
        return result;
    }

    private void saveRules(Long schemeId, List<BatterySchemeSaveDTO.BatterySchemeRuleDTO> ruleDTOs) {
        if (ruleDTOs == null || ruleDTOs.isEmpty()) {
            return;
        }
        
        int sort = 0;
        for (BatterySchemeSaveDTO.BatterySchemeRuleDTO ruleDTO : ruleDTOs) {
            BatterySchemeRule rule = new BatterySchemeRule();
            rule.setSchemeId(schemeId);
            rule.setBatteryPercent(ruleDTO.getBatteryPercent());
            rule.setStaticVoltage(ruleDTO.getStaticVoltage());
            rule.setLoadVoltage(ruleDTO.getLoadVoltage());
            rule.setMileage(ruleDTO.getMileage());
            rule.setSort(sort++);
            batterySchemeRuleMapper.insert(rule);
        }
    }

    private void saveUpdateRules(Long schemeId, List<BatterySchemeUpdateDTO.BatterySchemeRuleDTO> ruleDTOs) {
        if (ruleDTOs == null || ruleDTOs.isEmpty()) {
            return;
        }
        
        int sort = 0;
        for (BatterySchemeUpdateDTO.BatterySchemeRuleDTO ruleDTO : ruleDTOs) {
            BatterySchemeRule rule = new BatterySchemeRule();
            rule.setSchemeId(schemeId);
            rule.setBatteryPercent(ruleDTO.getBatteryPercent());
            rule.setStaticVoltage(ruleDTO.getStaticVoltage());
            rule.setLoadVoltage(ruleDTO.getLoadVoltage());
            rule.setMileage(ruleDTO.getMileage());
            rule.setSort(sort++);
            batterySchemeRuleMapper.insert(rule);
        }
    }
}
