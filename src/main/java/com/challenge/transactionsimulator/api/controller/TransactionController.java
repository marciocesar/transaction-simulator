package com.challenge.transactionsimulator.api.controller;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.service.TransactionService;
import com.challenge.transactionsimulator.api.simulator.SimulationEngine;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.challenge.transactionsimulator.api.controller.ApiPath.SIMULATED_TRANSACTIONS_LIST_RESOURCE;
import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
public class TransactionController {
	
	private static final String MESSAGE = "Deve ser um valor entre {min} and {max}";
	
	@Autowired
	private TransactionService transactionsService;
	
	@GetMapping(SIMULATED_TRANSACTIONS_LIST_RESOURCE)
	public ResponseEntity<List<SimulatedResponseTransactionDto>> list(
			@Range(min = 1000, max = 100000, message = MESSAGE) @PathVariable String id,
			@Range(min = 1, max = 9999, message = MESSAGE) @PathVariable String year,
			@Range(min = 1, max = 12, message = MESSAGE) @PathVariable String month) {
		
		SimulationEngine engine = new SimulationEngine(id, year, month);
		
		return ok(transactionsService.getSimulatedList(engine));
	}
}