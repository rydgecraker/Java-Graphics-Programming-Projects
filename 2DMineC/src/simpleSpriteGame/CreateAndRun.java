package simpleSpriteGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateAndRun implements ActionListener, Runnable, KeyListener{
	
	private JFrame mainFrame;
	private IntroPanel intro;
	private StartScreen ss;
	private JButton start;
	private Clip clip;
	private Clip clipbg;
	private GamePanel gp;
	private Thread th;
	private JButton options;
	

	public static void main(String[] args) {
		CreateAndRun car = new CreateAndRun();
	}
	
	public CreateAndRun(){
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 900);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		intro = new IntroPanel("Images/img0.png", "Images/img1.png");
		intro.setBackground(Color.BLACK);
		intro.setLayout(new BorderLayout());
		
		ss = new StartScreen(this);
		ss.setPreferredSize(new Dimension(1000, 900));
		
		start = new JButton("Start");
		start.setFont(new Font("Idk", Font.BOLD, 50));
		start.setActionCommand("Start");
		start.addActionListener(this);
		start.setBackground(Color.BLACK);
		start.setForeground(Color.RED);
		
		ss.add(start, BorderLayout.SOUTH);
		mainFrame.add(intro);
		mainFrame.setContentPane(intro);
		mainFrame.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = mainFrame.getSize().width;
		int h = mainFrame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		// Move the window
		mainFrame.setLocation(x, y - 30);
		
		mainFrame.setVisible(true);
		backgroundMusic(0);
		displayIntro();
		mainFrame.add(ss);
		mainFrame.setContentPane(ss);
		mainFrame.remove(intro);
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}
	
	public void displayIntro(){
		intro.displayImage(1);
		mainFrame.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		intro.displayImage(2);
		mainFrame.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		intro.displayImage(0);
		mainFrame.repaint();
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")){
			clipbg.close();
			soundEffects(0);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			gp = new GamePanel();
			gp.setBackground(new Color(0, 154, 205, 255));
			gp.setPreferredSize(new Dimension(1000, 900));
			gp.setLayout(new BorderLayout());
			options = new JButton("Options");
			options.addActionListener(this);
			options.setActionCommand("Options");
			
			gp.add(options, BorderLayout.NORTH);
			gp.addKeyListener(this);
			gp.setFocusable(true);
			
			mainFrame.remove(ss);
			mainFrame.setContentPane(gp);
			mainFrame.revalidate();
			mainFrame.repaint();
			
			backgroundMusic(1);
			th = new Thread(this);
			th.start();
		}else if(e.getActionCommand().equals("Options")){
			Object[] options = new Object[]{"Switch Item", "Quit"};
			int opt = JOptionPane.showOptionDialog(null, "What would you like to do?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, null);
			if(opt == 0){
				
			}else{
				System.exit(0);
			}
		}
	}
	
	public void soundEffects(int i){
		if(i == 0){ //click
			    File yourFile = new File("Sounds/click.wav");
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;

			    try {
					stream = AudioSystem.getAudioInputStream(yourFile);
					format = stream.getFormat();
				    info = new DataLine.Info(Clip.class, format);
				    clip = (Clip) AudioSystem.getLine(info);
				    clip.open(stream);
				    clip.start();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			    
		}else if(i == 1){
			
		}
	}
	
	public void backgroundMusic(int i){
		if(i == 0){
			 File yourFile = new File("BackgroundMusic/introBackground.mid");
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;

			    try {
					stream = AudioSystem.getAudioInputStream(yourFile);
					format = stream.getFormat();
				    info = new DataLine.Info(Clip.class, format);
				    clipbg = (Clip) AudioSystem.getLine(info);
				    clipbg.open(stream);
				    FloatControl gainControl = (FloatControl) clipbg.getControl(FloatControl.Type.MASTER_GAIN);
				    gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
				    clipbg.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
		}else{
			File yourFile = new File("BackgroundMusic/gameBackground.mid");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;

		    try {
				stream = AudioSystem.getAudioInputStream(yourFile);
				format = stream.getFormat();
			    info = new DataLine.Info(Clip.class, format);
			    clipbg = (Clip) AudioSystem.getLine(info);
			    clipbg.open(stream);
			    FloatControl gainControl = (FloatControl) clipbg.getControl(FloatControl.Type.MASTER_GAIN);
			    gainControl.setValue(-30.0f); // Reduce volume by 10 decibels.
			    clipbg.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		while(true){
			gp.requestFocus();
			mainFrame.repaint();
			gp.getPlayer().gravity();
			System.out.println(gp.getPlayer().getY());
		}
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_A || e.getKeyCode() == e.VK_LEFT){
			boolean b = false;
			for(int i = 0; i < gp.getGround().size(); i++){
				if(gp.getPlayer().returnRectange().intersects(gp.getGround().get(i).returnRectange())){
					b = true;
				}
			}
			
			if(gp.getPlayer().getX() > -10){
				gp.getPlayer().move(-5);
				gp.repaint();
			}
		}else if(e.getKeyCode() == e.VK_S || e.getKeyCode() == e.VK_DOWN){
			for(int i = 0; i < gp.getGround().size(); i++){
				if(gp.getPlayer().returnRectange().intersects(gp.getGround().get(i).returnRectange())){
					gp.getGround().remove(i);
					gp.getPlayer().changeGround(false);
					break;
				}
			}
		}else if(e.getKeyCode() == e.VK_D || e.getKeyCode() == e.VK_RIGHT){
			if(gp.getPlayer().getX() < 955){
				gp.getPlayer().move(5);
				gp.repaint();
			}
		}else if(e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_W || e.getKeyCode() == e.VK_UP ){
			gp.getPlayer().jump();
			gp.repaint();
		}
			
			
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_W || e.getKeyCode() == e.VK_UP ){
			gp.getPlayer().changeGround(false);
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
