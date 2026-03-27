-- ============================================================
-- Patch: 用户-车辆绑定 + 车辆-电池方案绑定
-- Date: 2026-03-27
-- DB: PostgreSQL
-- ============================================================

-- -----------------------------------------------------------
-- 表: user_vehicle (用户-车辆绑定表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS user_vehicle (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    user_account VARCHAR(100),
    vehicle_id BIGINT,
    imei VARCHAR(32) NOT NULL,
    bind_status CHAR(1) NOT NULL DEFAULT '1',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE user_vehicle IS '用户-车辆绑定表';
COMMENT ON COLUMN user_vehicle.user_id IS '用户ID';
COMMENT ON COLUMN user_vehicle.user_account IS '用户账号';
COMMENT ON COLUMN user_vehicle.vehicle_id IS '车辆ID';
COMMENT ON COLUMN user_vehicle.imei IS 'IMEI';
COMMENT ON COLUMN user_vehicle.bind_status IS '绑定状态：1-已绑定，0-已解绑';

CREATE UNIQUE INDEX IF NOT EXISTS uk_user_vehicle_user_imei
    ON user_vehicle(user_id, imei) WHERE deleted = 0;
CREATE INDEX IF NOT EXISTS idx_user_vehicle_imei
    ON user_vehicle(imei);

-- -----------------------------------------------------------
-- 表: vehicle_battery_scheme (车辆-电池方案绑定表)
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS vehicle_battery_scheme (
    id BIGSERIAL PRIMARY KEY,
    imei VARCHAR(32) NOT NULL,
    scheme_id BIGINT NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    deleted SMALLINT NOT NULL DEFAULT 0
);

COMMENT ON TABLE vehicle_battery_scheme IS '车辆-电池方案绑定表';
COMMENT ON COLUMN vehicle_battery_scheme.imei IS '设备IMEI';
COMMENT ON COLUMN vehicle_battery_scheme.scheme_id IS '电池方案ID';

CREATE UNIQUE INDEX IF NOT EXISTS uk_vehicle_battery_scheme_imei
    ON vehicle_battery_scheme(imei) WHERE deleted = 0;
CREATE INDEX IF NOT EXISTS idx_vehicle_battery_scheme_scheme_id
    ON vehicle_battery_scheme(scheme_id);

