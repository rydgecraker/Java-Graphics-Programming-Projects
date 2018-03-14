package testingPackage;

import java.awt.*;
import javax.swing.*;

public class GameRunner {
	//Runs The Title Screen
	public JFrame frame;
	public GameContentPanel contentPanel;
	public Dimension screenSize;
	public int frameHeight;
	public int frameWidth;
	public Boolean newGame;
	public Boolean loadGame;
	
	public GameRunner(){
		//Initilize Variables
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		newGame = false;
		loadGame = false;
		frame = new JFrame();
		frame.setSize(1500, 1000);
		frameWidth = 1500;
		frameHeight = 1000;
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new GameContentPanel(0, 0, 1500, 1000, Color.BLACK);
		
		frame.setContentPane(contentPanel);
		addButtons();
		
		frame.revalidate();
		contentPanel.revalidate();
		MouseListeners1 ml1 = new MouseListeners1(contentPanel, frame, this);
		contentPanel.addMouseListener(ml1);
		contentPanel.addMouseMotionListener(ml1);
		
		runGame();
	}
	
	public void addButtons(){
		//makes the content panel add buttons
		contentPanel.addButtons();
	}
	
	public void runGame(){
		//Main menu loop. Game starts when loop is broken.
		while(!newGame && !loadGame){
			resizing();
		}
		
		if(newGame){
			//Begin New Game
			new Game(frame);
		}else if(loadGame){
			//Begin Loading Game
		}
		
	}
	
	public void resizing(){
		//Resizes buttons to be the same percentages as the window. Sometimes it's a bit slow.
		if(frame.getSize().width != frameWidth || frame.getSize().height != frameHeight){
			contentPanel.setWidth(frame.getWidth());
			contentPanel.setHeight(frame.getHeight());
			frame.revalidate();
			frame.repaint();
			frameWidth = frame.getWidth();
			frameHeight = frame.getHeight();
			contentPanel.repaint();
		}
	}

}
