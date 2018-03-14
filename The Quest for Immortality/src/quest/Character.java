package quest;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Character{

	public int x;
	public int y;
	public int relX;
	public int relY;
	public int width;
	public int height;
	public File filepath;
	public BufferedImage img;
	
	public Character(int x, int y, int width, int height, File filepath){
		this.x = x;
		this.y = y;
		this.relX = x;
		this.relY = y;
		this.width = width;
		this.height = height;
		this.filepath = filepath;
		getImage();
	}
	
	public void draw(Graphics g){
		if(img != null){
			g.drawImage(img, relX, relY, null);
		}
	}
	
	public Rectangle getAbsoluteArea(){
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRelativeArea(){
		return new Rectangle(relX, relY, width, height);
	}
	
	public Rectangle getInteractionArea(){
		return new Rectangle(x - width, y - height, width*3, height*3);
	}
	
	public Rectangle getRelativeInteractionArea(){
		return new Rectangle(relX - width, relY - height, width*3, height*3);
	}
	
	public void getImage(){
		try {
			img = ImageIO.read(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
