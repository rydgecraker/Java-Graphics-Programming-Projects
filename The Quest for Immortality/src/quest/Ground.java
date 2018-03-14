package quest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground extends GameObject{

	public enum GroundType{GRASS, PATHUPDOWN, PATHLEFTRIGHT, PATHCROSS, SAND, SNOW}
	
	
	private GroundType type;
	private BufferedImage img;

	public Ground(int x, int y, GroundType type, BufferedImage img){
		super(x, y, 50, 50);
		this.type = type;
		this.img = img;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, relX, relY, null);
	}
	

//	public void run() {
//		File filepath;
//		if(type.equals(GroundType.GRASS)){
//			filepath = new File("images/Terrain/Grass.jpg");
//		}else if(type.equals(GroundType.PATHUPDOWN)){
//			filepath = new File("images/Terrain/Path(UpDown).jpg");
//		}else if(type.equals(GroundType.PATHLEFTRIGHT)){
//			filepath = new File("images/Terrain/Path(LeftRight).jpg");
//		}else if(type.equals(GroundType.PATHCROSS)){
//			filepath = new File("images/Terrain/Path(Crossroads).jpg");
//		}else if(type.equals(GroundType.SAND)){
//			filepath = new File("images/Terrain/Sand.jpg");
//		}else if(type.equals(GroundType.SNOW)){
//			filepath = new File("images/Terrain/Snow.jpg");
//		}else{
//			filepath = null;
//		}
//		
//		try {
//			img = ImageIO.read(filepath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public Rectangle getInteractionSpace() {
		return null;
	}
	
	public Boolean solid(){
		return false;
	}
	
}
