package gt;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, FocusListener{

	public boolean[] key = new boolean[68836];
	public static int mouseX;
	public static int mouseY;
	
	public void focusGained(FocusEvent e) {
		
		
	}

	
	public void focusLost(FocusEvent e) {
		for(int i = 0; i < key.length; i++){
			key[i] = false;
		}
	}

	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
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
		
		
	}

	
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		if(kc > 0 && kc < key.length){
			key[kc] = true;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		int kc = e.getKeyCode();
		if(kc > 0 && kc < key.length){
			key[kc] = false;
		}
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
