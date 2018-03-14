package zbg;

import java.awt.Color;

public class Zombie extends BoardComponent{

	private int zombieNumber;
	private int spaceNumber;
	private int countdown;
	
	public Zombie(int x, int y, int width, int height, Color c, int num, int spaceSize, int spacenumber){
		super(x, y, width, height, c, spaceSize);
		zombieNumber = num;
		spaceNumber = spacenumber;
		countdown = 10;
	}
	
	public int getZombieNumber(){
		return zombieNumber;
	}
	
	public void changeSpaceNumber(int x){
		spaceNumber = x;
	}
	
	public void move(int numberOfSpaces, BoardPanel bp){
		super.move(numberOfSpaces, spaceNumber, bp);
	}
	
	public int getSpaceNumber(){
		return spaceNumber;
	}
	
	public void countdown(){
		countdown = countdown - 1;
	}
	
	public int getCountdown(){
		return countdown;
	}
	
	public void setCountDown(int x){
		countdown = x;
	}
	
}
