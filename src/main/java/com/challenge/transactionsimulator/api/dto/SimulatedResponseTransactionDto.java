package com.challenge.transactionsimulator.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulatedResponseTransactionDto {
	
	@JsonProperty("descricao")
	private String description;
	
	@JsonProperty("data")
	private Long date;
	
	@JsonProperty("valor")
	private Integer amount;
	
	public String getDescription() {
		return description;
	}
	
	public SimulatedResponseTransactionDto setDescription(final String description) {
		this.description = description;
		return this;
	}
	
	public Long getDate() {
		return date;
	}
	
	public SimulatedResponseTransactionDto setDate(final Long date) {
		this.date = date;
		return this;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public SimulatedResponseTransactionDto setAmount(final Integer amount) {
		this.amount = amount;
		return this;
	}
}
