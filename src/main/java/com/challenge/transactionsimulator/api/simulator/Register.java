package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

@Component
public final class Register {
	
	private Map<String, List<SimulatedResponseTransactionDto>> payload;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Value("${record.path}")
	private String path;
	
	@PostConstruct
	private void initPayload() {
		payload = load();
	}
	
	public Optional<List<SimulatedResponseTransactionDto>> find(String key) {
		return ofNullable(payload.get(key));
	}
	
	public void add(String key, List<SimulatedResponseTransactionDto> transactions) {
		
		payload.put(key, transactions);
		
		save(payload);
	}
	
	private void save(Map<String, List<SimulatedResponseTransactionDto>> payload) {
		
		try (Writer writer = new BufferedWriter(new FileWriter(path))) {
			
			writer.write(MAPPER.writeValueAsString(payload));
			
		} catch (IOException ignored) {
			//do nothing
		}
	}
	
	private Map<String, List<SimulatedResponseTransactionDto>> load() {
		
		try (Stream<String> lines = lines(get(path), ISO_8859_1)) {
			
			final String json = lines.collect(joining(lineSeparator()));
			
			return MAPPER.readValue(json, new TypeReference<HashMap<String, List<SimulatedResponseTransactionDto>>>() {
			});
			
		} catch (IOException ignored) {
			return new HashMap<>();
		}
	}
}
