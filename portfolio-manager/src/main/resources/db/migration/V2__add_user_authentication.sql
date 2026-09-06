-- =====================================================
-- USERS
-- =====================================================
ALTER TABLE pm.users
    ADD COLUMN password_hash VARCHAR(255) NOT NULL,
    ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;