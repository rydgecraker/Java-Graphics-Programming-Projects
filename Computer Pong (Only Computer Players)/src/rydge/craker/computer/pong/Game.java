package rydge.craker.computer.pong;


import java.awt.Color;

import javax.swing.JFrame;


public class Game {
	
	public JFrame		frame;
	public MainPanel	mp;
	public int			delay;
	public int			delay2;
	
	
	public Game() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mp = new MainPanel(0, 0, 800, 600);
		mp.setBackground(Color.BLACK);
		
		frame.setContentPane(mp);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		System.out.println(mp.getWidth());
		System.out.println(mp.getHeight());
		
		delay = 0;
		delay2 = 1;
		
	}
	
	
	public void start() {
		while (true) {
			mp.doComputerStuff();
			mp.repaint();
			try {
				Thread.sleep(delay, delay2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
