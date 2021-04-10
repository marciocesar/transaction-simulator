package com.challenge.transactionsimulator.api.simulator;

import com.challenge.transactionsimulator.api.dto.SimulatedResponseTransactionDto;

import java.util.ArrayList;
import java.util.List;

import static com.challenge.transactionsimulator.api.simulator.TransactionSimulator.simulate;
import static com.challenge.transactionsimulator.api.simulator.singletons.SimulationsCacheSingleton.add;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class SimulationEngine implements TransactionSimulatorParameters {
	
	private Integer id;
	private Integer age;
	private Integer month;
	private String key;
	private Integer limit;
	
	private SimulationEngine() {
		//do nothing
	}
	
	public SimulationEngine(final String id, final String age, final String month) {
		
		this.id = valueOf(id);
		this.age = valueOf(age);
		this.month = valueOf(month);
		
		this.key = id + age + month;
		
		this.limit = getMonth() * parseInt(id.substring(0, 1));
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public String getKey() {
		return key;
	}
	
	public List<SimulatedResponseTransactionDto> run() {
		
		final List<SimulatedResponseTransactionDto> simulations = new ArrayList<>();
		
		for (int index = 0; index < this.getLimit(); index++) {
			simulations.add(simulate(this, index));
		}
		
		add(getKey(), simulations);
		
		return simulations;
	}
}