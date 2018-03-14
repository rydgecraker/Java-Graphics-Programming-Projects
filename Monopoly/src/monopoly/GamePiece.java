package monopoly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class GamePiece extends JPanel{
	
	private int number;
	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;
	private int money;
	ArrayList<Place> properties;
	
	public GamePiece(int number, int x, int y, int width, int height){
		this.number = number;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		properties = new ArrayList<Place>();
		money = 1500;
	}
	
	public void draw(Graphics g){
		g.setColor(this.getColor());
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public abstract Color getColor();
	
	public abstract String getName();
	
	public abstract int getPlayerNumber();
	
	public abstract void changeSpaceNumber(int x);
	
	public abstract int getSpaceNumber();
	
	public void move(int roll, int spaceWidth, int spaceHeight, MainBoardPanel mbp){
		int spaceNumber = this.getSpaceNumber();
		for(int j = 0; j < roll; j++){
			if(spaceNumber >= 0 && spaceNumber < 10){
				for(int i = 0; i < spaceWidth; i++){
					x--;
					mbp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
			}else if(spaceNumber >= 10 && spaceNumber < 20){
				for(int i = 0; i < spaceHeight; i++){
					y--;
					mbp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(spaceNumber == 19){
					for(int ii = 0; ii < 7; ii++){
						y--;
						mbp.repaint();
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				spaceNumber++;
			}else if(spaceNumber >= 20 && spaceNumber < 30){
				for(int i = 0; i < spaceWidth; i++){
					x++;
					mbp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
			}else if(spaceNumber >= 30 && spaceNumber < 40){
				for(int i = 0; i < spaceHeight; i++){
					y++;
					mbp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(spaceNumber == 30){
					for(int ii = 0; ii < 7; ii++){
						y++;
						mbp.repaint();
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				if(spaceNumber == 39){
					spaceNumber = 0;
				}else{
					spaceNumber++;
				}
			}
		}
		this.changeSpaceNumber(spaceNumber);
		mbp.repaint();
	}
	
	public abstract boolean checkIfInJail();
	
	public abstract void changeJail();
	
	public abstract int getJailCountdown();
	
	public void changeMoney(int x){
		money = money + x;
	}
	
	public int getMoney(){
		return money;
	}
	
	public ArrayList<Place> getProperties(){
		return properties;
	}
	
	public void addProperty(Property p){
		properties.add(p);
	}
	
	public void addRaiload(Railroad r){
		properties.add(r);
	}
	
	public void addUtility(Utility u){
		properties.add(u);
	}
	
	public boolean equals(Object o){
		if(o instanceof Player){
			Player p = (Player) o;
			if(this.getPlayerNumber() == p.getPlayerNumber()){
				return true;
			}else{
				return false;
			}
		}else{
			Computer c = (Computer) o;
			if(this.getPlayerNumber() == c.getPlayerNumber()){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public void bankrupt(GamePiece gp){
		for(int i = 0; i < this.properties.size(); i++){
			gp.properties.add(properties.get(0));
			properties.remove(0);
		}
		gp.changeMoney(this.getMoney());
		this.changeMoney(-1 * this.getMoney());
	}

}
