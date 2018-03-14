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

public class CharacterChooser extends JPanel{

	public CharacterHex[] hexes;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public String text;
	
	public int stringX;
	public int stringY;
	
	public int mouseX;
	public int mouseY;
	
	public int currentPlayer;
	
	public BufferedImage img;
	public boolean ynb;
	
	public static final Font font = new Font("Arial", Font.BOLD, 50);
	
	public YesOrNoCharacterPanel yn;
	
	public CharacterChooser(int x, int y, int width, int height, String text, int numCurrentPlayer){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.currentPlayer = numCurrentPlayer;
		try {
			img = ImageIO.read(new File("src/Images/trees3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		init();
	}
	
	public CharacterChooser(int x, int y, int width, int height, String text, int numCurrentPlayer, String[] chosenCharacters){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.currentPlayer = numCurrentPlayer;
		try {
			img = ImageIO.read(new File("src/Images/trees3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		init(chosenCharacters);
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
	
	public void init(){
		hexes = new CharacterHex[12];
		int startingY = (int) (height * .1);
		int space = 20;
		int hexWidth = (int) ((width - (space*7))/6);
		int hexHeight = (int) (((height * .9) - (space*3))/2);
		
		//Top
		hexes[0] = new CharacterHex(x + space, y + space + startingY, hexWidth, hexHeight, 0, 0);
		hexes[1] = new CharacterHex(x + (space*2) + hexWidth, y + space + startingY, hexWidth, hexHeight, 0, 1);
		hexes[2] = new CharacterHex(x + (space*3) + (hexWidth*2), y + space + startingY, hexWidth, hexHeight, 1, 0);
		hexes[3] = new CharacterHex(x + (space*4) + (hexWidth*3), y + space + startingY, hexWidth, hexHeight, 1, 1);
		hexes[4] = new CharacterHex(x + (space*5) + (hexWidth*4), y + space + startingY, hexWidth, hexHeight, 2, 0);
		hexes[5] = new CharacterHex(x + (space*6) + (hexWidth*5), y + space + startingY, hexWidth, hexHeight, 2, 1);
		//Bottom
		hexes[6] = new CharacterHex(x + space, y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 3, 0);
		hexes[7] = new CharacterHex(x + (space*2) + hexWidth, y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 3, 1);
		hexes[8] = new CharacterHex(x + (space*3) + (hexWidth*2), y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 4, 0);
		hexes[9] = new CharacterHex(x + (space*4) + (hexWidth*3), y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 4, 1);
		hexes[10] = new CharacterHex(x + (space*5) + (hexWidth*4), y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 5, 0);
		hexes[11] = new CharacterHex(x + (space*6) + (hexWidth*5), y + (space * 2) + hexHeight + startingY, hexWidth, hexHeight, 5, 1);
	}
	
	public void init(String[] chosenCharacters){
		hexes = new CharacterHex[(12 - (chosenCharacters.length * 2))];
		int[] cColor = new int[chosenCharacters.length];
		int counter = 0;
		
		int startingY = (int) (height * .1);
		int space = 20;
		int hexWidth;
		int hexHeight;
		
		if(hexes.length > 2){
			hexWidth = (int) ((width - (space * ((hexes.length / 2) + 1)))/(hexes.length / 2)); 
			//hexWidth = (int) ((width - (space*((hexes.length/2))))/(hexes.length/2));
			hexHeight = (int) (((height * .9) - (space*4))/2);
		}else{
			hexWidth = (int) ((width - (space*((hexes.length))))/(hexes.length));
			hexHeight = (int) ((height * .9) - (space * 3));
		}
		
		for(int i = 0; i < chosenCharacters.length; i++){
			if(chosenCharacters[i].equals("mz") || chosenCharacters[i].equals("vl")){
				cColor[counter] = 0;
				counter++;
			}else if(chosenCharacters[i].equals("bj") || chosenCharacters[i].equals("pa")){
				cColor[counter] = 1;
				counter++;
			}else if(chosenCharacters[i].equals("hg") || chosenCharacters[i].equals("jl")){
				cColor[counter] = 2;
				counter++;
			}else if(chosenCharacters[i].equals("dfw") || chosenCharacters[i].equals("ob")){
				cColor[counter] = 3;
				counter++;
			}else if(chosenCharacters[i].equals("fr") || chosenCharacters[i].equals("pl")){
				cColor[counter] = 4;
				counter++;
			}else if(chosenCharacters[i].equals("md") || chosenCharacters[i].equals("zi")){
				cColor[counter] = 5;
				counter++;
			}
		}
		
		int currentColor=0;
		int tmpI = 0;
		
		for(int i = 0; i < hexes.length; i++){
			for(int j = 0; j < cColor.length; j++){
				if(cColor[j] == currentColor){
					currentColor++;
				}
			}
			if(hexes.length > 2){
				
				if(i >= (hexes.length / 2)){
					hexes[i] = new CharacterHex(x + (space * (tmpI + 1)) + (hexWidth * tmpI), y + (space * 3) + startingY + hexHeight, hexWidth, hexHeight, currentColor, 0);
					i++;
					tmpI++;
					hexes[i] = new CharacterHex(x + (space * (tmpI + 1)) + (hexWidth * tmpI), y + (space * 3) + startingY + hexHeight, hexWidth, hexHeight, currentColor, 1);
					tmpI++;
				}else{
					hexes[i] = new CharacterHex(x + (space * (i + 1)) + (hexWidth * i), y + (space * 1) + startingY, hexWidth, hexHeight, currentColor, 0);
					i++;
					if(i >= hexes.length / 2){
						hexes[i] = new CharacterHex(x + (space * (tmpI + 1)) + (hexWidth * tmpI), y + (space * 3) + startingY + hexHeight, hexWidth, hexHeight, currentColor, 1);
						tmpI++;
					}else{
						hexes[i] = new CharacterHex(x + (space * (i + 1)) + (hexWidth * i), y + (space * 1) + startingY, hexWidth, hexHeight, currentColor, 1);
					}
				}
			}else{
				hexes[i] = new CharacterHex(x + (space * (i + 1)) + (hexWidth * i), y + (space * 1) + startingY, hexWidth, hexHeight, currentColor, 0);
				i++;
				hexes[i] = new CharacterHex(x + (space * (i + 1)) + (hexWidth * i), y + (space * 1) + startingY, hexWidth, hexHeight, currentColor, 1);
			}
			currentColor++;
		}	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, x, y, null);
		g.setFont(font);
		stringCalculations(g, new Rectangle(x, y, width, (int) (height * .1)));
		drawStringOutlines(g);
		g.setColor(Color.RED);
		g.drawString(text, stringX, stringY);
		for(int i = 0; i < hexes.length; i++){
			hexes[i].draw(g);
		}
		if(ynb){
			yn.paintComponent(g);
		}
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
	
	public CharacterHex getHexMousedOver(){
		return ggetHexMousedOver(mouseX, mouseY);
	}
	
	
	private void mouseOver(int x, int y) {
		for (int i = 0; i < hexes.length; i++) {
			if (x > hexes[i].x && x < hexes[i].width + hexes[i].x && y > hexes[i].y && y < hexes[i].height + hexes[i].y) {
				hexes[i].invert();
			} else {
				hexes[i].revert();
			}
		}
	}

	private CharacterHex ggetHexMousedOver(int x, int y) {
		for (int i = 0; i < hexes.length; i++) {
			if (x > hexes[i].x && x < hexes[i].width + hexes[i].x && y > hexes[i].y && y < hexes[i].height + hexes[i].y) {
				return hexes[i];
			} else {
				hexes[i].revert();
			}
		}
		return null;
	}

	public void ynStuff(YesOrNoCharacterPanel yn) {
		this.yn = yn;
		this.yn.mouseX = mouseX;
		this.yn.mouseY = mouseY;
		this.yn.menuEvents();
	}
	
}
