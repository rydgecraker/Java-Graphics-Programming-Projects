package quest;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Water extends GameObject {
	
	BufferedImage img;
	BufferedImage img2;
	BufferedImage temp;

	public Water(int x, int y, BufferedImage img1, BufferedImage img2) {
		super(x, y, 50, 50);
		this.img = img1;
		this.img2 = img2;
	}

	public void draw(Graphics g) {
		g.drawImage(img, relX, relY, null);
	}
	
	public void changeImage(){
		temp = img2;
		img2 = img;
		img = temp;
	}

	public Rectangle getInteractionSpace() {
		return null;
	}

}
