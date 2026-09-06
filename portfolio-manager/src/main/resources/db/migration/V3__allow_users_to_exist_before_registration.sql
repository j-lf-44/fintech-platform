-- =====================================================
-- USERS
-- =====================================================
ALTER TABLE pm.users
    ALTER COLUMN password_hash DROP NOT NULL;