package quest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntroAndStartScreen implements ActionListener, Runnable{ //Loads the intro movies and start screen.  //If non-existant, the files are not loaded.

	JFrame frame;
	IntroPanel introPanel;
	JButton startButton;
	LoadMenuPanel lmp;
	Player p;
	
	public IntroAndStartScreen(JFrame frame){
		this.frame = frame;
		introPanel = new IntroPanel();
		introPanel.setBackground(Color.BLACK);
		introPanel.setSize(800, 800);
		this.frame.add(introPanel);
		this.frame.setContentPane(introPanel);
		this.frame.revalidate();
		introPanel.setLayout(null);
		
		intro();
		
	}
	
	public void intro(){
		try {
			for(int i = 0; i < 4; i++){
				introPanel.setBackgroundImage(i);
					for(float f = 0; f < 1; f = (float) (f + .01)){
					introPanel.opacity = (float) f;
					frame.repaint();
					Thread.sleep(50);
					}
					Thread.sleep(300);
					if(i != 3){
						for(float f = 1; f > 0; f = (float) (f - .01)){
							introPanel.opacity = (float) f;
							frame.repaint();
							Thread.sleep(20);
						}	
					}	
			}
			startButton = new JButton("Start");
			Font f = new Font("Arial", Font.BOLD, 50);
			startButton.setFont(f);
			startButton.setLayout(null);
			startButton.setBounds(250, 500, 300, 100);
			startButton.setBackground(new Color(238, 224, 229, 255));
			startButton.setForeground(new Color(176, 23, 31, 255));
			startButton.setActionCommand("Start Button");
			startButton.addActionListener(this);
			introPanel.add(startButton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start Button")){
			loadMenu();
		}else{
			
		}
	}
	
	public void loadMenu(){
		lmp = new LoadMenuPanel();
		lmp.setBackground(Color.BLUE);
		lmp.setLayout(null);
		frame.setContentPane(lmp);
		frame.revalidate();
		frame.repaint();
		p = null;
		Thread th = new Thread(this);
		th.start();
	}
	
	public Player getPlayer(){
		if(p == null){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			return p;
		}
		
	}

	public void run() {
		do{
			
		}while(lmp.getPlayer() == null);
		p = lmp.getPlayer();
	}
	
	
}
