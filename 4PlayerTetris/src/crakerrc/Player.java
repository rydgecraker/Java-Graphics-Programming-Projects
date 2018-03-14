package crakerrc;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.aplu.xboxcontroller.*;

public class Player implements KeyListener{
	
	public boolean usingController;
	public XboxController xc;
	public GameScreen gs;
	public int playerNumber;
	public Grid grid;
	
	
	//Keyboard
	public boolean w;
	public boolean a;
	public boolean s;
	public boolean d;
	public boolean space;
	public boolean j;
	public boolean k;
	public boolean l;
	public boolean i;
	public boolean q;
	public boolean e;
	public boolean f;
	public boolean shift;
	public boolean esc;
	
	//Controller
	public double lThumbDir;
	public double lThumbMag;
	public double rThumbMag;
	public double rThumbDir;
	
	public boolean aButton;
	public boolean bButton;
	public boolean xButton;
	public boolean yButton;
	public boolean startButton;
	public boolean backButton;
	
	public Color col;
	public int offset;
	
	public Player(XboxController controller, GameScreen gs, int playerNumber, Grid grid){
		usingController = true;
		xc = controller;
		this.gs = gs;
		this.playerNumber = playerNumber;
		this.grid = grid;
		addContList();
		init();
	}
	
	public void init(){
		if(playerNumber == 0){
			col = Color.RED;
			offset = 12;
		}else if(playerNumber == 1){
			col = Color.GREEN;
			offset = 9;
		}else if(playerNumber == 2){
			col = Color.BLUE;
			offset = 6;
		}else if(playerNumber == 3){
			col = Color.YELLOW;
			offset = 3;
		}
	}
	
	public void addContList(){
		xc.addXboxControllerListener(new XboxControllerAdapter(){
			public void leftThumbDirection(double d){
				lThumbDir = d;
			}
			public void rightThumbDirection(double d){
				rThumbDir = d;
			}
			public void leftThumbMagnitude(double d){
				lThumbMag = d;
			}
			public void rightThumbMagnitude(double d){
				rThumbMag = d;
			}
			public void back(boolean b){
				System.exit(0);
				backButton = b;
			}
			public void start(boolean b){
				startButton = b;
			}
			public void buttonA(boolean b){
				aButton = b;
			}
			public void buttonB(boolean b){
				bButton = b;
			}
			public void buttonX(boolean b){
				xButton = b;
			}
			public void buttonY(boolean b){
				yButton = b;
			}
		});
	}
	
	public void playerInput(){
		if((lThumbMag > .5 && (lThumbDir >= 315 && lThumbDir < 45)) || w){
			//Pressing Up lStick
			gs.changeTime(5, playerNumber);
		}
		if((lThumbMag > .5 && (lThumbDir >= 225 && lThumbDir < 315)) || a){
			//Pressing Left lStick
			grid.shiftCurrentLeft();
		}
		if((lThumbMag > .5 && (lThumbDir >= 135 && lThumbDir < 225)) || s){
			//Pressing Down lStick
			gs.changeTime(.125f, playerNumber);
		}
		
		if(lThumbMag < .5){
			gs.changeTime(1f, playerNumber);
		}
		
		if((lThumbMag > .5 && (lThumbDir >= 45 && lThumbDir < 135)) || d){
			//Pressing Right lStick
			grid.shiftCurrentRight();
		}
		
		if((rThumbMag > .5 && (rThumbDir >= 315 && rThumbDir < 45)) || i){
			//Pressing Up rStick
			
		}
		if((rThumbMag > .5 && (rThumbDir >= 225 && rThumbDir < 315)) || j){
			//Pressing Left rStick
			grid = gs.getGridToLeft(grid.playerNumber);
		}
		if((rThumbMag > .5 && (rThumbDir >= 135 && rThumbDir < 225)) || k){
			//Pressing Down rStick
			
		}
		if((rThumbMag > .5 && (rThumbDir >= 45 && rThumbDir < 135)) || l){
			//Pressing Right rStick
			grid = gs.getGridToRight(grid.playerNumber);
		}
		if((shift && esc) || backButton){
			System.exit(0);
		}
		if(esc || startButton){
			//Pause
			
		}
		
		if(aButton || space){
			//A button
			grid.drop();
		}
		if(xButton || q){
			//xButton
			grid.RotateLeft();
		}
		if(yButton || f){
			//YButton
			if(gs.playWithoutMe){
				gs.playWithoutMe = false;
			}else{
				gs.playWithoutMe = true;
			}
		}
		if(bButton || e){
			//B Button
			grid.RotateRight();
		}
		
	}
	
	public Player(GameScreen gs, int playerNumber, Grid grid){
		usingController = false;
		this.gs = gs;
		this.playerNumber = playerNumber;
		this.grid = grid;
		init();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_A){
			a = true;
		}
		if(e.getKeyCode() == e.VK_W){
			w = true;
		}
		if(e.getKeyCode() == e.VK_ESCAPE){
			esc = true;
		}
		if(e.getKeyCode() == e.VK_SPACE){
			space = true;
		}
		if(e.getKeyCode() == e.VK_S){
			s = true;
		}
		if(e.getKeyCode() == e.VK_D){
			d = true;
		}
		if(e.getKeyCode() == e.VK_J){
			j = true;
		}
		if(e.getKeyCode() == e.VK_I){
			i = true;
		}
		if(e.getKeyCode() == e.VK_L){
			l = true;
		}
		if(e.getKeyCode() == e.VK_K){
			k = true;
		}
		if(e.getKeyCode() == e.VK_Q){
			q = true;
		}
		if(e.getKeyCode() == e.VK_E){
			this.e = true;
		}
		if(e.getKeyCode() == e.VK_F){
			f = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_A){
			a = false;
		}
		if(e.getKeyCode() == e.VK_W){
			w = false;
		}
		if(e.getKeyCode() == e.VK_ESCAPE){
			esc = false;
		}
		if(e.getKeyCode() == e.VK_SPACE){
			space = false;
		}
		if(e.getKeyCode() == e.VK_S){
			s = false;
		}
		if(e.getKeyCode() == e.VK_D){
			d = false;
		}
		if(e.getKeyCode() == e.VK_J){
			j = false;
		}
		if(e.getKeyCode() == e.VK_I){
			i = false;
		}
		if(e.getKeyCode() == e.VK_L){
			l = false;
		}
		if(e.getKeyCode() == e.VK_K){
			k = false;
		}
		if(e.getKeyCode() == e.VK_Q){
			q = false;
		}
		if(e.getKeyCode() == e.VK_E){
			this.e = false;
		}
		if(e.getKeyCode() == e.VK_F){
			f = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
