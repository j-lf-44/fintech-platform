package com.jlf.pmt.holding.mapper;

import org.springframework.stereotype.Component;

import com.jlf.pmt.holding.Holding;
import com.jlf.pmt.holding.dto.HoldingDto;

@Component
public class HoldingMapper {
	
	public HoldingDto toDto(Holding entity) {
		return new HoldingDto(
				entity.getAsset().getSymbol(), // TODO add mapper
				entity.getQuantity(), // quantity
				entity.getAverageCost(), // average cost
				entity.getTotalCost() // market value
				);
	}

}
