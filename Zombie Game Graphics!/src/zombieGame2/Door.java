package zombieGame2;

import java.awt.Color;

public class Door extends NextFrame{
	
	int doorNumber;

	public Door(int x, int y, int w, int h) {
		super(x, y, w, h, Color.MAGENTA);
	}

	public void decideDoorNumber(int doorNumber){
		this.doorNumber = doorNumber;
	}
	
	public int getDoorNumber(){
		return doorNumber;
	}
	
}
