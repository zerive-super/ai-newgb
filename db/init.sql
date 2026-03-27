-- ============================================================
-- 数据库初始化脚本
-- 生成时间: 2026-03-27
-- 数据库类型: PostgreSQL
-- 模块: 电池方案管理 + 使用帮助管理 + 移动端使用帮助 + 移动端电池数据选择
-- ============================================================

-- ============================================================
-- 第一部分：建表语句
-- ============================================================

-- -----------------------------------------------------------
-- 表: battery_scheme (电池方案主表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS battery_scheme (
    id BIGSERIAL PRIMARY KEY,
    scheme_name VARCHAR(100) NOT NULL,
    battery_voltage DECIMAL(10,2) NOT NULL,
    battery_type VARCHAR(20) NOT NULL,
    description VARCHAR(500),
    status CHAR(1) NOT NULL DEFAULT '1',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE battery_scheme IS '电池方案主表';
COMMENT ON COLUMN battery_scheme.id IS '主键ID';
COMMENT ON COLUMN battery_scheme.scheme_name IS '方案名称';
COMMENT ON COLUMN battery_scheme.battery_voltage IS '电池电压参数（V）';
COMMENT ON COLUMN battery_scheme.battery_type IS '电池类型编码';
COMMENT ON COLUMN battery_scheme.description IS '方案描述';
COMMENT ON COLUMN battery_scheme.status IS '状态（1:启用 0:停用）';
COMMENT ON COLUMN battery_scheme.create_time IS '创建时间';
COMMENT ON COLUMN battery_scheme.update_time IS '更新时间';
COMMENT ON COLUMN battery_scheme.create_by IS '创建人ID';
COMMENT ON COLUMN battery_scheme.update_by IS '更新人ID';
COMMENT ON COLUMN battery_scheme.deleted IS '逻辑删除标记';

-- -----------------------------------------------------------
-- 表: battery_scheme_rule (电池方案规则表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS battery_scheme_rule (
    id BIGSERIAL PRIMARY KEY,
    scheme_id BIGINT NOT NULL,
    battery_percent INTEGER NOT NULL,
    static_voltage DECIMAL(10,2) NOT NULL,
    load_voltage DECIMAL(10,2) NOT NULL,
    mileage DECIMAL(10,2) NOT NULL,
    sort INTEGER NOT NULL DEFAULT 0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE battery_scheme_rule IS '电池方案规则表';
COMMENT ON COLUMN battery_scheme_rule.id IS '主键ID';
COMMENT ON COLUMN battery_scheme_rule.scheme_id IS '关联电池方案ID';
COMMENT ON COLUMN battery_scheme_rule.battery_percent IS '电量百分比（0-100）';
COMMENT ON COLUMN battery_scheme_rule.static_voltage IS '静置电压值（V）';
COMMENT ON COLUMN battery_scheme_rule.load_voltage IS '负载电压值（V）';
COMMENT ON COLUMN battery_scheme_rule.mileage IS '续航里程（km）';
COMMENT ON COLUMN battery_scheme_rule.sort IS '排序号';
COMMENT ON COLUMN battery_scheme_rule.create_time IS '创建时间';
COMMENT ON COLUMN battery_scheme_rule.update_time IS '更新时间';
COMMENT ON COLUMN battery_scheme_rule.deleted IS '逻辑删除标记';

-- -----------------------------------------------------------
-- 表: sys_dict_type (字典类型表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS sys_dict_type (
    id BIGSERIAL PRIMARY KEY,
    dict_name VARCHAR(100) NOT NULL,
    dict_code VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    sort INTEGER NOT NULL DEFAULT 0,
    status CHAR(1) NOT NULL DEFAULT '1',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE sys_dict_type IS '字典类型表';
COMMENT ON COLUMN sys_dict_type.id IS '主键ID';
COMMENT ON COLUMN sys_dict_type.dict_name IS '字典类型名称';
COMMENT ON COLUMN sys_dict_type.dict_code IS '字典编码';
COMMENT ON COLUMN sys_dict_type.description IS '字典描述';
COMMENT ON COLUMN sys_dict_type.sort IS '排序号';
COMMENT ON COLUMN sys_dict_type.status IS '状态（1:启用 0:停用）';
COMMENT ON COLUMN sys_dict_type.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_type.update_time IS '更新时间';

-- -----------------------------------------------------------
-- 表: sys_dict_data (字典数据表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS sys_dict_data (
    id BIGSERIAL PRIMARY KEY,
    dict_type_id BIGINT NOT NULL,
    data_label VARCHAR(100) NOT NULL,
    data_value VARCHAR(100) NOT NULL,
    data_code VARCHAR(50),
    sort INTEGER NOT NULL DEFAULT 0,
    status CHAR(1) NOT NULL DEFAULT '1',
    parent_id BIGINT NOT NULL DEFAULT 0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE sys_dict_data IS '字典数据表';
COMMENT ON COLUMN sys_dict_data.id IS '主键ID';
COMMENT ON COLUMN sys_dict_data.dict_type_id IS '关联字典类型';
COMMENT ON COLUMN sys_dict_data.data_label IS '数据标签';
COMMENT ON COLUMN sys_dict_data.data_value IS '数据值';
COMMENT ON COLUMN sys_dict_data.data_code IS '类型编码';
COMMENT ON COLUMN sys_dict_data.sort IS '排序号';
COMMENT ON COLUMN sys_dict_data.status IS '状态（1:启用 0:停用）';
COMMENT ON COLUMN sys_dict_data.parent_id IS '父级ID（用于级联）';
COMMENT ON COLUMN sys_dict_data.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_data.update_time IS '更新时间';

-- -----------------------------------------------------------
-- 表: help_doc (帮助文档主表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS help_doc (
    id BIGSERIAL PRIMARY KEY,
    function_type VARCHAR(50) NOT NULL,
    title VARCHAR(100) NOT NULL,
    sort INTEGER NOT NULL DEFAULT 0,
    description VARCHAR(500) NOT NULL,
    status CHAR(1) NOT NULL DEFAULT '1',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE help_doc IS '帮助文档主表';
COMMENT ON COLUMN help_doc.id IS '主键ID';
COMMENT ON COLUMN help_doc.function_type IS '功能类型编码';
COMMENT ON COLUMN help_doc.title IS '文档标题';
COMMENT ON COLUMN help_doc.sort IS '排序号';
COMMENT ON COLUMN help_doc.description IS '功能简要描述';
COMMENT ON COLUMN help_doc.status IS '状态（1:启用 0:停用）';
COMMENT ON COLUMN help_doc.create_time IS '创建时间';
COMMENT ON COLUMN help_doc.update_time IS '更新时间';
COMMENT ON COLUMN help_doc.create_by IS '创建人ID';
COMMENT ON COLUMN help_doc.update_by IS '更新人ID';
COMMENT ON COLUMN help_doc.deleted IS '逻辑删除标记';

-- -----------------------------------------------------------
-- 表: help_doc_content (帮助文档说明内容表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS help_doc_content (
    id BIGSERIAL PRIMARY KEY,
    doc_id BIGINT NOT NULL,
    sort INTEGER NOT NULL DEFAULT 0,
    content TEXT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE help_doc_content IS '帮助文档说明内容表';
COMMENT ON COLUMN help_doc_content.id IS '主键ID';
COMMENT ON COLUMN help_doc_content.doc_id IS '关联帮助文档ID';
COMMENT ON COLUMN help_doc_content.sort IS '内容排序号';
COMMENT ON COLUMN help_doc_content.content IS '详细说明文字';
COMMENT ON COLUMN help_doc_content.image_url IS '配图URL';
COMMENT ON COLUMN help_doc_content.create_time IS '创建时间';
COMMENT ON COLUMN help_doc_content.update_time IS '更新时间';
COMMENT ON COLUMN help_doc_content.deleted IS '逻辑删除标记';

-- -----------------------------------------------------------
-- 表: vehicle_battery_config (车辆电池配置表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS vehicle_battery_config (
    id BIGSERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    battery_type VARCHAR(20) NOT NULL,
    battery_voltage INTEGER NOT NULL,
    scheme_id BIGINT NOT NULL,
    sync_status CHAR(1) NOT NULL DEFAULT '0',
    sync_time TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE vehicle_battery_config IS '车辆电池配置表';
COMMENT ON COLUMN vehicle_battery_config.id IS '主键ID';
COMMENT ON COLUMN vehicle_battery_config.vehicle_id IS '关联车辆ID';
COMMENT ON COLUMN vehicle_battery_config.battery_type IS '电池类型编码';
COMMENT ON COLUMN vehicle_battery_config.battery_voltage IS '电池电压值（V）';
COMMENT ON COLUMN vehicle_battery_config.scheme_id IS '关联电池方案ID';
COMMENT ON COLUMN vehicle_battery_config.sync_status IS '同步状态（0:待同步 1:已同步 2:同步失败）';
COMMENT ON COLUMN vehicle_battery_config.sync_time IS '同步时间';
COMMENT ON COLUMN vehicle_battery_config.create_time IS '创建时间';
COMMENT ON COLUMN vehicle_battery_config.update_time IS '更新时间';
COMMENT ON COLUMN vehicle_battery_config.create_by IS '创建人ID';
COMMENT ON COLUMN vehicle_battery_config.update_by IS '更新人ID';
COMMENT ON COLUMN vehicle_battery_config.deleted IS '逻辑删除标记';

-- -----------------------------------------------------------
-- 表: battery_config_log (电池配置变更日志表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS battery_config_log (
    id BIGSERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    operation_type VARCHAR(20) NOT NULL,
    before_data JSONB,
    after_data JSONB NOT NULL,
    operator_id BIGINT NOT NULL,
    operation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE battery_config_log IS '电池配置变更日志表';
COMMENT ON COLUMN battery_config_log.id IS '主键ID';
COMMENT ON COLUMN battery_config_log.vehicle_id IS '关联车辆ID';
COMMENT ON COLUMN battery_config_log.operation_type IS '操作类型（CREATE/UPDATE）';
COMMENT ON COLUMN battery_config_log.before_data IS '变更前数据（JSON）';
COMMENT ON COLUMN battery_config_log.after_data IS '变更后数据（JSON）';
COMMENT ON COLUMN battery_config_log.operator_id IS '操作人ID';
COMMENT ON COLUMN battery_config_log.operation_time IS '操作时间';
COMMENT ON COLUMN battery_config_log.create_time IS '创建时间';
COMMENT ON COLUMN battery_config_log.update_time IS '更新时间';
COMMENT ON COLUMN battery_config_log.deleted IS '逻辑删除标记';

-- ============================================================
-- 第二部分：索引语句
-- ============================================================

-- battery_scheme 索引
CREATE UNIQUE INDEX uk_battery_scheme_scheme_name ON battery_scheme(scheme_name) WHERE deleted = 0;
CREATE INDEX idx_battery_scheme_battery_type ON battery_scheme(battery_type);

-- battery_scheme_rule 索引
CREATE INDEX idx_battery_scheme_rule_scheme_id ON battery_scheme_rule(scheme_id);
CREATE INDEX idx_battery_scheme_rule_battery_percent ON battery_scheme_rule(battery_percent);

-- sys_dict_type 索引
CREATE UNIQUE INDEX uk_sys_dict_type_dict_code ON sys_dict_type(dict_code);

-- sys_dict_data 索引
CREATE INDEX idx_sys_dict_data_dict_type_id ON sys_dict_data(dict_type_id);

-- help_doc 索引
CREATE INDEX idx_help_doc_function_type ON help_doc(function_type);
CREATE INDEX idx_help_doc_sort ON help_doc(sort);

-- help_doc_content 索引
CREATE INDEX idx_help_doc_content_doc_id ON help_doc_content(doc_id);

-- vehicle_battery_config 索引
CREATE UNIQUE INDEX uk_vehicle_battery_config_vehicle_id ON vehicle_battery_config(vehicle_id) WHERE deleted = 0;
CREATE INDEX idx_vehicle_battery_config_scheme_id ON vehicle_battery_config(scheme_id);

-- battery_config_log 索引
CREATE INDEX idx_battery_config_log_vehicle_id ON battery_config_log(vehicle_id);
CREATE INDEX idx_battery_config_log_operation_time ON battery_config_log(operation_time);

-- ============================================================
-- 第三部分：初始化数据
-- ============================================================

-- -----------------------------------------------------------
-- 字典类型数据
-- -----------------------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('电池类型', 'battery_type', '电池类型字典', 1, '1')
ON CONFLICT (dict_code) DO NOTHING;

INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('电压类型', 'voltage_type', '电压类型字典', 2, '1')
ON CONFLICT (dict_code) DO NOTHING;

INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('功能类型', 'help_function_type', '帮助文档功能类型字典', 3, '1')
ON CONFLICT (dict_code) DO NOTHING;

INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('移动端功能类型', 'app_help_function_type', '移动端帮助文档功能类型字典', 10, '1')
ON CONFLICT (dict_code) DO NOTHING;

INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('移动端电池类型', 'app_battery_type', '移动端电池类型字典', 11, '1')
ON CONFLICT (dict_code) DO NOTHING;

INSERT INTO sys_dict_type (dict_name, dict_code, description, sort, status)
VALUES ('移动端电池电压', 'app_battery_voltage', '移动端电池电压字典', 12, '1')
ON CONFLICT (dict_code) DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 电池类型字典
-- -----------------------------------------------------------
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '锂电池', 'LITHIUM', 'LITHIUM', 1, '1'
FROM sys_dict_type WHERE dict_code = 'battery_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '铅酸电池', 'LEAD_ACID', 'LEAD_ACID', 2, '1'
FROM sys_dict_type WHERE dict_code = 'battery_type'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 电压类型字典
-- -----------------------------------------------------------
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '静置电压', 'STATIC', 'STATIC', 1, '1'
FROM sys_dict_type WHERE dict_code = 'voltage_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '负载电压', 'LOAD', 'LOAD', 2, '1'
FROM sys_dict_type WHERE dict_code = 'voltage_type'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 功能类型字典（管理端）
-- -----------------------------------------------------------
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '车辆管理', 'VEHICLE_MANAGE', 'VEHICLE_MANAGE', 1, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '终端型号管理', 'TERMINAL_MODEL', 'TERMINAL_MODEL', 2, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '终端管理', 'TERMINAL', 'TERMINAL', 3, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '资产列表', 'ASSET', 'ASSET', 4, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '报警明细', 'ALARM', 'ALARM', 5, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '电池方案管理', 'BATTERY_SCHEME', 'BATTERY_SCHEME', 6, '1'
FROM sys_dict_type WHERE dict_code = 'help_function_type'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 移动端功能类型字典
-- -----------------------------------------------------------
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '车辆管理', 'VEHICLE_MANAGE', 'VEHICLE_MANAGE', 1, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '电池配置', 'BATTERY_CONFIG', 'BATTERY_CONFIG', 2, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '报警提醒', 'ALARM', 'ALARM', 3, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '用车人管理', 'SHARE', 'SHARE', 4, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '车辆位置', 'LOCATION', 'LOCATION', 5, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '骑行轨迹', 'RIDING', 'RIDING', 6, '1'
FROM sys_dict_type WHERE dict_code = 'app_help_function_type'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 移动端电池类型字典
-- -----------------------------------------------------------
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '锂电池', 'LITHIUM', 'LITHIUM', 1, '1'
FROM sys_dict_type WHERE dict_code = 'app_battery_type'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status)
SELECT id, '铅酸电池', 'LEAD_ACID', 'LEAD_ACID', 2, '1'
FROM sys_dict_type WHERE dict_code = 'app_battery_type'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 字典数据 - 移动端电池电压字典（带父级值）
-- -----------------------------------------------------------
-- 锂电池电压选项
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status, parent_id)
SELECT dt.id, '48V', '48', '48', 1, '1',
    (SELECT id FROM sys_dict_type WHERE dict_code = 'app_battery_type' LIMIT 1)
FROM sys_dict_type dt WHERE dt.dict_code = 'app_battery_voltage'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status, parent_id)
SELECT dt.id, '60V', '60', '60', 2, '1',
    (SELECT id FROM sys_dict_type WHERE dict_code = 'app_battery_type' LIMIT 1)
FROM sys_dict_type dt WHERE dt.dict_code = 'app_battery_voltage'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status, parent_id)
SELECT dt.id, '72V', '72', '72', 3, '1',
    (SELECT id FROM sys_dict_type WHERE dict_code = 'app_battery_type' LIMIT 1)
FROM sys_dict_type dt WHERE dt.dict_code = 'app_battery_voltage'
ON CONFLICT DO NOTHING;

-- 铅酸电池电压选项
INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status, parent_id)
SELECT dt.id, '48V', '48', '48', 1, '1',
    (SELECT id FROM sys_dict_type WHERE dict_code = 'app_battery_type' LIMIT 1)
FROM sys_dict_type dt WHERE dt.dict_code = 'app_battery_voltage'
ON CONFLICT DO NOTHING;

INSERT INTO sys_dict_data (dict_type_id, data_label, data_value, data_code, sort, status, parent_id)
SELECT dt.id, '60V', '60', '60', 2, '1',
    (SELECT id FROM sys_dict_type WHERE dict_code = 'app_battery_type' LIMIT 1)
FROM sys_dict_type dt WHERE dt.dict_code = 'app_battery_voltage'
ON CONFLICT DO NOTHING;

-- -----------------------------------------------------------
-- 模拟数据 - 移动端帮助文档
-- -----------------------------------------------------------
INSERT INTO help_doc (function_type, title, sort, description, status) VALUES
('VEHICLE_MANAGE', '如何新增车辆', 1, '介绍车辆新增的操作步骤', '1'),
('VEHICLE_MANAGE', '车辆绑定说明', 2, '如何在小程序中绑定车辆', '1'),
('BATTERY_CONFIG', '电池配置使用指南', 3, '如何配置车辆电池参数', '1'),
('ALARM', '报警提醒设置', 4, '如何设置和查看报警提醒', '1'),
('SHARE', '用车人管理', 5, '如何添加和管理用车人', '1'),
('LOCATION', '车辆位置查看', 6, '如何在小程序中查看车辆位置', '1'),
('RIDING', '骑行轨迹查询', 7, '如何查看骑行轨迹记录', '1')
ON CONFLICT DO NOTHING;

INSERT INTO help_doc_content (doc_id, sort, content, image_url) VALUES
(1, 1, '点击右上角【新增】按钮，打开新增车辆弹窗', 'https://example.com/images/step1.png'),
(1, 2, '填写车辆基本信息，包括车辆名称、设备编号等必填字段', 'https://example.com/images/step2.png'),
(1, 3, '点击【保存】按钮完成车辆新增', 'https://example.com/images/step3.png'),
(2, 1, '进入车辆列表，点击需要绑定的车辆', 'https://example.com/images/bind1.png'),
(2, 2, '点击【绑定车辆】按钮', 'https://example.com/images/bind2.png'),
(2, 3, '输入设备序列号完成绑定', 'https://example.com/images/bind3.png'),
(3, 1, '在电池配置页面选择电池类型', 'https://example.com/images/bat1.png'),
(3, 2, '选择对应的电池电压', 'https://example.com/images/bat2.png'),
(3, 3, '选择合适的电池方案', 'https://example.com/images/bat3.png'),
(3, 4, '点击保存完成配置', 'https://example.com/images/bat4.png')
ON CONFLICT DO NOTHING;
