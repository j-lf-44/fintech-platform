package com.jlf.pmt.portfolio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jlf.pmt.holding.Holding;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity()
@Table(name = "portfolios", schema = "pm")
public class Portfolio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	private String description;
	
	@OneToMany(
		mappedBy = "portfolio", 
		cascade = CascadeType.ALL, 
		orphanRemoval = true
	)
	private List<PortfolioMembership> memberships = new ArrayList<>();
	
	@OneToMany(
		mappedBy = "portfolio", 
		cascade = CascadeType.ALL, 
		orphanRemoval = true
	)
	private List<Holding> holdings = new ArrayList<>();
	
    public void addMembership(PortfolioMembership membership) {
        memberships.add(membership);
        membership.setPortfolio(this);
    }

    public void removeMembership(PortfolioMembership membership) {
        memberships.remove(membership);
        membership.setPortfolio(null);
    }
	
	public void addHolding(Holding holding) {
		holdings.add(holding);
		holding.setPortfolio(this);
		holding.setAddedAt(Instant.now());
	}
	
	public void removeHolding(Holding holding) {
		holdings.remove(holding);
		holding.setPortfolio(null);
	}
	
	public BigDecimal totalValue(Map<String, BigDecimal> prices) {
		return holdings.stream()
				.map(h -> h.getQuantity()
						.multiply(prices.get(h.getAsset().getSymbol())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<PortfolioMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<PortfolioMembership> memberships) {
		this.memberships = memberships;
	}

	public List<Holding> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

}
