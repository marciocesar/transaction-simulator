package com.challenge.transactionsimulator.api.simulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DescriptionSimulatorTest {
	
	@Test
	@DisplayName("Should return a description between min and max length determined")
	void shouldReturnADescriptionBetweenMinAndMaxLengthDetermined() {
		
		final int minLength = 10;
		final int maxLength = 60;
		
		final String result = DescriptionSimulator.generate(minLength, maxLength);
		
		Assertions.assertAll(() -> assertTrue(result.length() >= minLength),
		                     () -> assertTrue(result.length() <= maxLength));
	}
}