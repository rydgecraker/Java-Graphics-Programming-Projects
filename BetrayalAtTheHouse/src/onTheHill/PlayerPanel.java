package onTheHill;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPanel {
	
	public int x;
	public int y;
	public int width;
	public int height;
	public Player player;
	
	public static final Color bgColor = new Color(255, 228, 181);
	
	public PlayerPanel(int x, int y, int width, int height, Player p){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.player = p;
		init();
	}
	
	public void init(){
		player.character.x = (int) (width * .05);
		player.character.y = (int) (height * .01);
		player.character.width = (int) (width * .9);
		player.character.height = (int) ((height * .39)); 
		//Height * .15 for each card and to have a fourth slot use .1 for space if necessary
		resetImageSizeAndWhatnot();
	}
	
	public void resetImageSizeAndWhatnot(){
		player.character.rescaleImage();
		player.character.changeFonts(10);
	}
	
	public void draw(Graphics g){
		g.setColor(bgColor);
		g.fillRect(x, y, width, height);
		player.draw(g);
		
	}

}
