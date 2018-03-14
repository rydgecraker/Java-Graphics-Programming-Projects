package quest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoadMenuPanel extends JPanel implements MouseListener, KeyListener, ActionListener{

	SavedDisplayInLoadMenu sdlm1;
	SavedDisplayInLoadMenu sdlm2;
	SavedDisplayInLoadMenu sdlm3;
	SavedDisplayInLoadMenu selectedFile;
	private String gameLoaded;
	private int deathStat;
	private ArrayList<Integer> stats;
	private boolean statsFigured;
	public boolean konamicoded;
	
	
	private JTextField name;
	private boolean createFile;
	private JButton okay;
	
	private Player mainPlayer;
	
	private int konamiCode;
	
	public LoadMenuPanel(){
		this.addMouseListener(this);
		konamicoded = false;
		this.setFocusable(true);
		this.addKeyListener(this);
		konamiCode = 0;
		statsFigured = false;
		createFile = false;
		sdlm1 = new SavedDisplayInLoadMenu(0, 0, 1);
		sdlm1.addMouseListener(this);
		sdlm1.addKeyListener(this);
		sdlm1.setFocusable(true);
		sdlm2 = new SavedDisplayInLoadMenu(0, 0, 2);
		sdlm2.addMouseListener(this);
		sdlm2.addKeyListener(this);
		sdlm2.setFocusable(true);
		sdlm3 = new SavedDisplayInLoadMenu(0, 0, 3);
		sdlm3.addMouseListener(this);
		sdlm3.addKeyListener(this);
		sdlm3.setFocusable(true);
		
		
		sdlm1.setLocation(0, 0);
		sdlm2.setLocation(0, 253);
		sdlm3.setLocation(0, 506);
		
		this.add(sdlm1);
		this.add(sdlm2);
		this.add(sdlm3);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(statsFigured){
			this.requestFocus();
		}else{
			SavedDisplayInLoadMenu s = (SavedDisplayInLoadMenu) e.getSource();
			s.requestFocus();
			if(selectedFile == null){
				s.bgColor = Color.RED;
				selectedFile = s;
				this.repaint();
			}else{
				if(selectedFile != s){
				s.bgColor = Color.RED;
				selectedFile.bgColor = Color.GREEN;
				selectedFile = s;
				this.repaint();
				}else{
					loadOrCreateGame();
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void loadOrCreateGame(){
		gameLoaded = selectedFile.loadOrCreateGame();
		fileCreation();
	}

	public void keyPressed(KeyEvent e) {
		
		if(statsFigured){
			if(e.getKeyCode() == e.VK_UP){
				if(konamiCode == 0){
					konamiCode = 1;
				}else if(konamiCode == 1){
					konamiCode = 2;
				}else{
					konamiCode = 0;
				}
			}
			if(e.getKeyCode() == e.VK_DOWN){
				if(konamiCode == 2){
					konamiCode = 3;
				}else if(konamiCode == 3){
					konamiCode = 4;
				}else{
					konamiCode = 0;
				}
			}
			if(e.getKeyCode() == e.VK_LEFT){
				if(konamiCode == 4){
					konamiCode = 5;
				}else if(konamiCode == 6){
					konamiCode = 7;
				}else{
					konamiCode = 0;
				}
			}
			if(e.getKeyCode() == e.VK_RIGHT){
				if(konamiCode == 5){
					konamiCode = 6;
				}else if(konamiCode == 7){
					konamiCode = 8;
					name.setEnabled(false);
				}else{
					konamiCode = 0;
				}
			}
			if(e.getKeyCode() == e.VK_B){
				if(konamiCode == 8){
					konamiCode = 9;
				}else{
					konamiCode = 0;
				}
			}
			if(e.getKeyCode() == e.VK_A){
				if(konamiCode == 9){
					int b = stats.size();
					stats.clear();
					for(int ii = 0; ii < b; ii++){
						stats.add(7);
					}
					name.setEnabled(true);
					konamicoded = true;
					fileCreation();
				}else{
					konamiCode = 0;
				}
			}
		}
		
		if(e.getKeyCode() == e.VK_ENTER){
			if(createFile == false){
				if(selectedFile != null){
					loadOrCreateGame();
				}
			}else{
				okay.doClick();
			}
			
		}
	}
	

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void fileCreation(){
		if(gameLoaded == null){
			createFile = true;
			this.removeAll();
			JLabel writeName = new JLabel("Please Type In Your Name Here:");
			writeName.setForeground(Color.WHITE);
			writeName.setFont(new Font("Serif", Font.BOLD, 30));
			writeName.setSize(500, 50);
			writeName.setLocation(15, 10);
			
			if(!konamicoded){
				deathStat = getDeathStat();
				stats = generateRandomStats();
				
				if(deathStat == 13){
					stats.clear();
					for(int i = 0; i < 7; i++){
						stats.add(1);
					}
				}else if(deathStat == 42){
					int temp = getDeathStat();
					if(temp == 42){
						stats.clear();
						for(int i = 0; i < 7; i++){
							stats.add(6);
						}
					}
				}
			}
			
			
			
			
			JLabel str = new JLabel("Strength: " + stats.get(0));
			JLabel def = new JLabel("Defense: " + stats.get(1));
			JLabel speed = new JLabel("Speed: " + stats.get(2));
			JLabel cha = new JLabel("Charisma: " + stats.get(3));
			JLabel magic = new JLabel("Magic: " + stats.get(4));
			JLabel luck = new JLabel("Luck: " + stats.get(5));
			JLabel deth = new JLabel("deathStat: " + deathStat);
			JLabel con = new JLabel("Constitution: " + stats.get(6));
			
			
			str.setFont(new Font("Serif", Font.BOLD, 30));
			def.setFont(new Font("Serif", Font.BOLD, 30));
			speed.setFont(new Font("Serif", Font.BOLD, 30));
			cha.setFont(new Font("Serif", Font.BOLD, 30));
			magic.setFont(new Font("Serif", Font.BOLD, 30));
			luck.setFont(new Font("Serif", Font.BOLD, 30));
			deth.setFont(new Font("Serif", Font.BOLD, 30));
			con.setFont(new Font("Serif", Font.BOLD, 30));
			
			str.setForeground(Color.WHITE);
			def.setForeground(Color.WHITE);
			speed.setForeground(Color.WHITE);
			cha.setForeground(Color.WHITE);
			magic.setForeground(Color.WHITE);
			luck.setForeground(Color.WHITE);
			deth.setForeground(Color.WHITE);
			con.setForeground(Color.WHITE);
			
			str.setSize(500, 50);
			str.setLocation(15, 150);
			def.setSize(500, 50);
			def.setLocation(15, 200);
			speed.setSize(500, 50);
			speed.setLocation(15, 250);
			cha.setSize(500, 50);
			cha.setLocation(15, 300);
			magic.setSize(500, 50);
			magic.setLocation(15, 350);
			luck.setSize(500, 50);
			luck.setLocation(15, 400);
			deth.setSize(500, 50);
			deth.setLocation(15, 500);
			con.setSize(500, 50);
			con.setLocation(15, 450);
			
			name =  new JTextField(1);
			name.setSize(500, 50);
			name.setLocation(15, 60);
			name.setFont(new Font("Serif", Font.BOLD, 30));
			
			okay = new JButton("Done!");
			okay.setSize(120, 50);
			okay.setLocation(340, 600);
			okay.setFont(new Font("Serif", Font.BOLD, 30));
			okay.setActionCommand("Okay");
			okay.addActionListener(this);
			
			this.add(con);
			this.add(okay);
			this.add(str);
			this.add(def);
			this.add(speed);
			this.add(cha);
			this.add(magic);
			this.add(luck);
			this.add(deth);
			this.add(name);
			this.add(writeName);
			this.setBackground(Color.RED);
			this.repaint();
		}else{
			
		}
	}
	
	public ArrayList<Integer> generateRandomStats(){
		ArrayList<Integer> stats = new ArrayList<Integer>();
		
		for(int i = 1; i <= 7; i++){
			stats.add(i);
		}
		
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		Collections.shuffle(stats);
		
		this.requestFocus();
		statsFigured = true;
		
		return stats;
	}
	
	public int getDeathStat(){
		Random r = new Random();
		int x = r.nextInt(100);
		return x + 1;
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Okay")){
			if(name.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please Enter A Name");
			}else{
				createPlayer();
			}
		}
	}
	
	public void createPlayer(){
		mainPlayer = new Player(name.getText(), stats, deathStat, (400 - 10), 300, 20, 20);
	}
	
	public Player getPlayer(){
		if(mainPlayer == null){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			return mainPlayer;
			
		}
	}

}
