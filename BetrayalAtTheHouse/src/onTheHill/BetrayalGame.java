package onTheHill;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class BetrayalGame implements Runnable{
	
	public Thread th;
	public Dimension resolution;
	public int numPlayersTotal;
	public int numHumanPlayers;
	public int numComPlayers;
	public ArrayList<Player> players;
	public ArrayList<Player> computers;
	public ArrayList<PlayerPanel> panels;
	public int currentPlayer;
	
	public JFrame frame;
	
	public GameScreen gs;
	
	public boolean running;
	
	public BetrayalGame(Dimension resolution, int numHumanPlayers, int numComPlayers, ArrayList<Player> players, ArrayList<Player> computers, JFrame frame){
		this.resolution = resolution;
		this.numPlayersTotal = numHumanPlayers + numComPlayers;
		this.numHumanPlayers = numHumanPlayers;
		this.numComPlayers = numComPlayers;
		this.players = players;
		this.computers = computers;
		this.frame = frame;
		init();
	}
	
	public void init(){
		panels = new ArrayList<PlayerPanel>();
		currentPlayer = 0;
		for(int i = 0; i < numHumanPlayers; i++){
			panels.add(new PlayerPanel(0, 0, (int) (resolution.width * .2), resolution.height, players.get(i)));
		}
		for(int i = 0; i < numComPlayers; i++){
			panels.add(new PlayerPanel(0, 0, (int) (resolution.width * .2), resolution.height, computers.get(i)));
		}
		running = true;
		
		gs = new GameScreen(0, 0, resolution, panels.get(0));
		gs.setSize(resolution);
		gs.setBackground(Color.BLACK);
		gs.setFocusable(true);
		
		frame.setContentPane(gs);
		
		th = new Thread(this);
		th.start();
	}

	public void run() {
		while(running){
			gs.repaint();
			gs.requestFocus();
		}
	}

}
