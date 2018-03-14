package zombieGame2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateAndRunGame implements Runnable, KeyListener{
	JFrame f;
	GamePanel p;
	public static Object[] newOrLoad = {"New Game", "Load Game"};
	public static Object[] loadGames;
	int lg;
	Thread th;

	public CreateAndRunGame() throws IOException{
		f = new JFrame();
		f.setSize(518, 545);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int x = 0;
		do{
			int nol = JOptionPane.showOptionDialog(null,"New Game or Load Game?","New Game or Load Game",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,newOrLoad,null);
			if(nol == 0){
				lg = -1;
				x = 1;
			}else if(nol == 1){
				loadGames = new File("Saves").listFiles(new FilenameFilter() { public boolean accept(File dir, String filename) { return filename.endsWith(".txt"); }} );
				lg = JOptionPane.showOptionDialog(null,"Load Which Game?","Load Game",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,loadGames,null);
				x = 2;
			}
		}while(x == 0);
		
		if(x == 1){
			p = new GamePanel();
		}else if(x == 2){
			p = new GamePanel( (File) loadGames[lg]);
		}
		p.setBackground(Color.BLACK);
		p.setLayout(new BorderLayout());
		p.setFocusable(true);
		p.addKeyListener(this);
		
		f.add(p);
		f.setContentPane(p);
		f.setVisible(true);
		
		th = new Thread(this);
		th.start();
		
	}
	

	public void changeBackgroundColor(Color c){
		p.setBackground(c);
	}

	public void run() {
		while(true){
			p.repaint();
			p.collideCheck();
			try {
				p.checkNextFrame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_W){
			p.move(0);
		}
		if(e.getKeyCode() == e.VK_S){
			p.move(1);
		}
		if(e.getKeyCode() == e.VK_A){
			p.move(2);
		}
		if(e.getKeyCode() == e.VK_D){
			p.move(3);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}


	public void keyTyped(KeyEvent e) {
		
	}
	
}
