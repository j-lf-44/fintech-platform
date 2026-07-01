package com.jlf.pmt.portfolio;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jlf.pmt.portfolio.dto.CreatePortfolioRequest;
import com.jlf.pmt.portfolio.dto.PortfolioResponse;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
	
	private final PortfolioService service;
	
	public PortfolioController(PortfolioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<PortfolioResponse> createPortfolio(@RequestBody CreatePortfolioRequest request) {
		
		PortfolioResponse response = service.createPortfolio(request);
		
		// TODO add error handling
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PortfolioResponse> getPortfolio(@PathVariable UUID id) {
		
		PortfolioResponse response = service.getPortfolio(id);
		
		// TODO add error handling
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePortfolio(@PathVariable UUID id) {
		
		service.deletePortfolio(id);
		
		return ResponseEntity.ok("Successfully deleted Portfolio with id: '" + id + "'");
	}
	
	
	@GetMapping("/sample")
	public PortfolioResponse sample() {
		return service.getSamplePortfolio();
	}

}
