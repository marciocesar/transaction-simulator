package com.challenge.transactionsimulator.api.controller;

import com.challenge.transactionsimulator.api.service.TransactionService;
import com.challenge.transactionsimulator.api.simulator.Register;
import com.challenge.transactionsimulator.api.validation.error.EntityExceptionHandler;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { TransactionController.class, TransactionService.class, EntityExceptionHandler.class })
class TransactionControllerTest {
	
	public static final String ID = "1000";
	public static final String YEAR = "2021";
	public static final String MONTH = "12";
	public static final String WRONG = "anything";
	
	public static final String RESOURCE = "/{id}/transacoes/{year}/{month}";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Register register;
	
	@Test
	@DisplayName("Should return a list with corret parameters")
	void shouldReturnAListWithCorrectParameters() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE, ID, YEAR, MONTH))
		       .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.[0].descricao").isString())
		       .andExpect(jsonPath("$.[0].data").isNumber())
		       .andExpect(jsonPath("$.[0].data").isNumber());
	}
	
	@Test
	@DisplayName("Should throw constraint exception")
	void shouldThrowConstraintExceptionToFieldId() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE, WRONG, YEAR, MONTH))
		       .andDo(print())
		       .andExpect(status().isBadRequest())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
		       .andExpect(jsonPath("$.message", Matchers.is("Validation error")))
		       .andExpect(jsonPath("$.dateTime").isString())
		       .andExpect(jsonPath("$.errors.[0].field", Matchers.is("id")))
		       .andExpect(jsonPath("$.errors.[0].rejectedValue", Matchers.is(WRONG)))
		       .andExpect(jsonPath("$.errors.[0].message", Matchers.is("Deve ser um valor entre 1000 and 100000")));
	}
	
	@Test
	@DisplayName("Should throw constraint exception to field month")
	void shouldThrowConstraintExceptionToFieLdMonth() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE, ID, YEAR, WRONG))
		       .andDo(print())
		       .andExpect(status().isBadRequest())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
		       .andExpect(jsonPath("$.message", Matchers.is("Validation error")))
		       .andExpect(jsonPath("$.dateTime").isString())
		       .andExpect(jsonPath("$.errors.[0].field", Matchers.is("month")))
		       .andExpect(jsonPath("$.errors.[0].rejectedValue", Matchers.is(WRONG)))
		       .andExpect(jsonPath("$.errors.[0].message", Matchers.is("Deve ser um valor entre 1 and 12")));
	}
	
	@Test
	@DisplayName("Should throw constraint exception to field year")
	void shouldThrowConstraintExceptionToFieldYear() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE, ID, WRONG, MONTH))
		       .andDo(print())
		       .andExpect(status().isBadRequest())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
		       .andExpect(jsonPath("$.message", Matchers.is("Validation error")))
		       .andExpect(jsonPath("$.dateTime").isString())
		       .andExpect(jsonPath("$.errors.[0].field", Matchers.is("year")))
		       .andExpect(jsonPath("$.errors.[0].rejectedValue", Matchers.is(WRONG)))
		       .andExpect(jsonPath("$.errors.[0].message", Matchers.is("Deve ser um valor entre 1 and 9999")));
	}
}