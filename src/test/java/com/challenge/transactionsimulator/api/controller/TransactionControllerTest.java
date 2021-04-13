package com.challenge.transactionsimulator.api.controller;

import com.challenge.transactionsimulator.api.service.TransactionService;
import com.challenge.transactionsimulator.api.simulator.Register;
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
@ContextConfiguration(classes = { TransactionController.class, TransactionService.class })
class TransactionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Register register;
	
	@Test
	@DisplayName("Get list with correct parameters")
	void GetListWithCorrectParameters() throws Exception {
		
		final String id = "1000";
		final String year = "2021";
		final String month = "12";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/{id}/transacoes/{year}/{month}", id, year, month))
		       .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(content().contentType("application/json"))
		       .andExpect(jsonPath("$.[0].descricao").isString())
		       .andExpect(jsonPath("$.[0].data").isNumber())
		       .andExpect(jsonPath("$.[0].data").isNumber());
	}
}