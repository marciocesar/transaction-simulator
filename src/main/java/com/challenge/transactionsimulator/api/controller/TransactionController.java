package com.challenge.transactionsimulator.api.controller;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static com.challenge.transactionsimulator.api.controller.ApiPath.SIMULATED_TRANSACTIONS_LIST_RESOURCE;
import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionsService;
	
	@GetMapping(SIMULATED_TRANSACTIONS_LIST_RESOURCE)
	public ResponseEntity<List<SimulatedResponseTransactionDto>> getList(
			@Min(1000) @Max(100000) @PathVariable("id") Integer id,
			@Min(1) @Max(9999) @PathVariable("year") Integer year,
			@Min(1) @Max(12) @PathVariable("month") Integer month) {
		
		return ok(transactionsService.getSimulatedList(id, year, month));
	}
}
