package com.challenge.transactionsimulator.api.simulator.singletons;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

public final class SimulationsCacheSingleton {
	
	private static Map<String, List<SimulatedResponseTransactionDto>> cache;
	
	private static final String RESOURCES_PATH = "src/main/resources/simulations.json";
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private SimulationsCacheSingleton() {
		//do nothing
	}
	
	private static Map<String, List<SimulatedResponseTransactionDto>> getInstance() {
		
		if (isNull(cache)) {
			
			cache = load();
			return cache;
		}
		
		return cache;
	}
	
	public static Optional<List<SimulatedResponseTransactionDto>> find(String key) {
		
		return getInstance().containsKey(key) ? of(getInstance().get(key))
		                                      : empty();
	}
	
	public static void add(String key, List<SimulatedResponseTransactionDto> transactions) {
		
		getInstance().put(key, transactions);
		save();
	}
	
	private static void save() {
		
		try (Writer writer = new BufferedWriter(new FileWriter(RESOURCES_PATH))) {
			
			writer.write(MAPPER.writeValueAsString(getInstance()));
			
		} catch (IOException ignored) {
			//do nothing
		}
	}
	
	private static Map<String, List<SimulatedResponseTransactionDto>> load() {
		
		try (Stream<String> lines = lines(get(RESOURCES_PATH), ISO_8859_1)) {
			
			final String json = lines.collect(joining(lineSeparator()));
			
			return MAPPER.readValue(json,
			                        new TypeReference<HashMap<String, List<SimulatedResponseTransactionDto>>>() {
			                        });
			
		} catch (IOException ignored) {
			return new HashMap<>();
		}
	}
}
