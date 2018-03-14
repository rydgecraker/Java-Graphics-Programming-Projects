package testingPackage;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject {
	
	public int x;
	public int y;
	public int width;
	public int height;
	public int z;
	public int depth;
	public Color bg;
	public Color outline;
	public Color highlight;
	public Boolean selected;
	public boolean draw;
	
	public int playerDirectionX;
	public int playerDirectionY;
	public int playerLocationX;
	public int playerLocationY;
	public int playerLocationZ;
	
	ArrayList<Point3d[]> points;
	ArrayList<Point3d[]> staticPoints;
	//0 front
	//1 back
	//2 left
	//3 right
	//4 top
	//5 bottom
	
	public Polygon front;
	public Polygon back;
	public Polygon left;
	public Polygon right;
	public Polygon top;
	public Polygon bottom;
	
	public GameObject(int x, int y, int width, int height, int z, int depth, Color bg, Color outline, Color highlight){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.z = z;
		this.bg = bg;
		this.outline = outline;
		this.highlight = highlight;
		selected = false;
		draw = true;
			
		points = new ArrayList<Point3d[]>();
		staticPoints = new ArrayList<Point3d[]>();
		Point3d[] tempArray = new Point3d[]{new Point3d(0, 0, 0), new Point3d(0, 0, 0), new Point3d(0, 0, 0), new Point3d(0, 0, 0)};
		
		points.add(tempArray);
		points.add(tempArray);
		points.add(tempArray);
		points.add(tempArray);
		points.add(tempArray);
		points.add(tempArray);
		
		InitilizeArray();
		staticPoints = points;
		
		int[] temp = new int[]{0, 0, 0, 0};
		
		front = new Polygon(temp, temp, 4);
		back = new Polygon(temp, temp, 4);
		left = new Polygon(temp, temp, 4);
		right = new Polygon(temp, temp, 4);
		top = new Polygon(temp, temp, 4);
		bottom = new Polygon(temp, temp, 4);
	}
	
	public void draw(Graphics g){
		drawShape((Graphics2D) g);
	}
	
	private void InitilizeArray(){
		//Front
		points.get(0)[0] = new Point3d(x, y, z);
		points.get(0)[1] = new Point3d(x+width, y, z);
		points.get(0)[2] = new Point3d(x+width, y+height, z);
		points.get(0)[3] = new Point3d(x, y+height, z);
		//Back
		points.get(1)[0] = new Point3d(x+width, y, z+depth);
		points.get(1)[1] = new Point3d(x, y, z+depth);
		points.get(1)[2] = new Point3d(x, y+height, z+depth);
		points.get(1)[3] = new Point3d(x+width, y+height, z+depth);
		//left
		points.get(1)[0] = new Point3d(x, y, z+depth);
		points.get(1)[1] = new Point3d(x, y, z);
		points.get(1)[2] = new Point3d(x, y+height, z);
		points.get(1)[3] = new Point3d(x, y+height, z+depth);
		//right
		points.get(1)[0] = new Point3d(x+width, y, z);
		points.get(1)[1] = new Point3d(x+width, y, z+depth);
		points.get(1)[2] = new Point3d(x+width, y+height, z+depth);
		points.get(1)[3] = new Point3d(x+width, y+height, z);
		//top
		points.get(0)[0] = new Point3d(x, y, z+depth);
		points.get(0)[1] = new Point3d(x+width, y, z+depth);
		points.get(0)[2] = new Point3d(x+width, y, z);
		points.get(0)[3] = new Point3d(x, y, z);
		//bottom
		points.get(0)[0] = new Point3d(x, y+height, z);
		points.get(0)[1] = new Point3d(x+width, y+height, z);
		points.get(0)[2] = new Point3d(x+width, y+height, z+depth);
		points.get(0)[3] = new Point3d(x, y+height, z+depth);
	}
	
	protected abstract void drawShape(Graphics2D g);
	
	public void setLocationAndScale(int playerX, int playerY, int playerZ, int screenWidth, int screenHeight, int playerViewDistance, int playerDirectionX, int playerDirectionY){
		doLocationCalculations(playerX, playerY, playerZ, screenWidth, screenHeight, playerViewDistance);
		this.playerDirectionX = playerDirectionX;
		this.playerDirectionY = playerDirectionY;
		
		this.playerLocationX = playerX;
		this.playerLocationY = playerY;
		this.playerLocationZ = playerZ;
		
		int[] tempX = new int[4];
		int[] tempY = new int[4];
		for(int i = 0; i < 6; i++){
			for(int j=0; j<4; j++){
				tempX[j] = points.get(i)[j].getX();
				tempY[j] = points.get(i)[j].getY();
			}
			if(i == 0){
				front.xpoints = tempX;
				front.ypoints = tempY;
			}else if(i == 1){
				back.xpoints = tempX;
				back.ypoints = tempY;
			}else if(i == 2){
				left.xpoints = tempX;
				left.ypoints = tempY;
			}else if(i == 3){
				right.xpoints = tempX;
				right.ypoints = tempY;
			}else if(i == 4){
				top.xpoints = tempX;
				top.ypoints = tempY;
			}else if(i == 5){
				bottom.xpoints = tempX;
				bottom.ypoints = tempY;
			}
		}
	}
	
	private void doLocationCalculations(int playerX, int playerY, int playerZ, int screenWidth, int screenHeight, int playerViewDistance){
		
		int pointDistanceX = 0;
		int pointDistanceY = 0;
		int pointDistanceZ = 0;
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 4; j ++){
				points.get(i)[j].setX(((staticPoints.get(i)[j].getX() - playerX) + (screenWidth / 2)));
				points.get(i)[j].setY(((staticPoints.get(i)[j].getY() - playerY) + (screenHeight/ 2)));
				points.get(i)[j].setZ(((staticPoints.get(i)[j].getZ() - playerZ)));
				//actual point on screen
				pointDistanceX = staticPoints.get(i)[j].getX() - playerX;
				//Negative is Left
				pointDistanceY = staticPoints.get(i)[j].getY() - playerX;
				//Negative is down
				pointDistanceZ = staticPoints.get(i)[j].getZ() - playerZ;
				//Negative is behind
				
				//calculate X
				if(pointDistanceX < 0 && pointDistanceX >= -playerViewDistance){
					//Left half of screen
					if(pointDistanceZ <= playerViewDistance && pointDistanceZ >= -playerViewDistance){
						draw = true;
						points.get(i)[j].setX(points.get(i)[j].getX() + ((int) (Math.sqrt(pointDistanceZ + (playerViewDistance)))));
					}else{
						draw=false;
					}
				}else if(pointDistanceX > 0 && pointDistanceX <= playerViewDistance){
					//right half of screen
					if(pointDistanceZ <= playerViewDistance && pointDistanceZ >= -playerViewDistance){
						draw = true;
						points.get(i)[j].setX(points.get(i)[j].getX() - ((int) (Math.sqrt(pointDistanceZ + (playerViewDistance)))));
					}else{
						draw=false;
					}
				}else{
					draw = false;
				}
				
				//Calculate Y
				if(pointDistanceY < 0 && pointDistanceY >= -playerViewDistance){
					//bottom half of screen
					if(pointDistanceZ <= playerViewDistance && pointDistanceZ >= -playerViewDistance){
						draw = true;
						points.get(i)[j].setY(points.get(i)[j].getY() + ((int) (Math.sqrt(pointDistanceZ + (playerViewDistance))))); 
					}else{
						draw = false;
					}
				}else if(pointDistanceY > 0 && pointDistanceY <= playerViewDistance){
					//top half of screen
					if(pointDistanceZ <= playerViewDistance && pointDistanceZ >= -playerViewDistance){
						draw = true;
						points.get(i)[j].setY(points.get(i)[j].getY() - ((int) (Math.sqrt(pointDistanceZ + (playerViewDistance))))); 
					}else{
						draw = false;
					}
				}else{
					draw = false;
				}
				
			}
		}	
		
	}
	

}
