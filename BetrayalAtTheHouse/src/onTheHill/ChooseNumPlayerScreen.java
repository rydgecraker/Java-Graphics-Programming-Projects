package onTheHill;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChooseNumPlayerScreen extends JPanel{
	
	public static final Font font = new Font("Arial", Font.BOLD, 50);
	public Button[] buttons;
	public int x;
	public int y;
	public int width;
	public int height;
	public String text;
	
	public int stringX;
	public int stringY;
	
	public int mouseX;
	public int mouseY;
	
	public BufferedImage img;

	public ChooseNumPlayerScreen(String text, int x, int y, int width, int height){
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		try {
			img = ImageIO.read(new File("src/Images/trees1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		initButtons();
	}
	
	public void initButtons(){
		buttons = new Button[6];
		int spaceBetween = 20;
		int startingY =(int) (height * .1);
		int buttonHeight =(int) (((height * .9) - (spaceBetween * 3))/ 2);
		int buttonWidth = (int) ((width - (spaceBetween * 4))/3);
		buttons[0] = new GreenButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "1", "1");
		buttons[1] = new GreenButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "2", "2");
		buttons[2] = new GreenButton(x + buttonWidth + buttonWidth + (spaceBetween*3), y + startingY + spaceBetween, buttonWidth, buttonHeight, "3", "3");
		buttons[3] = new GreenButton(x + spaceBetween, y + buttonHeight + startingY + (spaceBetween*2), buttonWidth, buttonHeight, "4", "4");
		buttons[4] = new GreenButton(x + buttonWidth + (spaceBetween*2), y + buttonHeight + startingY + (spaceBetween*2), buttonWidth, buttonHeight, "5", "5");
		buttons[5] = new GreenButton(x + buttonWidth + buttonWidth + (spaceBetween*3), y + buttonHeight + startingY + (spaceBetween*2), buttonWidth, buttonHeight, "6", "6");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(font);
		stringCalculations(g, new Rectangle(x, y, width, (int) (height * .1)));
		drawStringOutlines(g);
		g.setColor(Color.WHITE);
		g.drawString(text, stringX, stringY);
		for(int i = 0; i < buttons.length; i++){
			buttons[i].draw(g);
		}
		g.drawImage(img, x, y, null);
	}
	
	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
	
	public void drawStringOutlines(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString(text, stringX - 1, stringY -1);
		g.drawString(text, stringX - 1, stringY + 1);
		g.drawString(text, stringX + 1, stringY - 1);
		g.drawString(text, stringX + 1, stringY + 1);
		g.drawString(text, stringX - 2, stringY -2);
		g.drawString(text, stringX - 2, stringY + 2);
		g.drawString(text, stringX + 2, stringY - 2);
		g.drawString(text, stringX + 2, stringY + 2);
	}
	
	public void stringCalculations(Graphics g, Rectangle bounds){
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int stringWidth = fm.stringWidth(text);
		int stringAscent = fm.getAscent();
		int stringDecent = fm.getDescent();
		int stringHeight = stringDecent - stringAscent;
		
		int leftover = bounds.width - stringWidth;
		leftover = leftover / 2;
		stringX = bounds.x + leftover;
		
		leftover = bounds.height - stringHeight;
		leftover = leftover / 2;
		stringY = bounds.y + leftover;
	}
	
	public void menuEvents(){
		mouseOver(mouseX, mouseY);
	}
	
	public void darkColors(){
		ddarkColors(mouseX, mouseY);
	}
	
	public Button getButtonMousedOver(){
		return ggetButtonMousedOver(mouseX, mouseY);
	}
	
	
	private void mouseOver(int x, int y) {
		for (int i = 0; i < buttons.length; i++) {
			if (x > buttons[i].x && x < buttons[i].width + buttons[i].x && y > buttons[i].y && y < buttons[i].height + buttons[i].y) {
				buttons[i].invertColors();
			} else {
				buttons[i].revertColors();
			}
		}
	}

	private Button ggetButtonMousedOver(int x, int y) {
		for (int i = 0; i < buttons.length; i++) {
			if (x > buttons[i].x && x < buttons[i].width + buttons[i].x && y > buttons[i].y && y < buttons[i].height + buttons[i].y) {
				return buttons[i];
			} else {
				buttons[i].revertColors();
			}
		}
		return null;
	}

	private void ddarkColors(int x, int y) {
		for (int i = 0; i < buttons.length; i++) {
			if (x > buttons[i].x && x < buttons[i].width + buttons[i].x && y > buttons[i].y && y < buttons[i].height + buttons[i].y) {
				buttons[i].darkColors();
			}
		}
	}
	
}
