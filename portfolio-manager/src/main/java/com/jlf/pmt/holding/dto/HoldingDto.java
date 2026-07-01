package com.jlf.pmt.holding.dto;

import java.math.BigDecimal;

public record HoldingDto(
		String symbol,
		BigDecimal quantity,
		BigDecimal averageCost,
		BigDecimal marketValue
) {}
