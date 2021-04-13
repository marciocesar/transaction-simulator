package com.challenge.transactionsimulator.api.validation.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EntityExceptionHandler.class)
class EntityExceptionHandlerTest {
	
	@Autowired
	EntityExceptionHandler entityExceptionHandler;
	
	@MockBean
	ConstraintViolationException constraintViolation;
	
	@Test
	@DisplayName("Should return a valid error response")
	void shouldReturnABadRequestWithValidErrorResponse() {
		
		final ResponseEntity<ApiError> error = entityExceptionHandler.handleConstraintViolation(constraintViolation);
		
		assertAll(() -> assertEquals(BAD_REQUEST, error.getStatusCode()),
		          () -> assertEquals("Validation error", error.getBody().getMessage()));
	}
}