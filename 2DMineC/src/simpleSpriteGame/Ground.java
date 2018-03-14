package simpleSpriteGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ground {

	private boolean broken;
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage img;
	private int blockType;
	
	public Ground(int x, int y, int width, int height, int type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		broken = false;
		decideImage();
		blockType = type;
	}
	
	public void draw(Graphics g){
		decideImage();
		g.drawImage(img, x, y, null);
	}
	
	public void breakBlock(){
		broken = true;
	}
	
	public boolean isBroken(){
		return broken;
	}
	
	public void decideImage(){
		File file;
		if(blockType == 0){
			file = new File("Images/block0.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(blockType == 1){
			file = new File("Images/block1.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(blockType == 2){
			file = new File("Images/block2.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Rectangle returnRectange(){
		return new Rectangle(x, y, width, height);
	}
	
}
