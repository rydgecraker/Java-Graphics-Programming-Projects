package crakerrc;

import java.awt.Color;

import javax.swing.*;

import ch.aplu.xboxcontroller.*;

public class Game implements Runnable {

	public int numPlayers = 0;
	
	public boolean noController = false;
	
	public JFrame frame;
	
	public Thread gameThread;
	
	public GameScreen gs;
	
	public boolean gameOver = false;
	
	public float gameEventsTime1 = 1000;
	public float gameEventsMulti1 = 1f;
	public float gameEventsTime2 = 1000;
	public float gameEventsMulti2 = 1f;
	public float gameEventsTime3 = 1000;
	public float gameEventsMulti3 = 1f;
	public float gameEventsTime4 = 1000;
	public float gameEventsMulti4 = 1f;
	public float gameEventsTime5 = 1000;
	public float gameEventsMulti5 = 1f;
	
	public double delay = 100;
	
	public int resX;
	public int resY;
	public int sideBarSize;
	
	//Framerate
	long systemStartTime;
	long systemEndTime;
	int frameCounter;
	int fps;
	
	//Controllers
	public XboxController xc1;
	public XboxController xc2;
	public XboxController xc3;
	public XboxController xc4;
	
	//Booleans
	public boolean game;
	
	
	public Game(){
		initCont();
		init();
		initThread();
		initTextures();
		gameThread.start();
	}
	
	public void initTextures(){
		resX = frame.getWidth();
		resY = frame.getHeight();
		XboxController[] array = new XboxController[4];
		array[0] = xc1;
		array[1] = xc2;
		array[2] = xc3;
		array[3] = xc4;
		gs.setWidthAndHeight(resX, resY, array);
		
	}
	
	public void initCont(){
		xc1 = new XboxController(1);
		xc2 = new XboxController(2);
		xc3 = new XboxController(3);
		xc4 = new XboxController(4);
		
		if(xc1.isConnected()){
			numPlayers = 1;
		}
		if(xc2.isConnected()){
			numPlayers = 2;
		}
		if(xc3.isConnected()){
			numPlayers = 3;
		}
		if(xc4.isConnected()){
			numPlayers = 4;
		}
		
		if(numPlayers == 0){
			numPlayers = 1;
			noController = true;
		}
		
		numPlayers = 4;
		
	}
	
	public void init(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
		gs = new GameScreen(Color.BLACK, numPlayers, this, noController);
		
		frame.setContentPane(gs);
		
		frame.revalidate();
		frame.setVisible(true);
	}
	
	public void initThread(){
		gameThread = new Thread(this);
		game = true;
		frameCounter = 0;
		fps = 0;
	}

	public void run() {
		systemStartTime = System.currentTimeMillis();
		double timeStart1 = System.currentTimeMillis();
		double gameTime1 = 0.0;
		double timeStart2 = System.currentTimeMillis();
		double gameTime2 = 0.0;
		double timeStart3 = System.currentTimeMillis();
		double gameTime3 = 0.0;
		double timeStart4 = System.currentTimeMillis();
		double gameTime4 = 0.0;
		double timeStart5 = System.currentTimeMillis();
		double gameTime5 = 0.0;
		
		double inputTimerStart = System.currentTimeMillis();
		double inputTimerEnd = System.currentTimeMillis();
		while(game){
			gs.repaint();
			
			gameOver = gs.checkForGO();
			gs.checkForScores();
			
			systemEndTime = System.currentTimeMillis();
			inputTimerEnd = System.currentTimeMillis();
			double tim = inputTimerEnd - inputTimerStart;
			if(tim > delay && !gameOver){
				gs.doPlayerInput();
				inputTimerStart = System.currentTimeMillis();
			}
			gameTime1 = System.currentTimeMillis();
			gameTime2 = System.currentTimeMillis();
			gameTime3 = System.currentTimeMillis();
			gameTime4 = System.currentTimeMillis();
			gameTime5 = System.currentTimeMillis();
			frameCounter++;
			
			float timeDifference = systemEndTime - systemStartTime;
			double tdif1 = gameTime1 - timeStart1;
			double tdif2 = gameTime2 - timeStart2;
			double tdif3 = gameTime3 - timeStart3;
			double tdif4 = gameTime4 - timeStart4;
			double tdif5 = gameTime5 - timeStart5;
			
			int p1GridNum = gs.players.get(0).grid.playerNumber;
			int p2GridNum = gs.players.get(1).grid.playerNumber;
			int p3GridNum = gs.players.get(2).grid.playerNumber;
			int p4GridNum = gs.players.get(3).grid.playerNumber;
			
			if(tdif1 >= (gameEventsTime1 * gameEventsMulti1) && !gameOver){
				gs.doEvents(0);
				gameTime1 = 0;
				timeStart1 = System.currentTimeMillis();
			}
			if(tdif2 >= (gameEventsTime2 * gameEventsMulti2) && !gameOver){
				if(p2GridNum != p1GridNum){
					gs.doEvents(1);
					gameTime2 = 0;
					timeStart2 = System.currentTimeMillis();
				}
			}
			if(tdif3 >= (gameEventsTime3 * gameEventsMulti3) && !gameOver){
				
				if(p3GridNum != p1GridNum && p3GridNum != p2GridNum){
					gs.doEvents(2);
					gameTime3 = 0;
					timeStart3 = System.currentTimeMillis();
				}
			}
			if(tdif4 >= (gameEventsTime4 * gameEventsMulti4) && !gameOver){
				if(p4GridNum != p1GridNum && p4GridNum != p2GridNum && p4GridNum != p3GridNum){
					gs.doEvents(3);
					gameTime4 = 0;
					timeStart4 = System.currentTimeMillis();
				}
			}
			if(tdif5 >= (gameEventsTime5 * gameEventsMulti5) && !gameOver){
				if(gs.playWithoutMe){
					gs.doEventsWithoutMe(new int[]{p1GridNum, p2GridNum, p3GridNum, p4GridNum});
					gameTime5 = 0;
					timeStart5 = System.currentTimeMillis();
				}
			}
			
			
			if(timeDifference >= 1000){
				fps = frameCounter;
				gs.fps = fps;
				frameCounter = 0;
				systemStartTime = System.currentTimeMillis();
			}
		}
	}
	
	
}
