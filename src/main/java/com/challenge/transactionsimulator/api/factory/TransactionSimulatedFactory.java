package com.challenge.transactionsimulator.api.factory;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;

import java.time.LocalDateTime;

import static com.challenge.transactionsimulator.api.factory.DummyDescription.generate;
import static com.challenge.transactionsimulator.api.factory.RandomSingleton.getRandom;
import static java.sql.Timestamp.valueOf;
import static java.time.LocalDate.of;

public class TransactionSimulatedFactory {
	
	private TransactionSimulatedFactory() {
		//do nothing
	}
	
	public static SimulatedResponseTransactionDto buildTransaction(final TransactionSimulatedConfig config,
	                                                               final Integer index) {
		
		return new SimulatedResponseTransactionDto().setDescription(generateDescription())
		                                            .setAmount(generateAmount(config.getId(), config.getMonth(), index))
		                                            .setDate(generateDate(config.getAge(), config.getMonth()));
	}
	
	private static String generateDescription() {
		
		final int minLength = 10;
		final int maxLength = 60;
		
		return generate(minLength, maxLength);
	}
	
	private static Integer generateAmount(final Integer id, final Integer month, final Integer index) {
		
		final int negate = -1;
		
		final int amount = (id + index) * month;
		
		return getRandom().nextBoolean() ? amount * negate : amount;
	}
	
	private static Long generateDate(final Integer age, final int month) {
		
		final int anyDay = 1;
		final int firstDay = 1;
		
		final int day = getRandom().nextInt(of(age, month, anyDay).lengthOfMonth() - firstDay) + firstDay;
		
		final LocalDateTime randomDate = of(age, month, day).atStartOfDay();
		
		return valueOf(randomDate).getTime();
	}
}
