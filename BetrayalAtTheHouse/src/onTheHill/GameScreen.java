package onTheHill;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameScreen extends JPanel{
	
	public int x;
	public int y;
	public int width;
	public int height;
	public PlayerPanel playerPanel;
	
	public GameScreen(int x, int y, Dimension resolution, PlayerPanel p){
		this.x = x;
		this.y = y;
		this.width = resolution.width;
		this.height = resolution.height;
		this.playerPanel = p;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		playerPanel.draw(g);
	}

}
