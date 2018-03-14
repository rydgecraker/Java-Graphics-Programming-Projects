package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import com.rcs.rydge_craker_studios.basic_utilities.GridOfInts;


public class Biome {
	
	public static final int	OCEAN		= 0;
	public static final int	WASTELAND	= 1;
	public static final int	HILLS		= 2;
	public static final int	MOUNTAINS	= 3;
	public static final int	PLAINS		= 4;
	public static final int	FOREST		= 5;
	public static final int	DESERT		= 6;
	public static final int	TUNDRA		= 7;
	public static final int	SWAMP		= 8;
	public static final int	JUNGLE		= 9;
	
	private int				type;
	private int				subBiomeWidth;
	private int				posX;
	private int				posY;
	private Seed			seed;
	
	private GridOfInts		subBiomes;
	
	public Biome(int posX, int posY, int subBiomesWidth, int type, Seed seed) {
		this.posX = posX;
		this.posY = posY;
		this.subBiomeWidth = subBiomesWidth;
		this.type = type;
		subBiomes = new GridOfInts(subBiomesWidth, subBiomeWidth);
		this.seed = seed;
	}
	
	public void generate(){
		
		SubBiomeGenerator.generate(seed, subBiomes, posX, posY, type);
		
	}
	
	public int getType(){
		return type;
	}
	
	
	public void setType(int type){
		this.type = type;
	}
	
	
	public int getSubBiomeWidth(){
		return subBiomeWidth;
	}
	
	
	public int getPosX(){
		return posX;
	}
	
	
	public int getPosY(){
		return posY;
	}
	
	public GridOfInts getSubBiomes(){
		return subBiomes;
	}
	
	
	
}
