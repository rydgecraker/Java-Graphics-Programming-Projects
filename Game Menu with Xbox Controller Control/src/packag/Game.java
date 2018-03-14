package packag;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

import javax.swing.*;

public class Game {

	public XboxController c1;
	public XboxController c2;
	public XboxController c3;
	public XboxController c4;
	
	public boolean c1c;
	public boolean c2c;
	public boolean c3c;
	public boolean c4c;
	
	public JFrame frame;
	public GamePanel gp;
	
	public String dr;
	public double magnitude;
	
	public int screenwidth;
	public int screenheight;
	
	public boolean stmenu;
	
	//Controller Stuff
	public boolean a;
	public boolean b;
	public boolean x;
	public boolean y;
	public boolean start;
	
	//Player Menu
	public boolean playerMenu;
	public int numberOfPlayers;
	
	//gameStart
	public boolean startGame;
	
	public Game(XboxController c1, boolean c1connected, XboxController c2, boolean c2connected, XboxController c3, boolean c3connected, XboxController c4, boolean c4connected){
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c1c = c1connected;
		this.c2c = c2connected;
		this.c3c = c3connected;
		this.c4c = c4connected;
		stmenu = true;
		
		dr = "";
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenwidth = gd.getDisplayMode().getWidth();
		screenheight = gd.getDisplayMode().getHeight();
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		
		
		gp = new GamePanel(Color.BLACK, frame.getHeight(), frame.getWidth());
		frame.setContentPane(gp);
		frame.revalidate();
		frame.repaint();
		gp.addCross(20, 20, Color.GREEN, 0, 0);
		
		frame.repaint();
		controllerConfig();
		boolean b = true;
		gp.addButton(new Button(new Color(200, 0, 0), new Color(255, 0, 0), Color.WHITE, "Start", (int) (screenwidth/2 - (screenwidth * .05)),(int) (screenheight/2 - (screenheight * .05)),(int) (screenwidth * .05) * 2, (int) (screenheight * .05) * 2));
		gp.height = screenheight;
		gp.width = screenwidth;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		while(b){
			frame.repaint();
			if(gp.drawCross){
				moveCross();
			}
			if(gp.dofps){
				gp.fps();
			}
			if(stmenu){
				startMenu();
			}
			if(playerMenu){
				playerMenu();
			}
			if(startGame){
				startGame();
			}
			
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void startGame(){
		
	}
	
	public void playerMenu(){
		if(b){
			gp.clearButtons();
			gp.addButton(new Button(new Color(200, 0, 0), new Color(255, 0, 0), Color.WHITE, "Start", (int) (screenwidth/2 - (screenwidth * .05)),(int) (screenheight/2 - (screenheight * .05)),(int) (screenwidth * .05) * 2, (int) (screenheight * .05) * 2));
			stmenu = true;
			gp.dofps = true;
			playerMenu = false;
			frame.revalidate();
			gp.cross.changeColor(Color.GREEN);
		}
		if(a){
			int pmc = checkPlayerMenuCross();
			if(pmc == 1){
				numberOfPlayers = 1;
			}else if(pmc == 2 && c2c){
				numberOfPlayers = 2;
			}else if(pmc == 3 && c3c){
				numberOfPlayers = 3;
			}else if(pmc == 4 && c4c){
				numberOfPlayers = 4;
			}else{
				numberOfPlayers = 0;
			}
			if(numberOfPlayers > 0){
				gp.clearButtons();
				playerMenu = false;
				frame.revalidate();
				gp.cross.changeColor(Color.GREEN);
				gp.drawCross = false;
				startGame = true;
				gp.loadImages(numberOfPlayers);
			}
		}
	}
	
	public void startMenu(){
		if(a){
			if(checkCross()){
				stmenu = false;
				gp.dofps = false;
				gp.clearButtons();
				gp.createPlayerButtons(c1c, c2c, c3c, c4c);
				playerMenu = true;
				frame.revalidate();
				gp.cross.changeColor(Color.BLACK);
			}
		}
	}
	public boolean checkCross(){
		if(gp.cross.x > gp.buttons.get(0).x && gp.cross.x < gp.buttons.get(0).x + gp.buttons.get(0).width && gp.cross.y > gp.buttons.get(0).y && gp.cross.y < gp.buttons.get(0).y + gp.buttons.get(0).height){
			return true;
		}else{
			return false;
		}
		
	}
	
	public int checkPlayerMenuCross(){
		if(gp.cross.x <= screenwidth / 4){
			return 1;
		}
		if(gp.cross.x <= (screenwidth / 4) * 2){
			return 2;
		}
		if(gp.cross.x < (screenwidth / 4) * 3){
			return 3;
		}
		return 4;
	}
	
	public void controllerConfig(){
		c1.addXboxControllerListener(new XboxControllerAdapter(){
			public void back(boolean b){
				c1.release();
				c2.release();
				c3.release();
				c4.release();
				System.exit(0);
			}
			public void start(boolean b){
				start = b;
			}
			public void buttonA(boolean b){
				a = b;
			}
			public void buttonB(boolean x){
				b = x;
			}
			public void buttonX(boolean b){
				x = b;
			}
			public void buttonY(boolean b){
				y = b;
			}
			
			public void leftThumbDirection(double direction){
				if((direction <= 22 && direction >= 0) || direction >= 338){
					//up
					dr = "u";
				}else if(direction < 67 && direction > 22){
					//upright
					dr = "ur";
				}else if(direction <= 112  && direction >= 67){
					//right
					dr = "r";
				}else if(direction < 157 && direction > 112){
					//rightdown
					dr = "rd";
				}else if(direction <= 202 && direction >= 157){
					//down
					dr = "d";
				}else if(direction < 247 && direction > 202){
					//downleft
					dr = "dl";
				}else if(direction <= 292 && direction >= 247){
					//left
					dr = "l";
				}else if(direction < 338 && direction > 292){
					//leftup
					dr = "lu";
				}
				gp.cross.setString(dr);
			}
			
			public void leftThumbMagnitude(double mag){
				magnitude = mag;
				if(magnitude > .25 && magnitude < .5){
					magnitude = 1;
				}else if(magnitude > .5 && magnitude < .75){
					magnitude = 2;
				}else if(magnitude > .75){
					magnitude = 3;
				}else{
					magnitude = 0;
				}
				
			}
		});
	}
	
	public void moveCross(){
		if(dr.equals("u")){
			if(gp.cross.y > 3){
				gp.cross.y = gp.cross.y - (int) magnitude;
			}
		}
		if(dr.equals("d")){
			if(gp.cross.y < screenheight - 3){
				gp.cross.y = gp.cross.y + (int) magnitude;
			}
		}
		if(dr.equals("l")){
			if(gp.cross.x > 3){
				gp.cross.x = gp.cross.x - (int) magnitude;
			}
		}
		if(dr.equals("r")){
			if(gp.cross.x < screenwidth - 3){
				gp.cross.x = gp.cross.x + (int) magnitude;
			}
		}
		
		if(dr.equals("ur")){
			if(gp.cross.y > 3){
				gp.cross.y = gp.cross.y - (int) magnitude;
			}
			if(gp.cross.x < screenwidth - 3){
				gp.cross.x = gp.cross.x + (int) magnitude;
			}
		}
		if(dr.equals("rd")){
			if(gp.cross.x < screenwidth - 3){
				gp.cross.x = gp.cross.x + (int) magnitude;
			}
			if(gp.cross.y < screenheight - 3){
				gp.cross.y = gp.cross.y + (int) magnitude;
			}
		}
		if(dr.equals("dl")){
			if(gp.cross.y < screenheight - 3){
				gp.cross.y = gp.cross.y + (int) magnitude;
			}
			if(gp.cross.x > 3){
				gp.cross.x = gp.cross.x - (int) magnitude;
			}
		}
		if(dr.equals("lu")){
			if(gp.cross.x > 3){
				gp.cross.x = gp.cross.x - (int) magnitude;
			}
			if(gp.cross.y > 3){
				gp.cross.y = gp.cross.y - (int) magnitude;
			}
		}
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
