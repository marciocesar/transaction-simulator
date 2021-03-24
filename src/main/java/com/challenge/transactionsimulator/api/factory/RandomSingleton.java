package com.challenge.transactionsimulator.api.factory;

import java.util.Random;

import static java.util.Objects.isNull;

public class RandomSingleton {
	
	private static Random random;
	
	private RandomSingleton() {
	}
	
	static Random getRandom() {
		
		if (isNull(random)) {
			
			random = new Random();
			return random;
		}
		
		return random;
	}
}
