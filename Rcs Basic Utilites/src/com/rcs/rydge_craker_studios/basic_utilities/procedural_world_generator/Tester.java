package com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator;

import com.rcs.rydge_craker_studios.basic_utilities.R;


public class Tester {
	
	public static void main(String[] args){
		R.printf("Started");
		Seed seed = new Seed("", 25, 5);
		World w = new World(101);
		w.generateNewWorld(seed);
		R.printf("Finished");
		w.printWorld();
	}
	
}
