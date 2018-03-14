package com.forkBombSquad.asteroidAttack;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;


public class Game implements Runnable {
	
	private static final String	GAME_TITLE	= "Asteroid Attack";
	
	public int					screenWidth, screenHeight;
	public JFrame				frame;
	public Thread				gameThread;
	public boolean				gameRunning;
	
	public long					dt;
	
	public long					currentTime, previousTime;
	
	public GamePanel			panel;
	
	public float				maxFPS		= 240;
	public float				msBetweenFrames;
	
	public float				currentFPStime, fps, frameCount;
	
	
	public Game() {
		super();
	}
	
	
	public static void main(String[] args) {
		Game g = new Game();
		g.initGame();
		g.startGame();
	}
	
	
	public void initGame() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		// screenWidth = 600;
		// screenHeight = 800;
		
		// Initializes the frame and sets it's resolution to the screen resolution
		frame = new JFrame(GAME_TITLE);
		// Ensures the frame is at full size and then gets rid of bars on the side
		frame.setState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(screenWidth, screenHeight);
		frame.setLocationRelativeTo(null);
		// initializes all components that go inside the frame (such as the game panel)
		initPanel();
		
		// makes the frame visible and refreshes it, painting it to the screen for the first time
		frame.setVisible(true);
		frame.revalidate();
		gameRunning = true;
		
		// Initializes threads and tells them to run in this Class
		gameThread = new Thread(this);
	}
	
	
	private void initPanel() {
		panel = new GamePanel(this);
		panel.setFocusable(true);
		// panel.addMouseListener(panel);
		// panel.addMouseMotionListener(panel);
		panel.addKeyListener(panel);
		// panel.addMouseWheelListener(panel);
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	
	public void startGame() {
		previousTime = 0L;
		currentTime = 0L;
		currentFPStime = 0.0f;
		frameCount = 0.0f;
		msBetweenFrames = 1000000000L / maxFPS;
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		previousTime = System.nanoTime();
		while (gameRunning) {
			currentTime = System.nanoTime();
			if ((currentTime - previousTime) >= msBetweenFrames) {
				calculateDeltaTime();
				gameLoop();
			}
		}
	}
	
	
	private void calculateDeltaTime() {
		dt = (currentTime - previousTime);
		previousTime = currentTime;
	}
	
	
	private void gameLoop() {
		panel.update(dt * 0.000000001f);
		panel.repaint();
		panel.requestFocus();
		calcFps();
	}
	
	
	private void calcFps() {
		currentFPStime += (dt * 0.000000001f);
		frameCount++;
		if (currentFPStime >= 1.0f) {
			fps = frameCount;
			currentFPStime = 0.0f;
			frameCount = 0.0f;
			// System.out.println(fps);
		}
	}
	
	
	public void endGame() {
		gameRunning = false;
		System.exit(0);
	}
	
}
