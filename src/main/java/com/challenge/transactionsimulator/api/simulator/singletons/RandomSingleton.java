package com.challenge.transactionsimulator.api.simulator.singletons;

import java.util.Random;

import static java.util.Objects.isNull;

public final class RandomSingleton {
	
	private static Random random;
	
	private RandomSingleton() {
		//do nothing
	}
	
	public static Random getRandomInstance() {
		
		if (isNull(random)) {
			
			random = new Random();
			return random;
		}
		
		return random;
	}
}
