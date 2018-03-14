package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import com.rcs.rydge_craker_studios.basic_utilities.GridOfInts;


public class SubBiome {
	
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
	public static final int	UNKNOWN		= 10;
	public static final int	COAST		= 11;
	public static final int	LAKE		= 12;
	public static final int	ISLAND		= 13;
	
	
	private int				type;
	private int				spacesWidth;
	private int				posX;
	private int				posY;
	
	private GridOfInts[]	spaces;
	
	public SubBiome(int type, int spacesWidth, int posX, int posY, GridOfInts[] spaces) {
		super();
		this.type = type;
		this.spacesWidth = spacesWidth;
		this.posX = posX;
		this.posY = posY;
		this.spaces = spaces;
	}
	
	
	public int getType(){
		return type;
	}
	
	
	public void setType(int type){
		this.type = type;
	}
	
	
	public int getSpacesWidth(){
		return spacesWidth;
	}
	
	
	public int getPosX(){
		return posX;
	}
	
	
	public int getPosY(){
		return posY;
	}
	
	
	public GridOfInts[] getSpaces(){
		return spaces;
	}
	
	
}
