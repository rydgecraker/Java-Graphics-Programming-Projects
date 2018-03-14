package testingPackage;

import java.awt.Color;

import javax.swing.*;

public class Game {
	//Runs the game and all of it's components
	JFrame frame;
	ScreenView screen;
	
	
	public Game(JFrame frame){
		screen = new ScreenView(0, 0, 0, frame.getSize().width, frame.getSize().height, (int) Math.pow(frame.getSize().height, 2), 0, 0);
		screen.setBackground(Color.BLACK);
		frame.setContentPane(screen);
		frame.revalidate();
		screen.gameObjects.add(new Block(20, 20, 100, 100, 20, 100, Color.RED, Color.WHITE, Color.BLUE));
		screen.gameObjects.get(0).setLocationAndScale(0, 0, 0, frame.getSize().width, frame.getSize().width, (int) Math.pow(frame.getSize().height, 2), 0, 0);
		screen.repaint();
		System.out.println(screen.gameObjects.get(0).points.get(0)[0].getX() + " " + screen.gameObjects.get(0).points.get(0)[0].getY() + " " + screen.gameObjects.get(0).points.get(0)[0].getZ());
		System.out.println(screen.gameObjects.get(0).points.get(0)[1].getX() + " " + screen.gameObjects.get(0).points.get(0)[1].getY() + " " + screen.gameObjects.get(0).points.get(0)[1].getZ());
		System.out.println(screen.gameObjects.get(0).points.get(0)[2].getX() + " " + screen.gameObjects.get(0).points.get(0)[2].getY() + " " + screen.gameObjects.get(0).points.get(0)[2].getZ());
		System.out.println(screen.gameObjects.get(0).points.get(0)[3].getX() + " " + screen.gameObjects.get(0).points.get(0)[3].getY() + " " + screen.gameObjects.get(0).points.get(0)[3].getZ());

		
	}

}
