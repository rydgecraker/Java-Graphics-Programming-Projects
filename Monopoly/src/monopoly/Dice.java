package monopoly;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Dice extends JPanel {

	private int x;
	private int y;
	private int width;
	private int height;
	private int roll;
	
	public Dice(int x, int y, int width, int height, int roll){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.roll = roll;
	}
	
	public int getRoll(){
		return roll;
	}
	
	public void changeRoll(int roll){
		this.roll = roll;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(this.x, this.y, this.width, (int) (this.height * .1));
		g.fillRect(this.x, this.y, (int) (this.width * .1), height);
		g.fillRect((int) (this.x + (height * .9)), this.y, (int) (width * .1), height);
		g.fillRect(x, (int) (this.y + (height * .9)), width ,  (int) (height * .1));
		if(roll == 1){
			g.fillRect((int) ((width/2) - width * .05) + x, (int) ((height/2) - height * .05) + y, 10, 10);
		}else if(roll == 2){
			g.fillRect((int) (14 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
		}else if(roll == 3){
			g.fillRect((int) (4 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) ((width/2) - width * .05) + x, (int) ((height/2) - height * .05) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
		}else if(roll == 4){
			g.fillRect((int) (14 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
		}else if(roll == 5){
			g.fillRect((int) (14 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) ((width/2) - width * .05) + x, (int) ((height/2) - height * .05) + y, 10, 10);
		}else if(roll == 6){
			g.fillRect((int) (14 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (4 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (14 * (height/20)) + y, 10, 10);
			g.fillRect((int) (14 * (width/20)) + x, (int) (9 * (height/20)) + y, 10, 10);
			g.fillRect((int) (4 * (width/20)) + x, (int) (9 * (height/20)) + y, 10, 10);
		}
	}
	
}
