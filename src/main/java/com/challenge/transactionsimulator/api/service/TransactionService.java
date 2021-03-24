package com.challenge.transactionsimulator.api.service;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.factory.TransactionSimulatedConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.challenge.transactionsimulator.api.factory.TransactionSimulatedFactory.buildTransaction;

@Service
public class TransactionService {
	
	static final Map<String, List<SimulatedResponseTransactionDto>> CACHE = new HashMap<>();
	
	public List<SimulatedResponseTransactionDto> getSimulatedList(final Integer id, final Integer age,
	                                                              final Integer month) {
		
		final TransactionSimulatedConfig config = new TransactionSimulatedConfig(id, age, month);
		
		return isAlreadySimulated(config.getKey()) ? CACHE.get(config.getKey())
		                                           : simulateTransactions(config);
	}
	
	private boolean isAlreadySimulated(final String key) {
		return CACHE.containsKey(key);
	}
	
	private List<SimulatedResponseTransactionDto> simulateTransactions(final TransactionSimulatedConfig config) {
		
		final List<SimulatedResponseTransactionDto> simulatedTransactions = new ArrayList<>();
		
		for (int index = 0; index < config.getLimitOfTransactions(); index++) {
			simulatedTransactions.add(buildTransaction(config, index));
		}
		
		CACHE.put(config.getKey(), simulatedTransactions);
		
		return simulatedTransactions;
	}
}