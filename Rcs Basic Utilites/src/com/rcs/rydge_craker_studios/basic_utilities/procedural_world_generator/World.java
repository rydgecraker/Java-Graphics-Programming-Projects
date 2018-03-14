package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import java.util.ArrayList;

import com.rcs.rydge_craker_studios.basic_utilities.GridOfInts;
import com.rcs.rydge_craker_studios.basic_utilities.R;


public class World {
	
	private float				width;
	private float				height;
	private Seed				originSeed;
	private GridOfInts			biomeGrid;
	private ArrayList<Biome>	oceans;
	
	public World(float widthInBiomes) {
		if(widthInBiomes % 2 == 0) {
			widthInBiomes += 1;
		}
		
		this.width = widthInBiomes;
		this.height = widthInBiomes;
		biomeGrid = new GridOfInts((int) width, (int) height);
		oceans = new ArrayList<Biome>();
	}
	
	
	public void generateNewWorld(Seed seed){
		float genTime = System.nanoTime();
		this.originSeed = seed;
		biomeGrid.setGridAt(0, 0, originGen());
		
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
		
		
		float percentage = 0;
		float perInc = (width * height) / 100.0f;
		float perIncky = perInc;
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
				
				iterator = (currentX - currentY) % 10;
				incremetor = Integer.parseInt(R.FormatNumber.roundToNearestInt((((currentX + 0.0f) + (currentY + 0.0f)) / (2 + 0.0f)))) % 10;
				position = (int) (((currentX + currentY) + (counter * 2)) % 25);
				
				newWorldSeed = seed.getIncrementedSeed(iterator, incremetor);
				
				biomeGrid.setGridAt(currentX, currentY, newWorldSeed.getSeedAt(position));
				if(biomeGrid.getValueAt(currentX, currentY) == Biome.OCEAN) {
					oceans.add(new Biome(currentX, currentY, 9, Biome.OCEAN, newWorldSeed));
					oceans.get(oceans.size() - 1).generate();
				}
				lastY = currentY;
				lastX = currentX;
				
				if(counter >= perIncky) {
					perIncky += perInc;
					percentage++;
					if(percentage == 50) {
						R.print("\n" + percentage + "% ");
					} else {
						R.print(percentage + "% ");
					}
					
				}
				
				
			}
			currentX++;
			lastXinc = 0;
			lastYinc = 0;
			size++;
			first = true;
		}
		percentage++;
		R.printf("\n" + percentage + "%");
		
		genTime = (System.nanoTime() - genTime) / 1000000000.0f;
		String totalTime = R.FormatNumber.roundToDecimalPlaces(genTime, 2);
		R.printf("Total World Gen time was: " + totalTime + " Seconds");
	}
	
	private int originGen(){
		return originSeed.getAverage() % 10;
	}
	
	
	public float getWidth(){
		return width;
	}
	
	
	public float getHeight(){
		return height;
	}
	
	
	public GridOfInts getBiomeGrid(){
		return biomeGrid;
	}
	
	public Seed getOriginSeed(){
		return originSeed;
	}
	
	public void printWorld(){
		
		//biomeGrid.printGrid();
		R.printf("\n");
		R.printf("Stats: \n" + biomeGrid.getOccuranceStats());
		R.printf("Average Value is: " + SubBiomeNames.values()[Integer.parseInt(R.FormatNumber.roundToNearestInt(biomeGrid.getAverageValue()))]);
		R.printf("Most Occuring Value is: " + SubBiomeNames.values()[biomeGrid.getModeValue()]);
		R.printf("Origin is: " + SubBiomeNames.values()[biomeGrid.getValueAt(0, 0)]);
		
	}
}
