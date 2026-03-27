-- ============================================================
-- Mock 数据脚本
-- 生成时间: 2026-03-27
-- 模块: 电池方案管理 + 使用帮助管理
-- ============================================================

-- -----------------------------------------------------------
-- Mock 数据: 电池方案
-- -----------------------------------------------------------

-- 插入电池方案数据
INSERT INTO battery_scheme (id, scheme_name, battery_voltage, battery_type, description, status, create_time, update_time, deleted) VALUES
(1, '铅酸60V标准方案', 60.00, 'LEAD_ACID', '标准铅酸电池方案，适用于大部分车型', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, '锂电48V超能方案', 48.00, 'LITHIUM', '高性能锂电池方案，续航更长', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, '锂电60V长续航方案', 60.00, 'LITHIUM', '长续航锂电池方案，适合远距离行驶', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入电池方案规则数据（铅酸60V标准方案）
INSERT INTO battery_scheme_rule (scheme_id, battery_percent, static_voltage, load_voltage, mileage, sort, create_time, update_time, deleted) VALUES
(1, 100, 65.50, 58.00, 60.0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 90, 63.00, 55.50, 54.0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 80, 61.00, 53.50, 48.0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 70, 59.00, 51.50, 42.0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 60, 57.00, 49.50, 36.0, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 50, 55.00, 47.50, 30.0, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 40, 53.00, 45.50, 24.0, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 30, 51.00, 43.50, 18.0, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 20, 49.00, 41.50, 12.0, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 10, 47.00, 39.50, 6.0, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入电池方案规则数据（锂电48V超能方案）
INSERT INTO battery_scheme_rule (scheme_id, battery_percent, static_voltage, load_voltage, mileage, sort, create_time, update_time, deleted) VALUES
(2, 100, 54.60, 46.00, 80.0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 90, 52.50, 44.00, 72.0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 80, 50.50, 42.00, 64.0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 70, 48.50, 40.00, 56.0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 60, 46.50, 38.00, 48.0, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 50, 44.50, 36.00, 40.0, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 40, 42.50, 34.00, 32.0, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 30, 40.50, 32.00, 24.0, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 20, 38.50, 30.00, 16.0, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 10, 36.50, 28.00, 8.0, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入电池方案规则数据（锂电60V长续航方案）
INSERT INTO battery_scheme_rule (scheme_id, battery_percent, static_voltage, load_voltage, mileage, sort, create_time, update_time, deleted) VALUES
(3, 100, 67.20, 58.00, 100.0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 90, 65.00, 56.00, 90.0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 80, 62.80, 54.00, 80.0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 70, 60.60, 52.00, 70.0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 60, 58.40, 50.00, 60.0, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 50, 56.20, 48.00, 50.0, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 40, 54.00, 46.00, 40.0, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 30, 51.80, 44.00, 30.0, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 20, 49.60, 42.00, 20.0, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 10, 47.40, 40.00, 10.0, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- -----------------------------------------------------------
-- Mock 数据: 帮助文档
-- -----------------------------------------------------------

-- 插入帮助文档数据
INSERT INTO help_doc (id, function_type, title, sort, description, status, create_time, update_time, deleted) VALUES
(1, 'VEHICLE_MANAGE', '如何新增车辆', 1, '介绍车辆新增的操作步骤', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 'VEHICLE_MANAGE', '如何编辑车辆信息', 2, '介绍车辆信息编辑的操作步骤', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 'TERMINAL', '终端绑定操作指南', 1, '介绍终端设备绑定的操作流程', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(4, 'BATTERY_SCHEME', '电池方案配置说明', 1, '介绍电池方案配置的方法和注意事项', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(5, 'ASSET', '资产列表使用指南', 1, '介绍资产列表查询和筛选功能', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入帮助文档内容数据（如何新增车辆）
INSERT INTO help_doc_content (doc_id, sort, content, image_url, create_time, update_time, deleted) VALUES
(1, 1, '点击右上角【新增】按钮，打开新增车辆弹窗', 'https://example.com/images/vehicle/step1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 2, '填写车辆基本信息，包括车辆名称、设备编号等必填字段', 'https://example.com/images/vehicle/step2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 3, '选择电池方案并绑定终端设备（如需）', 'https://example.com/images/vehicle/step3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(1, 4, '点击【保存】按钮完成车辆新增', 'https://example.com/images/vehicle/step4.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入帮助文档内容数据（如何编辑车辆信息）
INSERT INTO help_doc_content (doc_id, sort, content, image_url, create_time, update_time, deleted) VALUES
(2, 1, '在车辆列表中找到需要编辑的车辆，点击【编辑】按钮', 'https://example.com/images/vehicle/edit_step1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 2, '修改需要更新的车辆信息', 'https://example.com/images/vehicle/edit_step2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(2, 3, '点击【保存】按钮完成车辆信息更新', 'https://example.com/images/vehicle/edit_step3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入帮助文档内容数据（终端绑定操作指南）
INSERT INTO help_doc_content (doc_id, sort, content, image_url, create_time, update_time, deleted) VALUES
(3, 1, '在车辆详情页面点击【绑定终端】按钮', 'https://example.com/images/terminal/bind_step1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 2, '输入终端设备编号或扫描终端二维码', 'https://example.com/images/terminal/bind_step2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(3, 3, '确认绑定信息无误后点击【确定】', 'https://example.com/images/terminal/bind_step3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入帮助文档内容数据（电池方案配置说明）
INSERT INTO help_doc_content (doc_id, sort, content, image_url, create_time, update_time, deleted) VALUES
(4, 1, '进入电池方案管理页面，点击【新增】创建新方案', 'https://example.com/images/battery/scheme_step1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(4, 2, '填写方案基本信息：方案名称、电池电压、电池类型', 'https://example.com/images/battery/scheme_step2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(4, 3, '配置电量与电压对应规则（0%-100%，每10%一条）', 'https://example.com/images/battery/scheme_step3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(4, 4, '保存方案后可对电压进行验证计算', 'https://example.com/images/battery/scheme_step4.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- 插入帮助文档内容数据（资产列表使用指南）
INSERT INTO help_doc_content (doc_id, sort, content, image_url, create_time, update_time, deleted) VALUES
(5, 1, '在资产列表页面可以通过搜索框快速查找资产', 'https://example.com/images/asset/list_step1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(5, 2, '使用筛选功能按状态、类型等条件过滤资产', 'https://example.com/images/asset/list_step2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
(5, 3, '点击资产行可查看资产详情和历史记录', 'https://example.com/images/asset/list_step3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
