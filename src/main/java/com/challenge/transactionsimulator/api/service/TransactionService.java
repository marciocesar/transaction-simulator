package com.challenge.transactionsimulator.api.service;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.simulator.Engine;
import com.challenge.transactionsimulator.api.simulator.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
	
	@Autowired
	Register register;
	
	public List<SimulatedResponseTransactionDto> getSimulatedList(final Engine<SimulatedResponseTransactionDto> engine) {
		
		final Optional<List<SimulatedResponseTransactionDto>> payload = this.register.find(engine.getKey());
		
		if (payload.isPresent()) {
			return payload.get();
		}
		
		final List<SimulatedResponseTransactionDto> simulation = engine.run();
		
		register.add(engine.getKey(), simulation);
		
		return simulation;
	}
}