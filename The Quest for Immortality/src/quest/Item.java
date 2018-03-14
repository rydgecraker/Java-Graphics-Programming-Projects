package quest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Item {
	
	public enum ItemType{WEAPON, STATUSITEM, SPELLBOOK, KEYITEM}
	
	public ItemType type;
	public String name;
	public File filepath;
	public BufferedImage img;
	public int x;
	public int y;
	public int width;
	public int height;
	public int relX;
	public int relY;
	
	public Item(String name, int x, int y, int width, int height, File f, ItemType type){
		this.type = type;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.filepath = f;
		this.relX = x;
		this.relY = y;
		getImage();
	}
	
	public void draw(Graphics g){
		g.drawImage(img, this.relX, this.relY, null);
	}
	
	public void getImage(){
		if(filepath != null){
			try {
				img = ImageIO.read(filepath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
