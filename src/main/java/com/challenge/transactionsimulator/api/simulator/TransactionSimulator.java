package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;

import java.time.LocalDateTime;

import static com.challenge.transactionsimulator.api.simulator.DescriptionSimulator.generate;
import static com.challenge.transactionsimulator.api.singleton.RandomSingleton.getRandomInstance;
import static java.sql.Timestamp.valueOf;
import static java.time.LocalDate.of;

public class TransactionSimulator {
	
	private TransactionSimulator() {
		//do nothing
	}
	
	public static SimulatedResponseTransactionDto simulate(final TransactionSimulatorParameters parameters,
	                                                       final Integer index) {
		
		return new SimulatedResponseTransactionDto().setDescription(generateDescription())
		                                            .setAmount(generateAmount(parameters.getId(), parameters.getMonth(),
		                                                                      index))
		                                            .setDate(generateDate(parameters.getAge(), parameters.getMonth()));
	}
	
	private static String generateDescription() {
		
		int minLength = 10;
		int maxLength = 60;
		
		return generate(minLength, maxLength);
	}
	
	private static Integer generateAmount(final Integer id, final Integer month, final Integer index) {
		
		int negate = -1;
		
		int amount = (id + index) * month;
		
		return getRandomInstance().nextBoolean() ? amount * negate : amount;
	}
	
	private static Long generateDate(final Integer age, final int month) {
		
		int anyDay = 1;
		int firstDay = 1;
		
		int day = getRandomInstance().nextInt(of(age, month, anyDay).lengthOfMonth() - firstDay) + firstDay;
		
		final LocalDateTime randomDate = of(age, month, day).atStartOfDay();
		
		return valueOf(randomDate).getTime();
	}
}
