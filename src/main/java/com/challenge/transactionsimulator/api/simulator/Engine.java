package com.challenge.transactionsimulator.api.simulator;

import java.util.List;

public interface Engine<T> {
	
	String getKey();
	
	Integer getLimit();
	
	List<T> run();
}