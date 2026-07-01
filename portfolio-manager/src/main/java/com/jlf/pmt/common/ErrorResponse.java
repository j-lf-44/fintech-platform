package com.jlf.pmt.common;

// TODO Extend and turn into record
public class ErrorResponse {
	
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
