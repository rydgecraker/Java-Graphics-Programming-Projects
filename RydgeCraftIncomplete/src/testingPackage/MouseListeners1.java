package testingPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MouseListeners1 implements MouseListener, MouseMotionListener{
//For title screen
	private GameContentPanel gcp;
	private JFrame frame;
	private GameRunner gr;
	
	public MouseListeners1(GameContentPanel gcp, JFrame frame, GameRunner gr){
		this.gcp = gcp;
		this.frame = frame;
		this.gr = gr;
	}
	
	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	
	public void mouseMoved(MouseEvent e) {
		//Swaps button colors when hovered over
		for(int i = 0; i < gcp.buttons.size(); i++){
			if(e.getX() > gcp.buttons.get(i).getX() && e.getX() < (gcp.buttons.get(i).getWidth() + gcp.buttons.get(i).getX()) && e.getY() >gcp.buttons.get(i).getY() && e.getY() < (gcp.buttons.get(i).getHeight() + gcp.buttons.get(i).getY())){
				gcp.buttons.get(i).setBackgroundColor(new Color(204, 204, 204));
				gcp.buttons.get(i).setForegroundColor(new Color(153, 153, 153));
			}else{
				gcp.buttons.get(i).setBackgroundColor(new Color(153, 153, 153));
				gcp.buttons.get(i).setForegroundColor(new Color(204, 204, 204));
			}
		}
		gcp.revalidate();
		gcp.repaint();
		
	}

	////////////////////////////////////////////////////////////////////////////////////
	
	
	public void mouseClicked(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}

	
	public void mousePressed(MouseEvent e) {
		//Darkens buttons when pressed
		for(int i = 0; i < gcp.buttons.size(); i++){
			if(e.getX() > gcp.buttons.get(i).getX() && e.getX() < (gcp.buttons.get(i).getWidth() + gcp.buttons.get(i).getX()) && e.getY() >gcp.buttons.get(i).getY() && e.getY() < (gcp.buttons.get(i).getHeight() + gcp.buttons.get(i).getY())){
				gcp.buttons.get(i).setBackgroundColor(new Color(153, 153, 153));
				gcp.buttons.get(i).setForegroundColor(new Color(153, 153, 153));
			}else{
				gcp.buttons.get(i).setBackgroundColor(new Color(153, 153, 153));
				gcp.buttons.get(i).setForegroundColor(new Color(204, 204, 204));
			}
		}
		gcp.revalidate();
		gcp.repaint();
		
	}

	
	public void mouseReleased(MouseEvent e) {
		//Makes buttons lighter when mouse released.
		for(int i = 0; i < gcp.buttons.size(); i++){
			if(e.getX() > gcp.buttons.get(i).getX() && e.getX() < (gcp.buttons.get(i).getWidth() + gcp.buttons.get(i).getX()) && e.getY() >gcp.buttons.get(i).getY() && e.getY() < (gcp.buttons.get(i).getHeight() + gcp.buttons.get(i).getY())){
				gcp.buttons.get(i).setBackgroundColor(new Color(204, 204, 204));
				gcp.buttons.get(i).setForegroundColor(new Color(204, 204, 204));
				//Button actions when mouse released
				if(i == 0){
					//Load game
					gr.loadGame = true;
				}else if(i == 1){
					//New Game
					gr.newGame = true;
				}else if(i == 2){
					//Quit Game
					System.exit(0);
				}
			}else{
				gcp.buttons.get(i).setBackgroundColor(new Color(153, 153, 153));
				gcp.buttons.get(i).setForegroundColor(new Color(204, 204, 204));
			}
		}
		gcp.revalidate();
		gcp.repaint();
	}

}
