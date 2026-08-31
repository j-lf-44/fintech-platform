-- =====================================================
-- SCHEMA
-- =====================================================
CREATE SCHEMA pm;


-- Enable UUID generation support
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =====================================================
-- USERS
-- =====================================================

CREATE TABLE pm.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    name VARCHAR(255) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE
);

-- =====================================================
-- ASSETS
-- =====================================================

CREATE TABLE pm.assets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    symbol VARCHAR(32) NOT NULL UNIQUE,

    name VARCHAR(255) NOT NULL
);

-- =====================================================
-- PORTFOLIOS
-- =====================================================

CREATE TABLE pm.portfolios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    name VARCHAR(255) NOT NULL,

    description TEXT
);

-- =====================================================
-- PORTFOLIO MEMBERSHIPS
-- =====================================================

CREATE TABLE pm.portfolio_memberships (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    portfolio_id UUID NOT NULL,

    user_id UUID NOT NULL,

    role VARCHAR(50),

	joined_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_portfolio_membership_portfolio
        FOREIGN KEY (portfolio_id)
        REFERENCES pm.portfolios(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_portfolio_membership_user
        FOREIGN KEY (user_id)
        REFERENCES pm.users(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_portfolio_user
        UNIQUE (portfolio_id, user_id)
);

-- =====================================================
-- HOLDINGS
-- =====================================================

CREATE TABLE pm.holdings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    portfolio_id UUID NOT NULL,

    asset_id UUID NOT NULL,

    quantity NUMERIC(19,8) NOT NULL,

    total_cost NUMERIC(19,4) NOT NULL,

    average_cost NUMERIC(19,4) NOT NULL,

    added_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_holding_portfolio
        FOREIGN KEY (portfolio_id)
        REFERENCES pm.portfolios(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_holding_asset
        FOREIGN KEY (asset_id)
        REFERENCES pm.assets(id),

    CONSTRAINT uq_portfolio_asset
        UNIQUE (portfolio_id, asset_id)
);

-- =====================================================
-- INDEXES
-- =====================================================

CREATE INDEX idx_holdings_portfolio_id
    ON pm.holdings(portfolio_id);

CREATE INDEX idx_holdings_asset_id
    ON pm.holdings(asset_id);

CREATE INDEX idx_portfolio_memberships_user_id
    ON pm.portfolio_memberships(user_id);

CREATE INDEX idx_portfolio_memberships_portfolio_id
    ON pm.portfolio_memberships(portfolio_id);

CREATE INDEX idx_assets_symbol
    ON pm.assets(symbol);

CREATE INDEX idx_users_email
    ON pm.users(email);
