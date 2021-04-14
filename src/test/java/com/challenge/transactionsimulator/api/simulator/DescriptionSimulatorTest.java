package com.challenge.transactionsimulator.api.simulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.regex.Pattern.compile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DescriptionSimulatorTest {
	
	public static final int MIN_LENGTH = 10;
	public static final int MAX_LENGTH = 60;
	
	@Test
	@DisplayName("Should return a description between min and max length determined")
	void shouldReturnADescriptionBetweenMinAndMaxLengthDetermined() {
		
		final String result = DescriptionSimulator.generate(MIN_LENGTH, MAX_LENGTH);
		
		Assertions.assertAll(() -> assertTrue(result.length() >= MIN_LENGTH),
		                     () -> assertTrue(result.length() <= MAX_LENGTH));
	}
	
	@Test
	@DisplayName("Should generate a description can be read by humans")
	void shouldGenerateADescriptionCanBeReadByHumans() {
		
		final String consonantRegex = "[bcfghjklmnpqrstvxz][bcfghjklmnpqrstvxz]";
		
		final String result = DescriptionSimulator.generate(MIN_LENGTH, MAX_LENGTH);
		
		assertFalse(compile(consonantRegex).matcher(result).find());
	}
}