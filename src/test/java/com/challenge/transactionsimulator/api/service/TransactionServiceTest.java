package com.challenge.transactionsimulator.api.service;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.challenge.transactionsimulator.api.simulator.Register;
import com.challenge.transactionsimulator.api.simulator.SimulationEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = { TransactionService.class })
@ExtendWith({ SpringExtension.class })
class TransactionServiceTest {
	
	@MockBean
	public SimulationEngine simulationEngine;
	
	@MockBean
	public Register cache;
	
	@Autowired
	public TransactionService transactionService;
	
	@Test
	@DisplayName("Should generate a new simulation")
	void shouldGenerateANewSimulation() {
		
		final SimulatedResponseTransactionDto simulatedResponseTransactionDto = buildSimulatedResponse();
		
		when(simulationEngine.run()).thenReturn(singletonList(simulatedResponseTransactionDto));
		
		assertEquals(simulatedResponseTransactionDto, transactionService.getSimulatedList(simulationEngine).get(0));
	}
	
	@Test
	@DisplayName("Should Extract Simulation from Cache")
	void shouldExtractSimulationFromCache() {
		
		final SimulatedResponseTransactionDto simulatedResponseTransactionDto = buildSimulatedResponse();
		
		when(cache.find(anyString())).thenReturn(of(singletonList(simulatedResponseTransactionDto)));
		
		when(simulationEngine.getKey()).thenReturn("any");
		
		assertEquals(simulatedResponseTransactionDto, transactionService.getSimulatedList(simulationEngine).get(0));
	}
	
	private SimulatedResponseTransactionDto buildSimulatedResponse() {
		
		return new SimulatedResponseTransactionDto().setAmount(1)
		                                            .setDate(1234L)
		                                            .setDescription("test");
	}
}