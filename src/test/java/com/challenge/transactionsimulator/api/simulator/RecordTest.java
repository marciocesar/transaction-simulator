package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static com.challenge.transactionsimulator.api.simulator.RecordTest.PATH;
import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.file.Files.delete;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Record.class)
@TestPropertySource(properties = { "record.path=" + PATH })
class RecordTest {
	
	public static final String PATH = "src/test/resources/record.json";
	@Autowired
	Record record;
	
	@Test
	@DisplayName("Should generate a register with simulated payload")
	void shouldGenerateARegisterWithPayload() throws IOException {
		
		final long date = 123456L;
		final int amount = 9999;
		final String description = "test";
		final String key = "123";
		
		record.add(key, singletonList(buildASimulatedPayload(date, amount, description)));
		
		Stream<String> lines = lines(get(PATH), ISO_8859_1);
		
		final String json = lines.collect(joining(lineSeparator()));
		
		final HashMap<String, List<SimulatedResponseTransactionDto>> payload = readFile(json);
		
		final SimulatedResponseTransactionDto simulationRecorded = payload.get(key).get(0);
		
		Assertions.assertAll(() -> assertEquals(date, simulationRecorded.getDate()),
		                     () -> assertEquals(description, simulationRecorded.getDescription()),
		                     () -> assertEquals(amount, simulationRecorded.getAmount()));
		
		delete(get(PATH));
	}
	
	@Test
	@DisplayName("Should find from key and return a simulated payload")
	void shouldFindAndReturnASimulatedPayload() throws IOException {
		
		final long date = 4444L;
		final int amount = 8888;
		final String description = "test2";
		final String key = "321";
		
		final SimulatedResponseTransactionDto simulation = buildASimulatedPayload(date, amount, description);
		
		record.add(key, singletonList(simulation));
		final SimulatedResponseTransactionDto result = record.find(key).get().get(0);
		
		Assertions.assertAll(() -> assertEquals(date, result.getDate()),
		                     () -> assertEquals(amount, result.getAmount()),
		                     () -> assertEquals(description, result.getDescription()));
		
		delete(get(PATH));
	}
	
	private SimulatedResponseTransactionDto buildASimulatedPayload(final long date, final int amount,
	                                                               final String description) {
		
		return new SimulatedResponseTransactionDto().setDate(date).setAmount(amount).setDescription(description);
	}
	
	private HashMap<String, List<SimulatedResponseTransactionDto>> readFile(final String json) throws
	                                                                                           JsonProcessingException {
		
		final ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(json, new TypeReference<HashMap<String, List<SimulatedResponseTransactionDto>>>() {
		});
	}
}