package gt;

import java.awt.event.KeyEvent;

public class GameTime {

	public int time;
	boolean forward, back, left, right, turnLeft, turnRight;
	
	public Controller controller;
	
	public GameTime(){
		time = 0;
		forward = false;
		back = false;
		left = false;
		right = false;
		turnLeft = false;
		turnRight = false;
		controller = new Controller();
	}
	
	public void tick(boolean[] key){
		time ++;
		forward = key[KeyEvent.VK_W];
		back = key[KeyEvent.VK_S];
		left = key[KeyEvent.VK_A];
		right = key[KeyEvent.VK_D];
		if(key[KeyEvent.VK_P]){
			System.exit(0);
		}
		controller.tick(forward, back, left, right);
	}
	
}
