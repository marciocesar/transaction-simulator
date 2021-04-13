package com.challenge.transactionsimulator.api.service;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.simulator.Engine;
import com.challenge.transactionsimulator.api.simulator.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
	
	@Autowired
	Record record;
	
	public List<SimulatedResponseTransactionDto> getSimulatedList(Engine<SimulatedResponseTransactionDto> engine) {
		
		final Optional<List<SimulatedResponseTransactionDto>> payload = this.record.find(engine.getKey());
		
		if (payload.isPresent()) {
			return payload.get();
		}
		
		final List<SimulatedResponseTransactionDto> simulation = engine.run();
		
		record.add(engine.getKey(), simulation);
		
		return simulation;
	}
}