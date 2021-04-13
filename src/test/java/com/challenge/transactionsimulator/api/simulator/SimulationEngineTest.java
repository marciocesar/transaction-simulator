package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(SpringExtension.class)
class SimulationEngineTest {
	
	private SimulationEngine simulationEngine;
	
	@MockBean
	Register register;
	
	@BeforeEach
	void setUp() {
		simulationEngine = new SimulationEngine("1", "1", "2");
	}
	
	@Test
	@DisplayName("Should return a key")
	void shouldReturnAKey() {
		assertEquals("112", simulationEngine.getKey());
	}
	
	@Test
	@DisplayName("Should return a simulate list of transactions")
	void ShouldReturnAListOfTransactions() {
		
		final SimulatedResponseTransactionDto simulation = buildSimulatedResponse();
		
		try (final MockedStatic<TransactionSimulator> simulatorMocked = mockStatic(TransactionSimulator.class)) {
			
			simulatorMocked.when(() -> TransactionSimulator.simulate(any(TransactionSimulatorParameters.class),
			                                                         anyInt())).thenReturn(simulation);
			
			final List<SimulatedResponseTransactionDto> expected = asList(simulation, simulation);
			
			assertEquals(expected, simulationEngine.run());
		}
	}
	
	private SimulatedResponseTransactionDto buildSimulatedResponse() {
		return new SimulatedResponseTransactionDto().setAmount(1)
		                                            .setDate(1234L)
		                                            .setDescription("test");
	}
}