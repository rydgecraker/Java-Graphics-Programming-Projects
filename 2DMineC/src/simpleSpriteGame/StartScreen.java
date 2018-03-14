package simpleSpriteGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScreen extends JPanel{
	
	private BufferedImage img1;
	private File file;
	
	public StartScreen(CreateAndRun c){
		file = new File("Images/startScreen.png");
		try {
			img1 = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img1, 0, 0, null);
	}
	
}
