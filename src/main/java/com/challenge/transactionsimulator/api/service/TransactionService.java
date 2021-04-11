package com.challenge.transactionsimulator.api.service;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.simulator.Engine;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.challenge.transactionsimulator.api.simulator.singletons.SimulationsCacheSingleton.find;

@Service
public class TransactionService {
	
	public List<SimulatedResponseTransactionDto> getSimulatedList(Engine<SimulatedResponseTransactionDto> engine) {
		return find(engine.getKey()).orElseGet(engine::run);
	}
}