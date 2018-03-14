package quest;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GetImage {
	
	GetImage(){
		
	}
	
	public BufferedImage image(File filepath){
		BufferedImage img = null;
		try {
			img = ImageIO.read(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toCompatibleImage(img);
	}
	
	private BufferedImage toCompatibleImage(BufferedImage image)
	{
	// obtain the current system graphical settings
	GraphicsConfiguration gfx_config = GraphicsEnvironment.
	getLocalGraphicsEnvironment().getDefaultScreenDevice().
	getDefaultConfiguration();

	/*
	* if image is already compatible and optimized for current system 
	* settings, simply return it
	*/
	if (image.getColorModel().equals(gfx_config.getColorModel()))
	return image;

	// image is not optimized, so create a new image that is
	BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());

	// get the graphics context of the new image to draw the old image on
	Graphics2D g2d = (Graphics2D) new_image.getGraphics();

	// actually draw the image and dispose of context no longer needed
	g2d.drawImage(image, 0, 0, null);
	g2d.dispose();

	// return the new optimized image
	return new_image; 
	}

}


