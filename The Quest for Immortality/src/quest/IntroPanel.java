package quest;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IntroPanel extends JPanel { //Starts intro

	File f1;
	File f2;
	File f3;
	File f4;
	BufferedImage background;
	public float opacity;
	
	public IntroPanel(){
		opacity = 1;
		importFiles();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(background, 0, 0, null);
	}
	
	public void importFiles(){
		f1 = new File("IntroFiles/introPic2.png");
		f2 = new File("IntroFiles/introPic3.png");
		f3 = new File("IntroFiles/introPic1.png");
		f4 = new File("IntroFiles/introPic4.png");
	}
	
	public void setBackgroundImage(int x){ 
		if(x != -1){
			if(x == 0){ // "R"
				try {
					background = ImageIO.read(f1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(x == 1){
				try {
					background = ImageIO.read(f2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(x == 2){
				try {
					background = ImageIO.read(f3);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(x == 3){
				try {
					background = ImageIO.read(f4);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
