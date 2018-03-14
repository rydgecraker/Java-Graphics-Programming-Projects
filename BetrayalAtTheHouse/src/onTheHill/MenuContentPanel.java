package onTheHill;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuContentPanel extends JPanel{

	public Color bg;
	public ButtonHolder bh;
	
	public int mouseX;
	public int mouseY;
	
	public BufferedImage img;
	
	public MenuContentPanel(Dimension resolution){
		super();
		bh = new ButtonHolder(this.getX(), this.getY() + ((int) (resolution.height * .75)), resolution.width, ((int)(resolution.height * .25)), 10);
		
		try {
			img = ImageIO.read(new File("src/Images/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		img = toBufferedImage( img.getScaledInstance(resolution.width, resolution.height, Image.SCALE_DEFAULT));  
	}
	
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
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
	
	public void addButton(String text, String keyCode){
		bh.addButton(text, keyCode);
	}
	
	public void setBackground(Color c){
		super.setBackground(c);
		this.bg = c;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		bh.draw(g);
	}
	
	public void menuEvents(){
		bh.mouseOver(mouseX, mouseY);
	}
	
	public void darkColors(){
		bh.darkColors(mouseX, mouseY);
	}
	
	public Button getButtonMousedOver(){
		return bh.getButtonMousedOver(mouseX, mouseY);
	}
	
}
