package zbg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class BoardPanel extends JPanel {

	private int x;
	private int y;
	private int length;
	private int width;
	private String filepath;
	private int numberOfLines;
	private ArrayList<Space> spaces;
	private ArrayList<Player> players;
	private ArrayList<Zombie> zombies;
	private int spaceSize;
	private Player pl;
	private boolean win;
	private boolean sounds;

	public BoardPanel(String filePath, int numberOflines, int ss, int zzz) {
		filepath = filePath;
		numberOfLines = numberOflines;
		ArrayList<String> strings = new ArrayList<String>();
		spaceSize = ss;
		win = false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();
			for (int i = 0; i < numberOfLines; i++) {
				String s = reader.readLine();
				strings.add(s);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		spaces = new ArrayList<Space>();
		players = new ArrayList<Player>();
		zombies = new ArrayList<Zombie>();

		for (int i = 0; i < strings.size(); i++) {
			String[] array = new String[7];
			array = strings.get(i).split(" ");
			if (array[0].equals("Space")) {
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int colorNumber = Integer.parseInt(array[5]);
				Color c;
				if (colorNumber == 0) {
					c = Color.BLACK;
				} else if (colorNumber == 1) {
					c = Color.WHITE;
				} else if (colorNumber == 2) {
					c = Color.CYAN;
				} else if (colorNumber == 3) {
					c = Color.GRAY;
				} else if (colorNumber == 4) {
					c = Color.DARK_GRAY;
				} else if (colorNumber == 5) {
					c = Color.BLUE;
				} else if (colorNumber == 6) {
					c = Color.RED;
				} else if (colorNumber == 7) {
					c = Color.ORANGE;
				} else if (colorNumber == 8) {
					c = Color.YELLOW;
				} else if (colorNumber == 9) {
					c = Color.GREEN;
				} else if (colorNumber == 10) {
					c = Color.LIGHT_GRAY;
				} else if (colorNumber == 11) {
					c = Color.MAGENTA;
				} else {
					c = Color.PINK;
				}
				String s = array[6];
				int sn = Integer.parseInt(array[7]);
				if(sn == 70){
					spaces.add(new Space(x, y, 100, 42, c, s, sn, spaceSize));
				}else{
					spaces.add(new Space(x, y, width, height, c, s, sn, spaceSize));
				}

			} else if (array[0].equals("Player")) {
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int colorNumber = Integer.parseInt(array[5]);
				Color c;
				if (colorNumber == 0) {
					c = new Color(0, 0, 50, 255);
				} else if (colorNumber == 1) {
					c = new Color(0, 0, 255, 255);
				} else if (colorNumber == 2) {
					c = new Color(0, 0, 150, 255);
				} else {
					c = new Color(0, 0, 100, 255);
				}
				String s = array[6];
				Player p = new Player(x, y, width, height, c, s, spaceSize);
				pl = p;
				players.add(p);
				pl.changeSpaceNumber(Integer.parseInt(array[7]));
			} else if (array[0].equals("PlayerInfo")) {
				int x = Integer.parseInt(array[1]);
				pl.setInfectCounters(x);
				x = Integer.parseInt(array[2]);
				pl.setHealCounters(x);
				x = Integer.parseInt(array[3]);
				pl.setCountdown(x);
				x = Integer.parseInt(array[4]);
				pl.setZombify(x);
				x = Integer.parseInt(array[5]);
				pl.setMoney(x);
				x = Integer.parseInt(array[6]);
				pl.setFood(x);
			} else if (array[0].equals("I")) {
				if (array[1].equals("W")) {
					pl.addWeapon(array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]));
				} else {
					pl.addSpecialItem(array[2], Integer.parseInt(array[3]));
				}
			} else if (array[0].equals("Zombie")) {
				Zombie z = new Zombie(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), new Color(60, 150, 0, 255), zombies.size(),
						spaceSize, Integer.parseInt(array[5]));
				zombies.add(z);
				z.setCountDown(Integer.parseInt(array[6]));
			}
		}

	}

	public BoardPanel(String filePath, int numberOflines, int ss) {
		filepath = filePath;
		numberOfLines = numberOflines;
		ArrayList<String> strings = new ArrayList<String>();
		spaceSize = ss;
		win = false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();
			for (int i = 0; i < numberOfLines; i++) {
				String s = reader.readLine();
				strings.add(s);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		spaces = new ArrayList<Space>();
		players = new ArrayList<Player>();
		zombies = new ArrayList<Zombie>();

		for (int i = 0; i < strings.size(); i++) {
			String[] array = new String[7];
			array = strings.get(i).split(" ");
			if (array[0].equals("Space")) {
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int colorNumber = Integer.parseInt(array[5]);
				Color c;
				if (colorNumber == 0) {
					c = Color.BLACK;
				} else if (colorNumber == 1) {
					c = Color.WHITE;
				} else if (colorNumber == 2) {
					c = Color.CYAN;
				} else if (colorNumber == 3) {
					c = Color.GRAY;
				} else if (colorNumber == 4) {
					c = Color.DARK_GRAY;
				} else if (colorNumber == 5) {
					c = Color.BLUE;
				} else if (colorNumber == 6) {
					c = Color.RED;
				} else if (colorNumber == 7) {
					c = Color.ORANGE;
				} else if (colorNumber == 8) {
					c = Color.YELLOW;
				} else if (colorNumber == 9) {
					c = Color.GREEN;
				} else if (colorNumber == 10) {
					c = Color.LIGHT_GRAY;
				} else if (colorNumber == 11) {
					c = Color.MAGENTA;
				} else {
					c = Color.PINK;
				}
				String s = array[6];
				int sn = Integer.parseInt(array[7]);
				spaces.add(new Space(x, y, width, height, c, s, sn, spaceSize));

			} else if (array[0].equals("Player")) {
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int colorNumber = Integer.parseInt(array[5]);
				Color c;
				if (colorNumber == 0) {
					c = new Color(0, 0, 50, 255);
				} else if (colorNumber == 1) {
					c = new Color(0, 0, 255, 255);
				} else if (colorNumber == 2) {
					c = new Color(0, 0, 150, 255);
				} else {
					c = new Color(0, 0, 100, 255);
				}
				String s = array[6];
				Player p = new Player(x, y, width, height, c, s, spaceSize);
				players.add(p);
			} else if(array[0].equals("Zombie")){
				this.addZombie(new JLabel(""));
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).draw(g);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw(g);
		}
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).draw(g);
		}
	}

	public ArrayList<Space> getSpaces() {
		return spaces;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public void addZombie(JLabel l) {
		int i = 0;
		for (i = 0; i < zombies.size(); i++)
			;
		Random r = new Random();
		int zspawn = r.nextInt(10) + 1;
		if (zspawn == 1) {
			zombies.add(new Zombie(1295, 895, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 1));
		} else if (zspawn == 2) {
			zombies.add(new Zombie(95, 895, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 13));
		} else if (zspawn == 3) {
			zombies.add(new Zombie(95, 695, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 15));
		} else if (zspawn == 4) {
			zombies.add(new Zombie(1295, 695, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 27));
		} else if (zspawn == 5) {
			zombies.add(new Zombie(1295, 495, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 29));
		} else if (zspawn == 6) {
			zombies.add(new Zombie(95, 495, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 41));
		} else if (zspawn == 7) {
			zombies.add(new Zombie(95, 295, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 43));
		} else if (zspawn == 8) {
			zombies.add(new Zombie(1295, 295, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 55));
		} else if (zspawn == 9) {
			zombies.add(new Zombie(1295, 95, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 57));
		} else {
			zombies.add(new Zombie(95, 95, 10, 10, new Color(60, 150, 0, 255), i, spaceSize, 69));
		}
		l.setText("Zombie Added");
		this.repaint();
	}

	public void checkForZombies(JLabel addInfect) {
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < zombies.size(); j++) {
				if (players.get(i).getSpaceNumber() == zombies.get(j).getSpaceNumber()) {
					players.get(i).addInfectCounter(1);
					if (players.get(i).getInfectCounters() >= 20) {
						addInfect.setText(players.get(i).getText() + " is going through zombification!");
						players.get(i).zombify();
					} else {
						addInfect.setText(players.get(i).getText() + " got an Infect Counter!");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					addInfect.setText("");
				}
			}
		}
	}

	public void checkZombify(JLabel dead, JLabel gameOver) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getZombify() == 1) {
				players.get(i).countDown();
				if (players.get(i).getGameOver() == 1) {
					dead.setText(players.get(i) + " has been fully zombified.");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					players.remove(i);
					if (players.size() == 0) {
						gameOver.setText("Game Over!");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.exit(0);
					}
				}else{
					dead.setText("");
					gameOver.setText("");
				}
			}
		}
	}

	public void zombieCountdown(JLabel l) {
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).countdown();
			if (zombies.get(i).getCountdown() <= 0) {
				zombies.remove(i);
				l.setText("A zombie died");
				this.repaint();
			}
		}
	}

	public void checkSpaceEffect(JLabel l, Player p, JLabel zroll) {
		int space = p.getSpaceNumber();
		if (space == 1) {  //Lab
			p.addHealCounter(5);
			l.setText(p.getText() + " recieved 5 Heal Counters");
		} else if (space == 2) { //open
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if (i >= 1 && i < 6) {
				p.addInfectCounter(1);
				l.setText(p.getText() + " got 1 Infect Counter!");
			} else {
				p.addHealCounter(1);
				l.setText(p.getText() +" recieved 1 Heal Counter");
			}
		} else if (space == 3) { //forest
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if (i > 3) {
				l.setText("Nothing Happens");
			} else {
				p.addHealCounter(1);
				l.setText(p.getText() +" recieved 1 Heal Counter");
			}
		} else if (space == 4) { //abandoned Van
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			l.setText(p.getText() + " moves " + i + " spaces ahead");
			p.move(i, this);
		} else if (space == 5) {//The Dump
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if (i > 3) {
				l.setText(p.getText() + " finds nothing.");
			} else if(i == 3 || i == 2){
				l.setText(p.getText() + " finds 10 pounds of food");
				p.addFood(10);
			}else{
				l.setText(p.getText() + " found a Long Sword (L)");
				p.addWeapon("LongSword", 2, 120);
			}
		} else if (space == 6) {//Toxic waste dump
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if (i > 6) {
				l.setText("Nothing Happens");
			}else{
				l.setText(p.getText() + " gets 3 infect counters");
				p.addInfectCounter(3);
			}

		} else if (space == 7) {//Abandoned Radio Tower
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if(i < 3){
				l.setText(p.getText() + " finds $500!");
				p.addMoney(500);
			}else if(i < 6){
				l.setText(p.getText() + " recieves 2 Heal Counters.");
				p.addHealCounter(2);
			}else{
				l.setText(p.getText() + " finds nothing.");
			}

		} else if (space == 8) {//Abandoned Car
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Random r = new Random();
			int i = r.nextInt(18) + 1;
			l.setText(p.getText() + " moves " + i + " spaces ahead");
			p.move(i, this);

		} else if (space == 9) {//Market
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			
			if(i < 3){
				l.setText("A zombie spawns!");
				this.addZombie(zroll);
			}else{
				l.setText(p.getText() + " finds 50 pounds of food!");
				p.addFood(50);
			}

		} else if (space == 10) {//Hunger
			l.setText(p.getText() + " loses 10 pounds of food.");
			p.addFood(-10);

		} else if (space == 11) {//Chance
			Random r = new Random();
			int i = r.nextInt(6) + 1;
			if(i == 1){
				l.setText("Nothing Happens");
			}else if(i == 2){
				l.setText(p.getText() + " finds a knife! (S)");
				p.addWeapon("Knife", 0, 80);
			}else if(i == 3){
				l.setText(p.getText() + " recieves an infect counter");
				p.addInfectCounter(1);
			}else if(i == 4){
				l.setText(p.getText() + " recieves a heal counter");
				p.addHealCounter(1);
			}else if(i == 5){
				l.setText(p.getText() + " finds $1000!");
				p.addMoney(1000);
			}else{
				l.setText(p.getText() + " loses $100 dollars");
				p.addMoney(100);
			}
			
		} else if (space == 12) {
			Random r = new Random();
			int i = r.nextInt(3) + 2;
			l.setText(i + " zombies spawn!");
			for(int j = 0; j < i; j++){
				this.addZombie(zroll);
			}
		} else if (space == 13) {//Village (small)
			Object[] smStore = new Object[]{"Knife(S) [$200]", "Katana(M) [$400]", "Claymore(L) [$500]", "200 pounds of food [$100]", "Blowtorch(SI) [$100]", "Nothing"};
			boolean shopping = true;
			while(shopping == true){
				int s = JOptionPane.showOptionDialog(null,"What would you like to buy?\nYou have $" + p.getMoney(),"Small Village",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,smStore,null);
				if(s == 0){
					if(p.getMoney() >= 200){
						p.addMoney(-200);
						p.addWeapon("Knife", 0, 80);
						shopping = false;
					}else{
						JOptionPane.showMessageDialog(null, "You can't afford that!");
					}
				}else if(s == 1){
					if(p.getMoney() >= 400){
						p.addMoney(-400);
						p.addWeapon("Katana", 1, 100);
						shopping = false;
					}else{
						JOptionPane.showMessageDialog(null, "You can't afford that!");
					}
				}else if(s == 2){
					if(p.getMoney() >= 500){
						p.addMoney(-500);
						p.addWeapon("Claymore", 2, 120);
						shopping = false;
					}else{
						JOptionPane.showMessageDialog(null, "You can't afford that!");
					}
				}else if(s == 3){
					if(p.getMoney() >= 100){
						p.addMoney(-100);
						p.addFood(200);
						shopping = false;
					}else{
						JOptionPane.showMessageDialog(null, "You can't afford that!");
					}
				}else if(s == 4){
					if(p.getMoney() >= 100){
						p.addMoney(-100);
						p.addSpecialItem("Blowtorch", 3);
						shopping = false;
					}else{
						JOptionPane.showMessageDialog(null, "You can't afford that!");
					}
				}else if(s == 5){
					shopping = false;
				}
			}

		} else if (space == 14) {
			if(p.getHealCounters() >= 40){
				Random r = new Random();
				int i = r.nextInt(4) + 1;
				if(i == 1){
					JOptionPane.showMessageDialog(null, "Your antidote fails and you gain 5 infect counters.");
					p.addHealCounter(-40);
					p.addInfectCounter(5);
				}else if(i == 2){
					JOptionPane.showMessageDialog(null, "Your antidote was a success! You win!");
					win = true;
					l.setText("Win");
				}else if(i == 3){
					JOptionPane.showMessageDialog(null, "Your antidote fails.");
					p.addHealCounter(-40);
					
				}else if(i == 4){
					JOptionPane.showMessageDialog(null, "Your antidote fails but you manage to salvage 20 heal counters from the useless antidote.");
					p.addHealCounter(-20);
				}
			}else{
				JOptionPane.showMessageDialog(null, "You don't have enough heal counters!\nYou have " + p.getHealCounters() + " out of 40.");
			}

		} else if (space == 15) {

		} else if (space == 16) {

		} else if (space == 17) {

		} else if (space == 18) {

		} else if (space == 19) {

		} else if (space == 20) {

		} else if (space == 21) {

		} else if (space == 22) {

		} else if (space == 23) {

		} else if (space == 24) {

		} else if (space == 25) {

		} else if (space == 26) {

		} else if (space == 27) {

		} else if (space == 28) {

		} else if (space == 29) {

		} else if (space == 30) {

		} else if (space == 31) {

		} else if (space == 32) {

		} else if (space == 33) {

		} else if (space == 34) {

		} else if (space == 35) {

		} else if (space == 36) {

		} else if (space == 37) {

		} else if (space == 38) {

		} else if (space == 39) {

		} else if (space == 40) {

		} else if (space == 41) {

		} else if (space == 42) {

		} else if (space == 43) {

		} else if (space == 44) {

		} else if (space == 45) {

		} else if (space == 46) {

		} else if (space == 47) {

		} else if (space == 48) {

		} else if (space == 49) {

		} else if (space == 50) {

		} else if (space == 51) {

		} else if (space == 52) {

		} else if (space == 53) {

		} else if (space == 54) {

		} else if (space == 55) {

		} else if (space == 56) {

		} else if (space == 57) {

		} else if (space == 58) {

		} else if (space == 59) {

		} else if (space == 60) {

		} else if (space == 61) {

		} else if (space == 62) {

		} else if (space == 63) {

		} else if (space == 64) {

		} else if (space == 65) {

		} else if (space == 66) {

		} else if (space == 67) {

		} else if (space == 68) {

		} else if (space == 69) {

		} else {

		}

	}

	public void checkForZombieSpawn(JLabel l) {
		Random r = new Random();
		int zspawn = r.nextInt(10) + 1;
		if (zspawn == 5) {
			this.addZombie(l);
		} else {
			l.setText("No Zombies Spawn This Turn");
		}
	}

	public void takeFood() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).addFood(-1);
		}
	}

	public void fight(Player p, JLabel gameOver) {
		int s = 0;
		int m = 0;
		int l = 0;
		ArrayList<Weapon> sm = new ArrayList<Weapon>();
		ArrayList<Weapon> me = new ArrayList<Weapon>();
		ArrayList<Weapon> la = new ArrayList<Weapon>();
		ArrayList<Item> inv = p.getInventory();
		boolean c = true;
		do {
			for (int i = 0; i < inv.size(); i++) {
				if (inv.get(i) instanceof Weapon) {
					Weapon w = (Weapon) inv.get(i);
					if (w.checkIfBroken() == true) {
						JOptionPane.showMessageDialog(null, p.getText() + "'s " + inv.get(i).getText() + " was broken when went to use it.");
						inv.remove(i);
						c = false;
						i++;
					}
				}
			}
		} while (c = false);

		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i) instanceof Weapon) {
				Weapon w = (Weapon) inv.get(i);
				if (w.getSize() == 0) {
					s++;
					sm.add(w);
				} else if (w.getSize() == 1) {
					m++;
					me.add(w);
				} else {
					l++;
					la.add(w);
				}
			}
		}

		if (l == 0) {
			if (m == 0) {
				// Small weapon attack
				Random r = new Random();
				int attack = r.nextInt(6) + 1;
				if (attack == 1) {
					JOptionPane.showMessageDialog(null, "You miss your attack.");
				} else if (attack == 2) {
					JOptionPane.showMessageDialog(null, "You miss your attack and your weapon loses 10 durablility.");
					sm.get(0).addDurability(-10);
				} else if (attack == 3) {
					JOptionPane.showMessageDialog(null, "You kill a zobmie and your weapon loses 10 durablility.");
					sm.get(0).addDurability(-10);
					zombies.remove(0);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else if (attack == 4) {
					JOptionPane.showMessageDialog(null, "You miss your attack and gain an infect counter.");
					p.addInfectCounter(1);
				} else if (attack == 5) {
					JOptionPane.showMessageDialog(null, "You kill a zobmie.");
					zombies.remove(0);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else if (attack == 6) {
					JOptionPane.showMessageDialog(null, "You miss your attack, gain an infect counter, and your weapon loses 10 durability.");
					sm.get(0).addDurability(-10);
					p.addInfectCounter(1);
				}
			} else {
				// Medium Weapon attack
				Random r = new Random();
				int attack = r.nextInt(6) + 1;
				if (attack == 1) {
					JOptionPane.showMessageDialog(null, "You miss your attack.");
				} else if (attack == 2) {
					JOptionPane.showMessageDialog(null, "You miss your attack and your weapon loses 10 durablility.");
					me.get(0).addDurability(-10);
				} else if (attack == 3) {
					JOptionPane.showMessageDialog(null, "You kill a zobmie.");
					zombies.remove(0);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else if (attack == 4) {
					JOptionPane.showMessageDialog(null, "You kill a zombie and your weapon loses 10 durability.");
					me.get(0).addDurability(-10);
					zombies.remove(0);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else if (attack == 5) {
					JOptionPane.showMessageDialog(null, "You kill a zobmie and gain a heal counter.");
					zombies.remove(0);
					p.addHealCounter(1);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else if (attack == 6) {
					JOptionPane.showMessageDialog(null, "You miss your attack, gain an infect counter, and your weapon loses 10 durability.");
					me.get(0).addDurability(-10);
					p.addInfectCounter(1);
				}
			}
		} else {
			// Large Weapon attack
			Random r = new Random();
			int attack = r.nextInt(8) + 1;
			if (attack == 1) {
				JOptionPane.showMessageDialog(null, "You miss your attack.");
			} else if (attack == 2) {
				JOptionPane.showMessageDialog(null, "You miss your attack and your weapon loses 10 durablility.");
				la.get(0).addDurability(-10);
			} else if (attack == 3) {
				JOptionPane.showMessageDialog(null, "You kill a zobmie and gain a heal counter.");
				p.addHealCounter(1);
				zombies.remove(0);
				if(zombies.size() == 1){
					gameOver.setText("There is " + zombies.size() + " zombie left");
				}else{
					gameOver.setText("There are " + zombies.size() + " zombies left");	
				}
			} else if (attack == 4) {
				JOptionPane.showMessageDialog(null, "You kill a zombie and your weapon loses 10 durability.");
				la.get(0).addDurability(-10);
				zombies.remove(0);
				if(zombies.size() == 1){
					gameOver.setText("There is " + zombies.size() + " zombie left");
				}else{
					gameOver.setText("There are " + zombies.size() + " zombies left");	
				}
			} else if (attack == 5) {
				JOptionPane.showMessageDialog(null, "You kill a zobmie.");
				zombies.remove(0);
				if(zombies.size() == 1){
					gameOver.setText("There is " + zombies.size() + " zombie left");
				}else{
					gameOver.setText("There are " + zombies.size() + " zombies left");	
				}
			} else if (attack == 6) {
				JOptionPane.showMessageDialog(null, "You miss your attack, gain an infect counter, and your weapon loses 10 durability.");
				la.get(0).addDurability(-10);
				p.addInfectCounter(1);
			} else if (attack == 7) {
				JOptionPane.showMessageDialog(null, "You kill a zobmie and gain a heal counter.");
				zombies.remove(0);
				p.addHealCounter(1);
				if(zombies.size() == 1){
					gameOver.setText("There is " + zombies.size() + " zombie left");
				}else{
					gameOver.setText("There are " + zombies.size() + " zombies left");	
				}
			} else if (attack == 8) {
				if (zombies.size() > 1) {
					JOptionPane.showMessageDialog(null, "You become the ultmate killing machine and kill all " + zombies.size() + " zombies!");
					for (int t = 0; t < zombies.size(); t++) {
						zombies.remove(0);
					}
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				} else {
					JOptionPane.showMessageDialog(null, "You kill a zobmie and hit the motherload! You gain 5 heal counters!");
					zombies.remove(0);
					p.addHealCounter(5);
					if(zombies.size() == 1){
						gameOver.setText("There is " + zombies.size() + " zombie left");
					}else{
						gameOver.setText("There are " + zombies.size() + " zombies left");	
					}
				}
			}

		}
	}

	public void save(JButton save, int resx, int resy) {
		save.setText("Saving Game...");
		save.setActionCommand("saving");
		BufferedWriter writer = null;
		int num = 0;
		num = new File("Saves").listFiles(new FilenameFilter() { public boolean accept(File dir, String filename) { return filename.endsWith(".rydge"); }} ).length;
		try {
			writer = new BufferedWriter(new FileWriter("Saves/save" + num + ".rydge"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Saving as Saves/save" + num + ".rydge");
		try {
			// Stuff from boardPanel
			writer.write(resx + " " + resy + " " + spaceSize);
			writer.newLine();

			for (int i = 0; i < spaces.size(); i++) {
				writer.write("Space" + " " + spaces.get(i).getX() + " " + spaces.get(i).getY() + " " + spaceSize + " " + spaceSize + " " + spaces.get(i).getColorNumber() + " "
						+ spaces.get(i).getText() + " " + spaces.get(i).getSpaceNumber());
				writer.newLine();
			}

			for (int i = 0; i < players.size(); i++) {
				writer.write("Player" + " " + players.get(i).getX() + " " + players.get(i).getY() + " " + players.get(i).getWidth() + " " + players.get(i).getHeight() + " "
						+ players.get(i).getColorNumber() + " " + players.get(i).getText() + " " + players.get(i).getSpaceNumber());
				writer.newLine();
				writer.write("PlayerInfo" + " " + players.get(i).getInfectCounters() + " " + players.get(i).getHealCounters() + " " + players.get(i).getCountDown() + " " + players.get(i).getZombify()
						+ " " + players.get(i).getMoney() + " " + players.get(i).getFood());
				writer.newLine();
				for (int j = 0; j < players.get(i).getInventory().size(); j++) {
					if (players.get(i).getInventory().get(j) instanceof Weapon) {
						Weapon w = (Weapon) players.get(i).getInventory().get(j);
						writer.write("I" + " " + "W" + " " + w.getText() + " " + w.getSize() + " " + w.getDurability());
						writer.newLine();
					} else {
						SpecialItem w = (SpecialItem) players.get(i).getInventory().get(j);
						writer.write("I" + " " + "SI" + " " + w.getText() + " " + w.getUses());
						writer.newLine();
					}
				}
			}

			for (int i = 0; i < zombies.size(); i++) {
				if(i == zombies.size() - 1){
					writer.write("Zombie" + " " + zombies.get(i).getX() + " " + zombies.get(i).getY() + " " + zombies.get(i).getWidth() + " " + zombies.get(i).getHeight() + " "
							+ zombies.get(i).getSpaceNumber() + " " + zombies.get(i).getCountdown());
				}else{
					writer.write("Zombie" + " " + zombies.get(i).getX() + " " + zombies.get(i).getY() + " " + zombies.get(i).getWidth() + " " + zombies.get(i).getHeight() + " "
							+ zombies.get(i).getSpaceNumber() + " " + zombies.get(i).getCountdown());
					writer.newLine();
				}
			}

			writer.close();
			save.setText("Game Saved");
			save.setActionCommand("Save");
			save.setText("Save Game");
			JOptionPane.showMessageDialog(null, "Game Saved");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public boolean checkForWin(){
		return win;
	}
	
	public void doSoundEffects(JButton j){
		Object[] options = new Object[]{"On", "Off"};
		int x = JOptionPane.showOptionDialog(null, "Would you like sound effects on or off?", "Sounds", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if(x == 0){
			sounds = true;
			j.setText("Turn Off Sounds (G)");
		}else{
			sounds = false;
			j.setText("Turn On Sounds (G)");
		}
	}
	
	public boolean getSounds(){
		return sounds;
	}
	
	public void switchSounds(JButton j){
		if(sounds == true){
			sounds = false;
			j.setText("Turn On Sounds (G)");
		}else{
			sounds = true;
			j.setText("Turn Off Sounds (G)");
		}
	}
	
	public void soundEffects(int soundNumber){
		if(soundNumber == 0){ //Player Begins Turn
			try {
			    File yourFile = new File("Sounds/player.wav");
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
			    //gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
			    clip.start();
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
}
