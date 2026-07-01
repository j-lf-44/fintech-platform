package com.jlf.pmt.portfolio.exceptions;

public class PortfolioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PortfolioNotFoundException(String message) {
		super(message);
	}

}
