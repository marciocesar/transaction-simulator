package com.challenge.transactionsimulator.api.factory;

import static com.challenge.transactionsimulator.api.factory.RandomSingleton.getRandom;

class DummyDescription {
	
	private DummyDescription() {
		//do nothing
	}
	
	private static final String VOWELS = "aeiou";
	private static final String CONSONANTS = "bcfçghjklmnpqrstvxz";
	private static final String BLANK = " ";
	
	static String generate(final int minLength, final int maxLength) {
		
		final int randomLength = getRandomLength(minLength, maxLength);
		
		final int withoutBlank = 0;
		
		final Integer blankPoint = getRandom().nextBoolean() ? (randomLength / getRandomFrequency())
		                                                     : withoutBlank;
		
		final StringBuilder descriptionBuilder = new StringBuilder(randomLength);
		
		final int limit = 1;
		
		while (descriptionBuilder.length() < randomLength) {
			
			final int randomConsonant = getRandom().nextInt(CONSONANTS.length() - limit);
			final int randomVowel = getRandom().nextInt(VOWELS.length() - limit);
			
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
		
		return getRandom().nextInt(bound - init) + init;
	}
	
	private static int getRandomLength(final int minLength, final int maxLength) {
		return getRandom().nextInt(maxLength - minLength) + minLength;
	}
}