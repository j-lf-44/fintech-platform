package com.jlf.pmt.portfolio;

import java.time.Instant;
import java.util.UUID;

import com.jlf.pmt.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	name = "portfolio_memberships", 
	schema = "pm",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "uq_portfolio_user",
			columnNames = {"portfolio_id", "user_id"}
		)
	}
)
public class PortfolioMembership {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(optional = false)
	@JoinColumn(
		name = "user_id",
		nullable = false,
		foreignKey = @ForeignKey(name = "fk_portfolio_membership_user"))
	private User user;
	
	@ManyToOne(optional = false)
	@JoinColumn(
		name = "portfolio_id",
		nullable = false,
		foreignKey = @ForeignKey(name = "fk_portfolio_membership_portfolio"))
	private Portfolio portfolio;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private Instant joinedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Instant getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}
}
