package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionSimulatorTest {
	
	@Test
	@DisplayName("Should return a simulated transaction with valid fields")
	void shouldReturnASimulatedTransactionWithValidFields() {
		
		final String id = "1";
		final String age = "2";
		final String month = "3";
		
		final int index = 1;
		
		final SimulationEngine parameters = new SimulationEngine(id, age, month);
		
		final SimulatedResponseTransactionDto result = TransactionSimulator.simulate(parameters, index);
		
		final int maxLengthDescription = 60;
		final int minLengthDescription = 10;
		
		final int amount = (parseInt(id) + index) * parseInt(month);
		final Integer amountNegate = amount * -1;
		
		final int anyDay = 1;
		
		final LocalDate date = LocalDate.of(parseInt(age),
		                                    parseInt(month),
		                                    anyDay);
		
		final LocalDate localDateResult = new Timestamp(result.getDate()).toLocalDateTime()
		                                                                 .toLocalDate();
		
		assertAll(() -> assertTrue(result.getDescription().length() <= maxLengthDescription),
		          () -> assertTrue(result.getDescription().length() >= minLengthDescription),
		          () -> assertTrue(result.getAmount().equals(amount) || result.getAmount().equals(amountNegate)),
		          () -> assertTrue(localDateResult.getDayOfMonth() <= date.lengthOfMonth()));
	}
}