package onTheHill;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Betrayal implements Runnable{
	
	public Thread th;
	public JFrame frame;
	public ChooseNumPlayerScreen humanPlayers;
	public ChooseNumPlayerScreen2 computerPlayers;
	public CharacterChooser charChoose;
	public YesOrNoCharacterPanel yn;
	public Dimension resolution;
	public ChoosePlayerListener cpl;
	public int numHumanPlayers;
	public int numComPlayers;
	
	public int currentPlayer;
	
	public boolean hp, cp, charCh, charYesNo;
	public boolean running;
	
	public ArrayList<Player> hPlayers = new ArrayList<Player>();
	public ArrayList<Player> cPlayers = new ArrayList<Player>();
	
	public Betrayal(JFrame frame, Dimension resolution){
		this.frame = frame;
		this.resolution = resolution;
		hp = true;
		running = true;
		initVariables();
		
		th.start();
	}
	
	public void initVariables(){
		th = new Thread(this);
		
		humanPlayers = new ChooseNumPlayerScreen("How Many Human Players?", 0, 0, resolution.width, resolution.height);
		humanPlayers.setBackground(Color.BLACK);
		humanPlayers.setSize(resolution);
		humanPlayers.setPreferredSize(resolution);
		
		charChoose = new CharacterChooser(0, 0, resolution.width, resolution.height, "Player 1: Choose Your Character", 1);
		charChoose.setBackground(Color.BLACK);
		charChoose.setSize(resolution);
		charChoose.setPreferredSize(resolution);
		
		charChoose.setFocusable(true);
		humanPlayers.setFocusable(true);
		
		cpl = new ChoosePlayerListener(this);
		
		humanPlayers.addMouseListener(cpl);
		humanPlayers.addMouseMotionListener(cpl);
		charChoose.addMouseListener(cpl);
		charChoose.addMouseMotionListener(cpl);
		
		currentPlayer = 1;
		
		frame.setContentPane(humanPlayers);
		frame.revalidate();
		humanPlayers.repaint();
	}

	public void run() {
		while(running){
			if(hp){
				humanPlayers.menuEvents();
				humanPlayers.repaint();
				humanPlayers.requestFocus();
			}else if(cp){
				computerPlayers.menuEvents();
				computerPlayers.repaint();
				computerPlayers.requestFocus();
			}else if(charCh){
				charChoose.ynb = false;
				charChoose.menuEvents();
				charChoose.repaint();
				charChoose.requestFocus();
			}else if(charYesNo){
				charChoose.ynb = true;
				charChoose.ynStuff(yn);
				charChoose.repaint();
				charChoose.requestFocus();
			}
		}
		
		new BetrayalGame(resolution, numHumanPlayers, numComPlayers, hPlayers, cPlayers, frame);
	}

}
