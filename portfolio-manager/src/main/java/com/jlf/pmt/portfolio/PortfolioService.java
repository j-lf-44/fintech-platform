package com.jlf.pmt.portfolio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jlf.pmt.asset.Asset;
import com.jlf.pmt.holding.Holding;
import com.jlf.pmt.portfolio.dto.CreatePortfolioRequest;
import com.jlf.pmt.portfolio.dto.PortfolioResponse;
import com.jlf.pmt.portfolio.exceptions.PortfolioNotFoundException;
import com.jlf.pmt.portfolio.mapper.PortfolioMapper;

@Service
public class PortfolioService {
	
	private final PortfolioMapper mapper;
	private final PortfolioRepository repository;
	
	public PortfolioService(PortfolioMapper mapper, PortfolioRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}
	
	public PortfolioResponse createPortfolio(CreatePortfolioRequest request) {
		// TODO add validation
		
		Portfolio portfolio = mapper.toEntity(request);
		
		// call repository
		Portfolio savedPortfolio = repository.save(portfolio);
		
		return mapper.toResponse(savedPortfolio);
	}
	
	public PortfolioResponse getPortfolio(UUID id) {
		// if not found throws an Exception
		Portfolio portfolio = repository.findById(id)
				.orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found: " + id));
		
		return mapper.toResponse(portfolio);
	}
	
	public void deletePortfolio(UUID id) {
		repository.deleteById(id);
	}
	
	public PortfolioResponse getSamplePortfolio() {
		Portfolio sample = new Portfolio();
		
		sample.setId(UUID.randomUUID()); // random id (do i want to expose it?
		sample.setName("Sample portfolio 1");
		sample.setDescription("This is a sample portfolio with hardcoded mocked data");
		
		Asset asset1 = new Asset();
		asset1.setName("Sample Asset 1");
		asset1.setSymbol("SA1");
		
		Holding holding1 = new Holding();
		holding1.setAsset(asset1);
		holding1.setAverageCost(new BigDecimal(3.4));
		holding1.setAddedAt(Instant.now());
		holding1.setQuantity(new BigDecimal(10));
		holding1.setTotalCost(new BigDecimal(34));
		holding1.setPortfolio(sample);
		
		sample.addHolding(holding1);
		
		
		return mapper.toResponse(sample);
	}
 
}
