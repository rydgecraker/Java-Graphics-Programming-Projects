package zombieGame2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class GUI extends JFrame {
	
	int numberOfSaves;
	Dimension screen;
	
	public static void main(String[] args) throws IOException{
		GUI game = new GUI();
	}
	
	public GUI() throws IOException{
		loadGameAspects();
		CreateAndRunGame jf = new CreateAndRunGame();
		
	}
	
	public void loadGameAspects(){
		JFrame loading = new JFrame();
		loading.setTitle("Loading Bar");
		loading.setSize(500, 100);
		loading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel loadingPanel = new JPanel(new BorderLayout());
		JLabel load = new JLabel("Looking for save files");
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
		loadingPanel.setBackground(Color.WHITE);
		left.setBackground(Color.BLACK);
		center.setBackground(Color.BLACK);
		right.setBackground(Color.BLACK);
		load.setBackground(Color.WHITE);
		
		loadingPanel.add(load, BorderLayout.NORTH);
		loadingPanel.add(left, BorderLayout.WEST);
		loadingPanel.add(center, BorderLayout.CENTER);
		loadingPanel.add(right, BorderLayout.EAST);
		loading.add(loadingPanel);
		loading.setContentPane(loadingPanel);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	 
		int w = loading.getSize().width;
		int h = loading.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;	 
		loading.setLocation(x, y);
		loading.setVisible(true);
		
		numberOfSaves = new File("Saves").listFiles(new FilenameFilter() { public boolean accept(File dir, String filename) { return filename.endsWith(".txt"); }} ).length;
		left.setBackground(Color.GREEN);
		load.setText("Found " + numberOfSaves + "Saves");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load.setText("Loading Background Music");
		Sounds(0);
		load.setText("Background Music Loaded");
		center.setBackground(Color.GREEN);
		right.setBackground(Color.GREEN);
		load.setText("Game Loaded");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loading.dispose();
		
	}
	
	public void Sounds(int i){
		if(i == 0){
			try {
			    File yourFile = new File("Music/background.wav");
			    AudioInputStream stream;
			    AudioFormat format;
			    DataLine.Info info;
			    Clip clip;

			    stream = AudioSystem.getAudioInputStream(yourFile);
			    format = stream.getFormat();
			    info = new DataLine.Info(Clip.class, format);
			    clip = (Clip) AudioSystem.getLine(info);
			    clip.open(stream);
			    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			    gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
			    clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
