package testingPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameContentPanel extends JPanel {
//Used to run the title screen
	private int x;
	private int y;
	private int width;
	private int height;
	private Color bg;
	private BufferedImage image;
	public ArrayList<Button> buttons = new ArrayList<Button>();

	public GameContentPanel(int x, int y, int width, int height, Color bg) {
		//Jpanel that holds buttons
		super();
		this.x = x;
		this.y = y;
		this.setLocation(x, y);
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.bg = bg;
		this.setBackground(bg);   
		
	    try {
			image = ImageIO.read(new File("ImagesAndMusic/RydgeCraft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		
	}

	public void setX(int x) {
		//Subject to change with JFrame
		this.x = x;
		this.setLocation(x, y);
	}

	public void setY(int y) {
		//Subject to change with JFrame
		this.y = y;
		this.setLocation(x, y);
	}

	public void setWidth(int width) {
		//Subject to change with JFrame
		this.width = width;
		this.setSize(width, height);
	}

	public void setHeight(int height) {
		//Subject to change with JFrame
		this.height = height;
		this.setSize(width, height);
	}

	public void setColor(Color bg) {
		//Not important, image overtakes it. leave black.
		this.bg = bg;
		this.setBackground(bg);
	}

	public void paintComponent(Graphics g) {
		//paints everything to the jpanel
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(image, 0, 0, width, height, null);
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).paintComponent(g);
		}
	}

	public void addButtons() {
		//Adding menu buttons by percentages
		Button b = new Button((int) (x + (width * .1)),(int) (y + (height * .66)),(int) (width * .8), (int) (height * .1), new Color(153, 153, 153),new Color(204, 204, 204), Color.WHITE, "LOAD GAME", new Font("Arial", Font.BOLD, 55));
		buttons.add(b);
		this.add(b);
		
		b = new Button((int) (x + (width * .1)), (int) (y + (height * .495)), (int) (width * .8), (int) (height * .1), new Color(153, 153, 153),new Color(204, 204, 204), Color.WHITE, "NEW GAME",  new Font("Arial", Font.BOLD, 55));
		buttons.add(b);
		this.add(b);
		
		b = new Button((int) (x + (width * .1)), (int) (y + (height * .825)), (int) (width * .8), (int) (height * .1), new Color(153, 153, 153), new Color(204, 204, 204), Color.WHITE, "QUIT GAME", new Font("Arial", Font.BOLD, 55));
		buttons.add(b);
		this.add(b);
	}

	public void revalidate() {
		//Resizing takes place here
		super.revalidate();
		if (buttons != null && !buttons.isEmpty()) {
			revalidateButtons();
			super.revalidate();
		}
	}

	private void revalidateButtons() {
		//Begins resizing buttons
		for (int i = 0; i < buttons.size(); i++) {
			revalidateButton(i, buttons.get(i));
		}
	}

	private void revalidateButton(int num, Button temp) {
		//Finishes resizing buttons
		if(num == 0){
			buttons.set(num, new Button((int) (x + (width * .1)),(int) (y + (height * .66)),(int) (width * .8), (int) (height * .1), temp.getBackgroundColor(), temp.getForegroundColor(), temp.getTextColor(),temp.getText(), temp.getFont()));
		}else if(num == 1){
			buttons.set(num, new Button((int) (x + (width * .1)), (int) (y + (height * .495)), (int) (width * .8), (int) (height * .1),  temp.getBackgroundColor(), temp.getForegroundColor(), temp.getTextColor(),temp.getText(), temp.getFont()));
		}else if(num == 2){
			buttons.set(num, new Button((int) (x + (width * .1)), (int) (y + (height * .825)), (int) (width * .8), (int) (height * .1), temp.getBackgroundColor(), temp.getForegroundColor(), temp.getTextColor(), temp.getText(), temp.getFont()));
		}
	}

}
