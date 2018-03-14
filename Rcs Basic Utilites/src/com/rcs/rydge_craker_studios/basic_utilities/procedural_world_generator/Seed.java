package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import java.util.Random;

import com.rcs.rydge_craker_studios.basic_utilities.R;


public class Seed {
	
	private int		digits;
	private int[]	seed;
	private String	seedAsString;
	private long	seedAsLong;
	private boolean	hasSubseeds;
	private Seed[]	subSeeds;
	
	public Seed(String seed, int digits, int numSubSeeds) {
		
		if(numSubSeeds > 0) {
			hasSubseeds = true;
			while (digits % numSubSeeds != 0) {
				digits++;
			}
		} else {
			hasSubseeds = false;
		}
		
		this.digits = digits;
		if(seed == null || seed.isEmpty()) {
			Random r = new Random();
			seed = r.nextLong() + "";
		}
		
		
		if(R.StringOpp.isStringNumericNoSpecialChars(seed)) {
			createSeed(seed);
		} else {
			String sd = createNumeric(seed);
			createSeed(sd);
		}
		
		if(hasSubseeds) {
			createSubseeds(numSubSeeds);
		}
		
		R.printf("New Seed Created:" + seedAsString);
	}
	
	
	private void createSubseeds(int numSubSeeds){
		subSeeds = new Seed[numSubSeeds];
		
		int[] temp = new int[digits / numSubSeeds];
		for (int i = 0; i < numSubSeeds; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[j] = seed[(i * temp.length) + j];
			}
			subSeeds[i] = new Seed(temp);
		}
	}
	
	public Seed(int[] seed) {
		this.seed = seed;
		String s = "";
		for (int i = 0; i < seed.length; i++) {
			s += seed[i] + "";
		}
		seedAsString = s;
		seedAsLong = (long) Double.parseDouble(s);
		digits = seed.length;
		hasSubseeds = false;
	}
	
	private String createNumeric(String sd){
		return R.StringOpp.convertStringToASCII(sd);
	}
	
	
	private String adjustSeedLength(String sd){
		if(sd.length() > digits) {
			sd = sd.substring(0, digits);
		} else if(sd.length() < digits) {
			while (sd.length() < digits) {
				sd = sd + sd;
			}
			if(sd.length() > digits) {
				sd = sd.substring(0, digits);
			}
		}
		
		return sd;
	}
	
	private void createSeed(String sd){
		if(sd.length() != digits) {
			sd = adjustSeedLength(sd);
		}
		seed = new int[digits];
		for (int i = 0; i < digits; i++) {
			if(i + 1 != digits) {
				seed[i] = Integer.parseInt(sd.substring(i, i + 1));
			} else {
				seed[i] = Integer.parseInt(sd.substring(i));
			}
			
		}
		seedAsString = sd;
		seedAsLong = (long) (Double.parseDouble(seedAsString));
	}
	
	public int getDigits(){
		return digits;
	}
	
	public int getSeedAt(int x){
		while (x < 0) {
			x = x + 1;
		}
		return seed[x];
	}
	
	public Seed getIncrementedSeed(int iterator, int incrementation){
		if(incrementation != 0) {
			int[] newSeed = new int[digits];
			if(iterator > 0) {
				int current = iterator - 1;
				for (int i = 0; i < newSeed.length; i++) {
					if(i == current) {
						newSeed[i] = (seed[i] + incrementation) % 10;
						if(newSeed[i] < 0) {
							newSeed[i] = Math.abs(newSeed[i]);
						}
						current += iterator;
					} else {
						newSeed[i] = seed[i];
					}
				}
				
				return new Seed(newSeed);
			} else if(iterator < 0) {
				int current = digits + iterator;
				for (int i = newSeed.length - 1; i > -1; i--) {
					if(i == current) {
						newSeed[i] = (seed[i] + incrementation) % 10;
						if(newSeed[i] < 0) {
							newSeed[i] = Math.abs(newSeed[i]);
						}
						current += iterator;
					} else {
						newSeed[i] = seed[i];
					}
				}
				return new Seed(newSeed);
				
			} else if(iterator == 0) {
				iterator = 10;
				int current = iterator - 1;
				for (int i = 0; i < newSeed.length; i++) {
					if(i == current) {
						newSeed[i] = (seed[i] + incrementation) % 10;
						if(newSeed[i] < 0) {
							newSeed[i] = Math.abs(newSeed[i]);
						}
						current += iterator;
					} else {
						newSeed[i] = seed[i];
					}
				}
				
				
				return new Seed(newSeed);
			}
		}
		
		
		return this;
		
	}
	
	public String getSeedAsString(){
		return seedAsString;
	}
	
	public long getSeedAsInt(){
		return seedAsLong;
	}
	
	public int[] getSeed(){
		return seed;
	}
	
	public int getAverage(){
		float avg = 0;
		for (int i = 0; i < seed.length; i++) {
			avg += seed[i];
		}
		
		avg = (avg + 0.0f / digits + 0.0f);
		String s = R.FormatNumber.roundToNearestInt(avg);
		return Integer.parseInt(s);
	}
	
	
	public boolean hasSubseeds(){
		return hasSubseeds;
	}
	
	
	public int[] getSubseed(int position){
		return subSeeds[position].getSeed();
	}
	
	public int getNumSubseeds(){
		return subSeeds.length;
	}
	
	public void printSeed(){
		R.print("\n");
		for (int i = 0; i < seed.length; i++) {
			R.print("[ " + seed[i] + " ]");
		}
		R.print("\n");
	}
	
}
