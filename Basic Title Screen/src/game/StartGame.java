package game;

import java.awt.*;
import java.util.ArrayList;

import engine.ButtonArea;
import engine.RCButton;

import javax.swing.*;

public class StartGame implements Runnable{

	public static float version = .1f;
	private JFrame frame;
	private GamePanel panel;
	private Dimension resolution;
	private Thread gameThread;
	
	private boolean running;
	
	public StartGame(){
		init();
		initFrame();
		initThread();
		initButtons();
		gameThread.start();
	}
	
	public void init(){
		resolution = Toolkit.getDefaultToolkit().getScreenSize();
		running = true;
	}
	
	public void initButtons(){
		panel.titleScreen = new ButtonArea(0, (int)(resolution.height * .75), resolution.width, (int)(resolution.height * .25), Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, new Font("Arial", Font.BOLD, 35), .5, 5, 5, 5);
		panel.titleScreen.addButton("New Game");
		panel.titleScreen.addButton("Load Game");
		panel.titleScreen.addButton("Options");
		panel.titleScreen.addButton("Quit");
		
		panel.optionsMenu = new ButtonArea(0, (int)(resolution.height * .75), resolution.width, (int)(resolution.height * .25), Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, new Font("Arial", Font.BOLD, 35), .5, 5, 5, 5);
		panel.optionsMenu.addButton("Turn Sounds Off");
		panel.optionsMenu.addButton("Clear Save Data");
		panel.optionsMenu.addButton("Back");
	}
	
	public void initThread(){
		gameThread = new Thread(this);
	}
	
	public void initFrame(){
		frame = new JFrame();
		frame.setSize(resolution);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		panel = new GamePanel();
		panel.setBackground(Color.BLACK);
		panel.setFocusable(true);
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		panel.addKeyListener(panel);
		
		frame.setContentPane(panel);
		frame.revalidate();
		frame.setVisible(true);
	}

	public void run() {
		while(running){
			panel.repaint();
			panel.doEvents();
		}
	}
	
}
