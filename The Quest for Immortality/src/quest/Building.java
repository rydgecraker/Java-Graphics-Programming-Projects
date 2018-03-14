package quest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import quest.Ground.GroundType;

public class Building extends GameObject implements Runnable{

	public enum Dir{DOWN, UP, LEFT, RIGHT}
	
	
	private Dir facing;
	private BufferedImage img;
	public File filepath;
	
	public Building(int x, int y, Direction d, File filepath) {
		super(x, y, d.width, d.height);
		this.filepath = filepath;
		facing = d.d;
		Thread th = new Thread(this);
		th.start();
	}

	public void draw(Graphics g) {
		g.drawImage(img, relX, relY, null);
	}

	public Rectangle getInteractionSpace() {
		if(facing.equals(Dir.DOWN)){
			return new Rectangle(x + 90, y + 80, 20, 40);
		}else if(facing.equals(Dir.UP)){
			return new Rectangle();
		}else if(facing.equals(Dir.LEFT)){
			return new Rectangle();
		}else if(facing.equals(Dir.RIGHT)){
			return new Rectangle();
		}else{
			return null;
		}
		
	}

	public void run() {
		if(filepath == null){
			if(facing.equals(Dir.DOWN)){
				filepath = new File("images/houseDown.png");
			}else if(facing.equals(Dir.UP)){
				filepath = new File("images/houseUp.png");
			}else if(facing.equals(Dir.RIGHT)){
				filepath = new File("images/houseRight.png");
			}else{
				filepath = new File("images/houseLeft.png");
			}
		}
		
		try {
			img = ImageIO.read(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean solid() {
		return true;
	}

}
