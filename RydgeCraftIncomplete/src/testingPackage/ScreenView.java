package testingPackage;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class ScreenView extends JPanel{

	public int x;
	public int y;
	public int z;
	public int screenSizeX;
	public int screenSizeY;
	public int viewDistance;
	public int directionX;
	public int direcitonY;
	
	public ArrayList<GameObject> gameObjects;
	
	public ScreenView(int x, int y, int z, int screenSizeX, int screenSizeY, int viewDistance, int directionX, int directionY){
		super();
		this.screenSizeX = screenSizeX;
		this.screenSizeY = screenSizeY;
		this.x = x;
		this.y = y;
		this.z = z;
		this.viewDistance = viewDistance;
		this.directionX = directionX;
		this.direcitonY = directionY;
		gameObjects = new ArrayList<GameObject>();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < gameObjects.size(); i++){
			gameObjects.get(i).draw(g);
		}
	}
	
	
}
