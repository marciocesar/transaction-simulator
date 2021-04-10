package com.challenge.transactionsimulator.api.simulator;

import static com.challenge.transactionsimulator.api.simulator.singletons.RandomSingleton.getRandomInstance;

class DescriptionSimulator {
	
	private DescriptionSimulator() {
		//do nothing
	}
	
	private static final String VOWELS = "aeiou";
	private static final String CONSONANTS = "bcfçghjklmnpqrstvxz";
	private static final String BLANK = " ";
	
	static String generate(final int minLength, final int maxLength) {
		
		final int randomLength = getRandomLength(minLength, maxLength);
		
		final int withoutBlank = 0;
		
		final Integer blankPoint = getRandomInstance().nextBoolean() ? (randomLength / getRandomFrequency())
		                                                             : withoutBlank;
		
		final StringBuilder descriptionBuilder = new StringBuilder(randomLength);
		
		final int limit = 1;
		
		while (descriptionBuilder.length() < randomLength) {
			
			final int randomConsonant = getRandomInstance().nextInt(CONSONANTS.length() - limit);
			final int randomVowel = getRandomInstance().nextInt(VOWELS.length() - limit);
			
			descriptionBuilder.append(CONSONANTS.toCharArray()[randomConsonant]);
			descriptionBuilder.append(VOWELS.toCharArray()[randomVowel]);
			
			if (blankPoint.equals(descriptionBuilder.length())) {
				descriptionBuilder.append(BLANK);
			}
		}
		
		return descriptionBuilder.toString();
	}
	
	private static int getRandomFrequency() {
		
		final int bound = 4;
		final int init = 2;
		
		return getRandomInstance().nextInt(bound - init) + init;
	}
	
	private static int getRandomLength(final int minLength, final int maxLength) {
		return getRandomInstance().nextInt(maxLength - minLength) + minLength;
	}
}