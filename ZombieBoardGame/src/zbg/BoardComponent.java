package zbg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.*;

public abstract class BoardComponent extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;
	private int spaceSize;

	public BoardComponent(int x, int y, int width, int height, Color c, int ss){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
		spaceSize = ss;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillRect(this.x, this.y, this.width, this.height);
		if(this instanceof Space){
			int xx = (width / 2) + x -25;
			int yy = (height / 2) + y + 5;
			g.setColor(Color.BLACK);
			Font font = new Font("New", Font.BOLD, 17);
			g.setFont(font);
			g.drawString(this.getText(), xx, yy);
		}
	}
	
	public String getText(){
		String s = "";
		return s;
	}
	
	public Rectangle toRectangle(){
		return new Rectangle(x, y, width, height);
	}
	
	public void move(int numberOfSpaces, int sNumber, BoardPanel b){
		BoardPanel bp = b;
		int spaceNumber = sNumber;
		for(int i = 0; i < numberOfSpaces; i++){
			if(spaceNumber >= 1 && spaceNumber < 13){
				for(int j = 0; j < spaceSize; j++){
					x = x - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				
			}else if(spaceNumber == 13 || spaceNumber == 14){
				for(int j = 0; j < spaceSize; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber >= 15 && spaceNumber < 27){
				for(int j = 0; j < spaceSize; j++){
					x = x + 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber == 27 || spaceNumber == 28){
				for(int j = 0; j < spaceSize; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber >= 29 && spaceNumber < 41){
				for(int j = 0; j < spaceSize; j++){
					x = x - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber == 41 || spaceNumber == 42){
				for(int j = 0; j < spaceSize; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber >= 43 && spaceNumber < 55){
				for(int j = 0; j < spaceSize; j++){
					x = x + 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber == 55 || spaceNumber == 56){
				for(int j = 0; j < spaceSize; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else if(spaceNumber >= 57 && spaceNumber < 69){
				for(int j = 0; j < spaceSize; j++){
					x = x - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber++;
				bp.repaint();
			}else{
				for(int j = 0; j < 42; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				y = y + (spaceSize*8) + 85;
				x = x + spaceSize * 12;
				for(int j = 0; j < 43; j++){
					y = y - 1;
					bp.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				spaceNumber = 1;
				bp.repaint();
			}
		}
		if(this instanceof Player){
			Player p = (Player) this;
			p.changeSpaceNumber(spaceNumber);
		}else{
			Zombie z = (Zombie) this;
			z.changeSpaceNumber(spaceNumber);
		}
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
