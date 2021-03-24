package com.challenge.transactionsimulator.api.factory;

import static java.lang.Integer.parseInt;

public class TransactionSimulatedConfig {
	
	private final Integer id;
	private final Integer age;
	private final Integer month;
	
	public TransactionSimulatedConfig(final Integer id, final Integer age, final Integer month) {
		this.id = id;
		this.age = age;
		this.month = month;
	}
	
	Integer getId() {
		return id;
	}
	
	Integer getAge() {
		return age;
	}
	
	Integer getMonth() {
		return month;
	}
	
	public String getKey() {
		return getId().toString() +
		       getAge().toString() +
		       getMonth().toString();
	}
	
	public Integer getLimitOfTransactions() {
		
		final int multiplicator = parseInt(getId().toString().substring(0, 1));
		
		return getMonth() * multiplicator;
	}
}
