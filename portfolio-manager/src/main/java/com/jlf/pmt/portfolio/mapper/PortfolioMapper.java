package com.jlf.pmt.portfolio.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jlf.pmt.holding.dto.HoldingDto;
import com.jlf.pmt.holding.mapper.HoldingMapper;
import com.jlf.pmt.portfolio.Portfolio;
import com.jlf.pmt.portfolio.dto.CreatePortfolioRequest;
import com.jlf.pmt.portfolio.dto.PortfolioResponse;

// TODO move to MapStruct
@Component
public class PortfolioMapper {
	
	private final HoldingMapper holdingMapper;
	
	public PortfolioMapper (HoldingMapper holdingMapper) {
		this.holdingMapper = holdingMapper;
	};
	
	public Portfolio toEntity(CreatePortfolioRequest request) {
		
		Portfolio portfolio = new Portfolio();
		
		portfolio.setName(request.getName());
		portfolio.setDescription(request.getDescription());
		
		return portfolio;
		
	}
	
	public PortfolioResponse toResponse(Portfolio entity) {
		
		List<HoldingDto> holdingDtos = entity.getHoldings().stream()
					.map(holdingMapper::toDto)
					.toList();
		
		return new PortfolioResponse(
				entity.getId(), 
				entity.getName(), 
				entity.getDescription(),
				holdingDtos, 
				new BigDecimal("12345") //entity.totalValue(new HashMap<>()) // list of prices
				);
	}

}
