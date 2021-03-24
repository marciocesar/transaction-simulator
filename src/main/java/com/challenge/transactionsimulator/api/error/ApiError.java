package com.challenge.transactionsimulator.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;

public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonProperty("dateTime")
	private final LocalDateTime localDateTime;
	
	@JsonProperty("errors")
	private List<ApiValidationError> subErrors;
	
	private HttpStatus status;
	private String message;
	
	private ApiError() {
		localDateTime = now();
	}
	
	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(final String message) {
		this.message = message;
	}
	
	public List<ApiValidationError> getSubErrors() {
		return subErrors;
	}
	
	private void addSubError(ApiValidationError subError) {
		
		if (isNull(subErrors)) {
			subErrors = new ArrayList<>();
		}
		
		subErrors.add(subError);
	}
	
	private void addValidationError(String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(field, rejectedValue, message));
	}
	
	private void addValidationError(ConstraintViolation<?> constraintViolation) {
		
		this.addValidationError(
				((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
				constraintViolation.getInvalidValue(),
				constraintViolation.getMessage());
	}
	
	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);
	}
}