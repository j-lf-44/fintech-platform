package com.jlf.pmt.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jlf.pmt.portfolio.PortfolioMembership;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", schema = "pm")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@OneToMany(
		mappedBy = "user",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<PortfolioMembership> memberships = new ArrayList<>();
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PortfolioMembership> getMemberships() {
		return memberships;
	}
	
	public void addMembership(PortfolioMembership membership) {
        memberships.add(membership);
        membership.setUser(this);
    }

    public void removeMembership(PortfolioMembership membership) {
        memberships.remove(membership);
        membership.setUser(null);
    }
}
