package onTheHill;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMenuListener implements MouseListener, MouseMotionListener{

	public MenuRunner mr;
	
	public MainMenuListener(MenuRunner mr){
		this.mr = mr;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	
	public void mouseMoved(MouseEvent e) {
		if(mr.mm){
			mr.mcp.mouseX = e.getX();
			mr.mcp.mouseY = e.getY();
		}else if(mr.opt){
			mr.options.mouseX = e.getX();
			mr.options.mouseY = e.getY();
		}
			
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}

	
	public void mousePressed(MouseEvent e) {
		
	}

	
	public void mouseReleased(MouseEvent e) {
		Button b = null;
		if(mr.mm){
			b = mr.mcp.getButtonMousedOver();
		}else if(mr.opt){
			b = mr.options.getButtonMousedOver();
		}
		if(b != null){
			if(b.keyCode.equals("ng")){
				//Run a new Game
				mr.looping = false;
			}else if(b.keyCode.equals("op")){
				//Load Options Menu
				mr.frame.setContentPane(mr.options);
				mr.frame.revalidate();
				mr.mm = false;
				mr.opt = true;
			}else if(b.keyCode.equals("qt")){
				//Quit Game
				System.exit(0);
			}else if(b.keyCode.endsWith("ct")){
				//Show Credits
				
			}else if(b.keyCode.endsWith("bk")){
				//Options
				//Go Back
				mr.frame.setContentPane(mr.mcp);
				mr.frame.revalidate();
				mr.mm = true;
				mr.opt = false;
			}
		}
	}

}
