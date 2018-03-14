package simpleSpriteGame;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	ArrayList<Ground> ground;
	Player p;
	
	public GamePanel(){
		ground = new ArrayList<Ground>();
		int x = 0;
		int y = 400;
		for(int i = 0; i < 10; i++){
			ground.add(new Ground(x, y, 100, 100, 0));
			x += 100;
		}
		x = 0;
		y = 500;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 10; j++){
				ground.add(new Ground(x, y, 100, 100, 1));
				x += 100;
			}
			y += 100;
			x = 0;
		}
		y = 800;
		x = 0;
		for(int i = 0; i < 10; i++){
			ground.add(new Ground(x, y, 100, 100, 2));
			x += 100;
		}
		p = new Player(425, 300, 50, 100, this, ground);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < ground.size(); i++){
			ground.get(i).draw(g);
		}
		p.draw(g);
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public ArrayList<Ground> getGround(){
		return ground;
	}
	
}
