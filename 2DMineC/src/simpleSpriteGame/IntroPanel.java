package simpleSpriteGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IntroPanel extends JPanel{

	String file0;
	String file1;
	BufferedImage img0;
	BufferedImage img1;
	
	private int imageNumber;
	
	public IntroPanel(String file0, String file1){
		this.file0 = file0;
		this.file1 = file1;
		getImage();
		imageNumber = 0;
	}
	
	public void getImage(){
		File f0 = new File(file0);
		File f1 = new File(file1);
		try {
			img0 = ImageIO.read(f0);
			img1 = ImageIO.read(f1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(imageNumber != 0){
			if(imageNumber == 1){
				g.drawImage(img0, 0, 0, null);
			}else if(imageNumber == 2){
				g.drawImage(img1, 0, 0, null);
			}else{
				
			}
		
		}
	}
	
	public void displayImage(int i){
		imageNumber = i;
	}
	
}
