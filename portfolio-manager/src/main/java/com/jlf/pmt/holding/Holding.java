package com.jlf.pmt.holding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.UUID;

import com.jlf.pmt.asset.Asset;
import com.jlf.pmt.portfolio.Portfolio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	name = "holdings",
	schema = "pm",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "uq_portfolio_asset",
			columnNames = {"portfolio_id", "asset_id"}
		)
	}
)
public class Holding {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(
		name = "portfolio_id",
		nullable = false,
		foreignKey = @ForeignKey(name = "fk_holding_portfolio")
	)
	private Portfolio portfolio;
	
	@ManyToOne
	@JoinColumn(
		name = "asset_id",
		nullable = false,
		foreignKey = @ForeignKey(name = "fk_holding_asset"))
	private Asset asset;
	
	@Column(nullable = false, precision = 19, scale = 8)
	private BigDecimal quantity;
	
	@Column(nullable = false, precision = 19, scale = 4)
	private BigDecimal totalCost;
	
	@Column(nullable = false, precision = 19, scale = 4)
	private BigDecimal averageCost;
	
	@Column(nullable = false)
	private Instant addedAt;
	
	// not sure about this, revisit
	// not present in last convo
	public void addQuantity(BigDecimal amount, BigDecimal price) {
		totalCost = totalCost.add(price.multiply(amount));
		quantity = quantity.add(amount);
		averageCost = totalCost.divide(quantity, RoundingMode.HALF_UP);
		
		addedAt = Instant.now();
	}
	
	public void buy(BigDecimal quantity, BigDecimal price) {
		addQuantity(quantity, price);
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(BigDecimal averageCost) {
		this.averageCost = averageCost;
	}

	public Instant getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Instant addedAt) {
		this.addedAt = addedAt;
	}
}
