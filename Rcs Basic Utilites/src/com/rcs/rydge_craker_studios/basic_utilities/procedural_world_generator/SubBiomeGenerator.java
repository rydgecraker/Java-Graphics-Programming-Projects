package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import com.rcs.rydge_craker_studios.basic_utilities.GridOfInts;
import com.rcs.rydge_craker_studios.basic_utilities.R;


public class SubBiomeGenerator {
	
	
	
	public static GridOfInts generate(Seed s, GridOfInts subBiomes, int xLoc, int yLoc, int type){
		if(type == Biome.OCEAN) {
			return generateOcean(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.WASTELAND) {
			return generateWasteland(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.HILLS) {
			return generateHills(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.MOUNTAINS) {
			return generateMountains(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.PLAINS) {
			return generatePlains(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.FOREST) {
			return generateForest(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.DESERT) {
			return generateDesert(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.TUNDRA) {
			return generateTundra(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.SWAMP) {
			return generateSwamp(s, subBiomes, xLoc, yLoc);
		}
		if(type == Biome.JUNGLE) {
			return generateJungle(s, subBiomes, xLoc, yLoc);
		}
		
		
		return subBiomes;
		
	}
	
	
	
	private static GridOfInts generateOcean(Seed seed, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		float genTime = System.nanoTime();
		sbs.setGridAt(0, 0, SubBiome.OCEAN);
		
		int width = sbs.getWidth();
		int height = sbs.getHeight();
		
		
		boolean first = true;
		int lastYinc = 0;
		int lastXinc = 0;
		int size = 1;
		int lastX = 0;
		int lastY = 0;
		int currentX = 0;
		int currentY = 0;
		
		
		
		int iterator = 0;
		int incremetor = 0;
		int position = 0;
		double counter = 0;
		Seed newWorldSeed;
		
		
		float perInc = (width * height) / 100.0f;
		while (size < (height / 2)) {
			for (int i = 0; i < 8 * size; i++) {
				counter++;
				if(first) {
					currentY++;
					lastYinc = 1;
					first = false;
				} else if(lastYinc > 0 && lastY != size && lastXinc == 0) {
					currentY++;
					lastYinc = 1;
				} else if(lastYinc > 0 && lastY == size && lastXinc == 0) {
					lastYinc = 0;
					lastXinc = 1;
					currentX++;
				} else if(lastXinc > 0 && lastX != size && lastYinc == 0) {
					currentX++;
					lastXinc = 1;
				} else if(lastXinc > 0 && lastX == size && lastYinc == 0) {
					lastYinc = -1;
					lastXinc = 0;
					currentY--;
				} else if(lastYinc < 0 && lastY != size * -1 && lastXinc == 0) {
					currentY--;
					lastYinc = -1;
				} else if(lastYinc < 0 && lastY == size * -1 && lastXinc == 0) {
					lastYinc = 0;
					lastXinc = -1;
					currentX--;
				} else if(lastXinc < 0 && lastX != size * -1 && lastYinc == 0) {
					currentX--;
					lastXinc = -1;
				} else if(lastXinc < 0 && lastX == size * -1 && lastYinc == 0) {
					lastXinc = 0;
					lastYinc = 1;
					currentY++;
				}
				
				
				if(size == 1) {
					sbs.setGridAt(currentX, currentY, SubBiome.OCEAN);
				} else if(size == 2) {
					iterator = (int) (Math.sqrt(Math.pow(currentX + 0.0, 2) + Math.pow(currentY + 0.0, 2)) % 10);
					incremetor = 1;
					position = (int) counter;
					
					newWorldSeed = seed.getIncrementedSeed(iterator, incremetor);
					int result = newWorldSeed.getSeedAt(position);
					if(result % 2 == 0) {
						sbs.setGridAt(currentX, currentY, SubBiome.OCEAN);
					} else {
						sbs.setGridAt(currentX, currentY, SubBiome.COAST);
					}
				} else if(size == 3) {
					iterator = (int) (Math.sqrt(Math.pow(currentX + 0.0, 2) + Math.pow(currentY + 0.0, 2)) % 10);
					incremetor = 1;
					position = (int) counter;
					
					newWorldSeed = seed.getIncrementedSeed(iterator, incremetor);
					if(position > 24) {
						position = position - 24;
					}
					int result = newWorldSeed.getSeedAt(position);
					if(sbs.isTouching(SubBiome.COAST, currentX, currentY)) {
						result = result % 4;
						if(result < 3) {
							sbs.setGridAt(currentX, currentY, SubBiome.COAST);
						} else {
							sbs.setGridAt(currentX, currentY, SubBiome.OCEAN);
						}
					} else {
						result = result % 4;
						if(result == 1 || result == 2) {
							sbs.setGridAt(currentX, currentY, SubBiome.COAST);
						} else {
							sbs.setGridAt(currentX, currentY, SubBiome.OCEAN);
						}
					}
				} else if(size == 4) {
					sbs.determineBorders(currentX, currentY, size, SubBiome.COAST);
					sbs.surround(SubBiome.COAST, SubBiome.UNKNOWN, size);
					sbs.surround(SubBiome.COAST, SubBiome.COAST, SubBiome.UNKNOWN, size);
				}
				
				
				
				lastY = currentY;
				lastX = currentX;
				
				
			}
			currentX++;
			lastXinc = 0;
			lastYinc = 0;
			size++;
			
			
			if(size == 4) {
				sbs.surround(SubBiome.COAST, SubBiome.OCEAN, size - 1);
				sbs.surround(SubBiome.OCEAN, SubBiome.COAST, size - 1);
			}
			
			if(size == 5) {
				size++;
			}
			
			first = true;
		}
		
		genTime = (System.nanoTime() - genTime) / 1000000000.0f;
		String totalTime = R.FormatNumber.roundToDecimalPlaces(genTime, 2);
		//R.printf("\nTotal Ocean Gen time was: " + totalTime + " Seconds");
		//sbs.printGrid();
		
		return sbs;
	}
	
	private static GridOfInts generateWasteland(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateHills(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateMountains(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generatePlains(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateForest(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateDesert(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateTundra(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateSwamp(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
	private static GridOfInts generateJungle(Seed s, GridOfInts subBiomes, int xLoc, int yLoc){
		GridOfInts sbs = subBiomes;
		
		
		
		return sbs;
	}
	
}
