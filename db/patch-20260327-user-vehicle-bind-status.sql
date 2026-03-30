-- ============================================================
-- Patch: user_vehicle bind_status
-- Date: 2026-03-27
-- DB: PostgreSQL
-- ============================================================

-- 绑定状态：1-已绑定，0-已解绑
ALTER TABLE IF EXISTS user_vehicle
    ADD COLUMN IF NOT EXISTS bind_status CHAR(1) NOT NULL DEFAULT '1';

COMMENT ON COLUMN user_vehicle.bind_status IS '绑定状态：1-已绑定，0-已解绑';

-- 存量数据兜底：为空则按已绑定处理
UPDATE user_vehicle
   SET bind_status = '1'
 WHERE bind_status IS NULL;

