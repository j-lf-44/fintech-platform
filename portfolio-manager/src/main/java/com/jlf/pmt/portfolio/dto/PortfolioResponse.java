package com.jlf.pmt.portfolio.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.jlf.pmt.holding.dto.HoldingDto;

public record PortfolioResponse(
		UUID id,
		String name,
		String description,
		List<HoldingDto> holdings,
		BigDecimal totalValue
){}
