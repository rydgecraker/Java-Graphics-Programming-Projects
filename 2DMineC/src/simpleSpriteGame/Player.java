package simpleSpriteGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean onGround;
	private BufferedImage img;
	private int imgNumber;
	private GamePanel gp;
	private ArrayList<Ground> ground;
	private boolean tools;
	
	public Player(int x, int y, int width, int height, GamePanel gp, ArrayList<Ground> ground){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		onGround = false;
		imgNumber = 0;
		getImage();
		this.gp = gp;
		this.ground = ground;
		tools = false;
	}
	
	public void changeImgNumber(int i){
		imgNumber = i;
	}
	
	public void draw(Graphics g){
		getImage();
		g.drawImage(img, x, y, null);
	}
	
	public void move(int x){
		this.x = this.x + x;
		gp.repaint();
	}
	
	public void jump(){
		onGround = true;
		if(y > 25){
			y = y - 5;
		}
	 
	}
	
	public void gravity(){
		if(onGround == false){
			if(y > 10 && y < 770){
				y = y + 10;
				for(int i = 0; i < ground.size(); i++){
					if(this.returnRectange().intersects(ground.get(i).returnRectange())){
						onGround = true;
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else if(y >= 775){
				y = 770;
			}
		}
	}
	
	
	public void getImage(){
		if(imgNumber == 0){ 
			File file = new File("Images/facingRight0.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(imgNumber == 1){
			File file = new File("Images/facingRight1.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(imgNumber == 2){
			File file = new File("Images/facingRight2.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(imgNumber == 3){
			File file = new File("Images/facingLeft0.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(imgNumber == 4){
			File file = new File("Images/facingLeft1.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(imgNumber == 5){
			File file = new File("Images/facingLeft2.png");
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void changeGround(boolean b){
		onGround = b;
	}
	
	public boolean onGround(){
		return onGround;
	}
	
	public Rectangle returnRectange(){
		return new Rectangle(x, y, width, height);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void tools(){
		if(tools == false){
			tools = true;
		}else{
			tools = false;
		}
	}
	
	public boolean hasTools(){
		return tools;
	}
	
}
