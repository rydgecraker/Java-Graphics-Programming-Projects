package onTheHill;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChoosePlayerListener implements MouseListener, MouseMotionListener{

public Betrayal b;
	
	public ChoosePlayerListener(Betrayal b){
		this.b = b;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	
	public void mouseMoved(MouseEvent e) {
		if(this.b.hp){
			b.humanPlayers.mouseX = e.getX();
			b.humanPlayers.mouseY = e.getY();
		}else if(this.b.cp){
			b.computerPlayers.mouseX = e.getX();
			b.computerPlayers.mouseY = e.getY();
		}else if(this.b.charCh || this.b.charYesNo){
			b.charChoose.mouseX = e.getX();
			b.charChoose.mouseY = e.getY();
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
		if(this.b.hp){
			Button b = this.b.humanPlayers.getButtonMousedOver();
			if(b != null){
				if(b.keyCode.equals("1")){
					this.b.numHumanPlayers = 1;
					this.b.computerPlayers = new ChooseNumPlayerScreen2("HOW MANY COMPUTER PLAYERS?", 0, 0, this.b.resolution.width, this.b.resolution.height, 2, 5);
					this.b.computerPlayers.setBackground(Color.BLACK);
					this.b.computerPlayers.addMouseListener(this.b.cpl);
					this.b.computerPlayers.addMouseMotionListener(this.b.cpl);
					this.b.frame.setContentPane(this.b.computerPlayers);
					this.b.frame.revalidate();
					this.b.cp = true;
					this.b.hp = false;
				}else if(b.keyCode.equals("2")){
					this.b.numHumanPlayers = 2;
					this.b.computerPlayers = new ChooseNumPlayerScreen2("HOW MANY COMPUTER PLAYERS?", 0, 0, this.b.resolution.width, this.b.resolution.height, 1, 4);
					this.b.computerPlayers.setBackground(Color.BLACK);
					this.b.computerPlayers.addMouseListener(this.b.cpl);
					this.b.computerPlayers.addMouseMotionListener(this.b.cpl);
					this.b.frame.setContentPane(this.b.computerPlayers);
					this.b.frame.revalidate();
					this.b.cp = true;
					this.b.hp = false;
				}else if(b.keyCode.equals("3")){
					this.b.numHumanPlayers = 3;
					this.b.computerPlayers = new ChooseNumPlayerScreen2("HOW MANY COMPUTER PLAYERS?", 0, 0, this.b.resolution.width, this.b.resolution.height, 1, 3);
					this.b.computerPlayers.setBackground(Color.BLACK);
					this.b.computerPlayers.addMouseListener(this.b.cpl);
					this.b.computerPlayers.addMouseMotionListener(this.b.cpl);
					this.b.frame.setContentPane(this.b.computerPlayers);
					this.b.frame.revalidate();
					this.b.cp = true;
					this.b.hp = false;
				}else if(b.keyCode.equals("4")){
					this.b.numHumanPlayers = 4;
					this.b.computerPlayers = new ChooseNumPlayerScreen2("HOW MANY COMPUTER PLAYERS?", 0, 0, this.b.resolution.width, this.b.resolution.height, 1, 2);
					this.b.computerPlayers.setBackground(Color.BLACK);
					this.b.computerPlayers.addMouseListener(this.b.cpl);
					this.b.computerPlayers.addMouseMotionListener(this.b.cpl);
					this.b.frame.setContentPane(this.b.computerPlayers);
					this.b.frame.revalidate();
					this.b.cp = true;
					this.b.hp = false;
				}else if(b.keyCode.equals("5")){
					this.b.numHumanPlayers = 5;
					this.b.computerPlayers = new ChooseNumPlayerScreen2("HOW MANY COMPUTER PLAYERS?", 0, 0, this.b.resolution.width, this.b.resolution.height, 1, 1);
					this.b.computerPlayers.setBackground(Color.BLACK);
					this.b.computerPlayers.addMouseListener(this.b.cpl);
					this.b.computerPlayers.addMouseMotionListener(this.b.cpl);
					this.b.frame.setContentPane(this.b.computerPlayers);
					this.b.frame.revalidate();
					this.b.cp = true;
					this.b.hp = false;
				}else if(b.keyCode.equals("6")){
					this.b.numHumanPlayers = 6;
					this.b.numComPlayers = 0;
					//Skip Computer Player Section
					this.b.frame.setContentPane(this.b.charChoose);
					this.b.frame.revalidate();
					this.b.charCh = true;
					this.b.hp = false;
				}
			}
		}else if(this.b.cp){
			Button b = this.b.computerPlayers.getButtonMousedOver();
			if(b != null){
				if(b.keyCode.equals("0")){
					this.b.numComPlayers = 0;
				}else if(b.keyCode.equals("1")){
					this.b.numComPlayers = 1;
				}else if(b.keyCode.equals("2")){
					this.b.numComPlayers = 2;
				}else if(b.keyCode.equals("3")){
					this.b.numComPlayers = 3;
				}else if(b.keyCode.equals("4")){
					this.b.numComPlayers = 4;
				}else if(b.keyCode.equals("5")){
					this.b.numComPlayers = 5;
				}
				//Onto Choosing Characters
				
				this.b.frame.setContentPane(this.b.charChoose);
				this.b.frame.revalidate();
				this.b.charCh = true;
				this.b.cp = false;
			}
			
		}else if(this.b.charCh){
			// Choose Character Stuff
			CharacterHex ch = b.charChoose.getHexMousedOver();
			if(ch != null){
				b.yn = new YesOrNoCharacterPanel( ((int)(b.resolution.width * .1)),((int)(b.resolution.height * .1)), ((int)(b.resolution.width * .8)), ((int)(b.resolution.height * .8)), ch);
				b.frame.revalidate();
				b.charYesNo = true;
				b.charCh = false;
			}
		}else if(this.b.charYesNo){
			Button bb = b.charChoose.yn.getButtonMousedOver();
			if(bb != null){
				if(bb.keyCode.equals("yes")){
					if(b.currentPlayer < b.numHumanPlayers){
						b.charCh = false;
						b.charYesNo = false;
						
						b.hPlayers.add(new Player(b.currentPlayer, b.charChoose.yn.ch));
						
						String[] s = new String[b.currentPlayer];
						
						for(int i = 0; i < s.length; i++){
							s[i] = b.hPlayers.get(i).character.keyCode;
						}
						
						b.charChoose = new CharacterChooser(0, 0, b.resolution.width, b.resolution.height, "Player " + (b.currentPlayer + 1) + ": Choose Your Character", b.currentPlayer + 1, s);
						b.charChoose.setBackground(Color.BLACK);
						b.charChoose.setSize(b.resolution);
						b.charChoose.setPreferredSize(b.resolution);			
						b.charChoose.setFocusable(true);
						b.charChoose.addMouseListener(b.cpl);
						b.charChoose.addMouseMotionListener(b.cpl);
						b.frame.setContentPane(b.charChoose);
						b.frame.revalidate();
						b.charCh = true;
						b.charYesNo = false;
					}else if(b.currentPlayer == b.numHumanPlayers){
						b.hPlayers.add(new Player(b.currentPlayer, b.charChoose.yn.ch));
						
						if(b.currentPlayer < b.numHumanPlayers + b.numComPlayers){
							
							String[] s = new String[b.currentPlayer];
							
							int j = 0;
							for(int i = 0; i < s.length; i++){
								if(i < b.hPlayers.size()){
									s[i] = b.hPlayers.get(i).character.keyCode;
								}else{
									s[i] = b.cPlayers.get(j).character.keyCode;
									j++;
								}
							}
							
							b.charChoose = new CharacterChooser(0, 0, b.resolution.width, b.resolution.height, "Computer Player " + ((b.currentPlayer + 1) - b.numHumanPlayers) + ": Choose Your Character", b.currentPlayer + 1, s);
							b.charChoose.setBackground(Color.BLACK);
							b.charChoose.setSize(b.resolution);
							b.charChoose.setPreferredSize(b.resolution);			
							b.charChoose.setFocusable(true);
							b.charChoose.addMouseListener(b.cpl);
							b.charChoose.addMouseMotionListener(b.cpl);
							b.frame.setContentPane(b.charChoose);
							b.frame.revalidate();
						}else{
							//All Players Chosen
							b.charCh = false;
							b.charYesNo = false;
							b.running = false;
						}
						b.charCh = true;
						b.charYesNo = false;
						
					}else if(b.currentPlayer < b.numHumanPlayers + b.numComPlayers){
						b.cPlayers.add(new Player(b.currentPlayer, b.charChoose.yn.ch));
						
						String[] s = new String[b.currentPlayer];
						
						int j = 0;
						for(int i = 0; i < s.length; i++){
							if(i < b.hPlayers.size()){
								s[i] = b.hPlayers.get(i).character.keyCode;
							}else{
								s[i] = b.cPlayers.get(j).character.keyCode;
								j++;
							}
						}
						
						b.charChoose = new CharacterChooser(0, 0, b.resolution.width, b.resolution.height, "Computer Player " + ((b.currentPlayer + 1) - b.numHumanPlayers) + ": Choose Your Character", b.currentPlayer + 1, s);
						b.charChoose.setBackground(Color.BLACK);
						b.charChoose.setSize(b.resolution);
						b.charChoose.setPreferredSize(b.resolution);			
						b.charChoose.setFocusable(true);
						b.charChoose.addMouseListener(b.cpl);
						b.charChoose.addMouseMotionListener(b.cpl);
						b.frame.setContentPane(b.charChoose);
						b.frame.revalidate();
						b.charCh = true;
						b.charYesNo = false;
					}else if(b.currentPlayer == b.numHumanPlayers + b.numComPlayers){
						if(b.numComPlayers != 0){
							b.cPlayers.add(new Player(b.currentPlayer, b.charChoose.yn.ch));
						}
						
						b.charCh = false;
						b.charYesNo = false;
						//All Players Chosen
						b.running = false;
					}
					b.currentPlayer++;
				}else if(bb.keyCode.equals("no")){
					b.charCh = true;
					b.charYesNo = false;
				}
			}
		}
	}
	
}
