package com.challenge.transactionsimulator.api.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
		
		ApiError apiError = new ApiError(BAD_REQUEST);
		
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getConstraintViolations());
		
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}