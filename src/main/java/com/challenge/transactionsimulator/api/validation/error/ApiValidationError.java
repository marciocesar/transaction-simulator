package com.challenge.transactionsimulator.api.validation.error;

public class ApiValidationError {
	
	private String field;
	private Object rejectedValue;
	private String message;
	
	ApiValidationError(final String field, final Object rejectedValue, final String message) {
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
	
	public String getField() {
		return field;
	}
	
	public Object getRejectedValue() {
		return rejectedValue;
	}
	
	public String getMessage() {
		return message;
	}
}