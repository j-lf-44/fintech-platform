package com.jlf.pmt.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jlf.pmt.common.ErrorResponse;
import com.jlf.pmt.portfolio.exceptions.PortfolioNotFoundException;

@RestControllerAdvice
public class PortfolioExceptionHandler {
	
	@ExceptionHandler(PortfolioNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(PortfolioNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
	}

}
