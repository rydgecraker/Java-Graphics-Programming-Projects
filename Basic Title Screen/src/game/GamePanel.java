package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import engine.ButtonArea;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public ButtonArea titleScreen;
	public ButtonArea optionsMenu;
	
	private int mouseX;
	private int mouseY;
	
	public World w;
	
	public boolean ts = true;
	public boolean ng = false;
	public boolean lg = false;
	public boolean opt = false;
	
	public int worldHeight = 1000;
	public int worldWidth = 1000;
	
	public int[][] blocks = new int[worldHeight][worldWidth];
	
	
	public boolean leftClick = false;
	public boolean shift = false;
	public boolean esc = false;

	public GamePanel(){
		super();
	}
	
	public void doEvents(){
		if(ts){
			titleScreen.doSelection(mouseX, mouseY);
		}
		if(opt){
			optionsMenu.doSelection(mouseX, mouseY);
		}
		if(ng){
			//Create A Save File
			//Generate World
			//Then Load It
			ng = false;
			lg = true;
		}
		if(lg){
			//Load the game and the seed
			
			//This Will be replaced with loading later
			
			w = new World("THIS IS A TESTING SEED USING AS MANY CHARACTERS AS POSSIBLE");
			buildWorld();			
			lg = false;
		}
		if(leftClick){
			if(titleScreen.hasButtonSelected()){
				pressButton(titleScreen.getSelectedButtonId());
			}
		}
		if(esc && shift){
			ts = true;
			opt = false;
			ng = false;
			lg = false;
		}
	}
	
	private void buildWorld() {
		int currentLayer = 0;
		//First block is all air
		/*
		for(int i = 0; i < worldWidth; i++){
			blocks[currentLayer][i] = air;
		}
		*/
		//Second block is mostly air
		currentLayer++;
		/*
		for(int i = 0; i < worldWidth; i++){
			if(i == w.getSeed()[0] * 10 && i != 0){
				blocks[currentLayer][i] = someBlock;
			}else{
				blocks[currentLayer][i] = air;
			}
		}
		*/
	}

	private void pressButton(int id) {
		if(ts){
			if(id == 0){
				//New Game
				ng = true;
				ts = false;
				leftClick = false;
			}else if(id == 1){
				//Load Game
				lg = true;
				ts = false;
				leftClick = false;
			}else if(id == 2){
				//Options
				opt = true;
				ts = false;
				leftClick = false;
			}else if(id == 3){
				//Quit
				leftClick = false;
				System.exit(0);
			}
		}else if(ng){
			//New Game
			
			leftClick = false;
		}else if(lg){
			//Load Game
			
			leftClick = false;
		}else if(opt){
			//OptionsMenu
			if(id == 0){
				//Turn Sounds Off
				
				leftClick = false;
			}else if(id == 1){
				//Clear Save Data
				
				leftClick = false;
			}else if(id == 2){
				//Back
				opt = false;
				ts = true;
				leftClick = false;
			}
			
		}
		
	}

	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
		if(ts){
			titleScreen.draw(g);
		}else if(ng){
			//New Game
			//Paint Loading Bar
		}else if(lg){
			//Load Game
			
		}else if(opt){
			//OptionsMenu
			optionsMenu.draw(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ESCAPE){
			esc = true;
		}
		if(e.getKeyCode() == e.VK_SHIFT){
			shift = true;
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ESCAPE){
			esc = false;
		}
		if(e.getKeyCode() == e.VK_SHIFT){
			shift = false;
		}
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	
	public void mouseClicked(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}

	
	public void mousePressed(MouseEvent e) {
		leftClick = true;
	}

	
	public void mouseReleased(MouseEvent e) {
		leftClick = false;
	}

}
