package zbg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class CreateBoard implements ActionListener, Runnable, KeyListener {

	private JFrame frame;
	private String filePath;
	private int numberOfLines;
	private int resX;
	private int resY;
	private JPanel mainPanel;
	private StatPanel sp;
	private BoardPanel bp;
	private Thread th;
	private JLabel currentPlayer;
	private int dice;
	private int zdice;
	private int timeToRoll;
	private JLabel rolled;
	private JButton infectCounterList;
	private JButton healCounterList;
	private JLabel zrolled;
	private JLabel addInfect;
	private JLabel dead;
	private JLabel gameOver;
	private JButton zombifyList;
	private JButton countdownList;
	private JButton cure;
	private JButton invent;
	private JButton moneh;
	private JLabel spaceEffect;
	private int spaceSize;
	private Player player;
	private JButton foodz;
	private JButton fight;
	private JButton save;
	private JButton quit;
	private JButton ruleBook;
	private JButton b;
	private int konamiCode;
	private JButton sound;
	private boolean cntrl;

	public CreateBoard(String filePath, int zzz) {
		cntrl = false;
		this.filePath = filePath;
		timeToRoll = 0;
		BufferedReader reader;
		numberOfLines = 0;
		String res = "";
		konamiCode = 0;

		try {
			reader = new BufferedReader(new FileReader(this.filePath));
			res = reader.readLine();
			while (reader.readLine() != null)
				numberOfLines++;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] array = new String[3];
		array = res.split(" ");
		resX = Integer.parseInt(array[0]);
		resY = Integer.parseInt(array[1]);
		spaceSize = Integer.parseInt(array[2]);

		frame = new JFrame();
		frame.setSize(resX, resY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zombie Game");

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints r = new GridBagConstraints();
		r.fill = GridBagConstraints.BOTH;
		r.gridx = 1;
		r.gridy = 0;
		r.weightx = .20;
		r.weighty = 1;
		r.anchor = GridBagConstraints.EAST;

		sp = new StatPanel();
		sp.setLayout(new BoxLayout(sp, BoxLayout.Y_AXIS));
		sp.setBackground(new Color(252, 230, 201, 255));

		b = new JButton("Roll (R)");
		b.addActionListener(this);

		rolled = new JLabel("Roll The Dice");
		rolled.setAlignmentX(Component.CENTER_ALIGNMENT);

		spaceEffect = new JLabel("");
		spaceEffect.setAlignmentX(Component.CENTER_ALIGNMENT);
		spaceEffect.setForeground(Color.BLUE);

		zrolled = new JLabel("No Zombies");
		zrolled.setAlignmentX(Component.CENTER_ALIGNMENT);

		addInfect = new JLabel("");
		addInfect.setAlignmentX(Component.CENTER_ALIGNMENT);
		dead = new JLabel("");
		gameOver = new JLabel("");
		dead.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

		currentPlayer = new JLabel("Player 1");
		currentPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setActionCommand("Roll");

		infectCounterList = new JButton("Infect Counters (N)");
		infectCounterList.setAlignmentX(Component.CENTER_ALIGNMENT);
		infectCounterList.addActionListener(this);
		infectCounterList.setActionCommand("Infect");
		healCounterList = new JButton("Heal Counters (H)");
		healCounterList.setAlignmentX(Component.CENTER_ALIGNMENT);
		healCounterList.addActionListener(this);
		healCounterList.setActionCommand("Heal");
		zombifyList = new JButton("Check Zombified (Z)");
		zombifyList.setAlignmentX(Component.CENTER_ALIGNMENT);
		zombifyList.addActionListener(this);
		zombifyList.setActionCommand("Zombie");
		countdownList = new JButton("Check Countdowns (X)");
		countdownList.setAlignmentX(Component.CENTER_ALIGNMENT);
		countdownList.addActionListener(this);
		countdownList.setActionCommand("Countdown");
		cure = new JButton("Cure Someone (C)");
		cure.setAlignmentX(Component.CENTER_ALIGNMENT);
		cure.addActionListener(this);
		cure.setActionCommand("Cure");
		invent = new JButton("Inventory (I)");
		invent.setAlignmentX(Component.CENTER_ALIGNMENT);
		invent.addActionListener(this);
		invent.setActionCommand("Inventory");
		moneh = new JButton("Money (M)");
		moneh.setAlignmentX(Component.CENTER_ALIGNMENT);
		moneh.addActionListener(this);
		moneh.setActionCommand("Money");
		foodz = new JButton("Food (F)");
		foodz.setAlignmentX(Component.CENTER_ALIGNMENT);
		foodz.addActionListener(this);
		foodz.setActionCommand("Food");
		fight = new JButton("Fight a Zombie (T)");
		fight.setAlignmentX(Component.CENTER_ALIGNMENT);
		fight.addActionListener(this);
		fight.setActionCommand("Fight");
		save = new JButton("Save Game (S)");
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.addActionListener(this);
		save.setActionCommand("Save");
		quit = new JButton("Quit (Q)");
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.addActionListener(this);
		quit.setActionCommand("Quit");
		ruleBook = new JButton("Rules (U)");
		ruleBook.setAlignmentX(Component.CENTER_ALIGNMENT);
		ruleBook.addActionListener(this);
		ruleBook.setActionCommand("RuleBook");
		sound = new JButton("");
		sound.setAlignmentX(Component.CENTER_ALIGNMENT);
		sound.addActionListener(this);
		sound.setActionCommand("Sound");

		sp.addKeyListener(this);

		sp.setFocusable(true);
		sp.add(currentPlayer);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(b);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(rolled);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(infectCounterList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(healCounterList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(zrolled);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(spaceEffect);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(zombifyList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(countdownList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(cure);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(invent);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(moneh);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(foodz);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(fight);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(dead);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(addInfect);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(gameOver);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(ruleBook);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(sound);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(save);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(quit);
		mainPanel.add(sp, r);

		r.fill = GridBagConstraints.BOTH;
		r.anchor = GridBagConstraints.WEST;
		r.gridx = 0;
		r.gridy = 0;
		r.weightx = .80;
		r.weighty = 1;

		bp = new BoardPanel(this.filePath, numberOfLines, spaceSize, 0);
		bp.setBackground(Color.BLACK);
		bp.doSoundEffects(sound);
		mainPanel.add(bp, r);

		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		th = new Thread(this);
		th.start();
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	public CreateBoard(String filePath) {
		cntrl = false;
		this.filePath = filePath;
		timeToRoll = 0;
		BufferedReader reader;
		numberOfLines = 0;
		String res = "";
		konamiCode = 0;

		try {
			reader = new BufferedReader(new FileReader(this.filePath));
			res = reader.readLine();
			while (reader.readLine() != null)
				numberOfLines++;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] array = new String[3];
		array = res.split(" ");
		resX = Integer.parseInt(array[0]);
		resY = Integer.parseInt(array[1]);
		spaceSize = Integer.parseInt(array[2]);

		frame = new JFrame();
		frame.setSize(resX, resY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zombie Game");

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints r = new GridBagConstraints();
		r.fill = GridBagConstraints.BOTH;
		r.gridx = 1;
		r.gridy = 0;
		r.weightx = .20;
		r.weighty = 1;
		r.anchor = GridBagConstraints.EAST;

		sp = new StatPanel();
		sp.setLayout(new BoxLayout(sp, BoxLayout.Y_AXIS));
		sp.setBackground(new Color(252, 230, 201, 255));

		b = new JButton("Roll (R)");
		b.addActionListener(this);

		rolled = new JLabel("Roll The Dice");
		rolled.setAlignmentX(Component.CENTER_ALIGNMENT);

		spaceEffect = new JLabel("");
		spaceEffect.setAlignmentX(Component.CENTER_ALIGNMENT);

		zrolled = new JLabel("No Zombies");
		zrolled.setAlignmentX(Component.CENTER_ALIGNMENT);
		spaceEffect.setForeground(Color.BLUE);

		addInfect = new JLabel("");
		addInfect.setAlignmentX(Component.CENTER_ALIGNMENT);
		dead = new JLabel("");
		gameOver = new JLabel("");
		dead.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

		currentPlayer = new JLabel("Player 1");
		currentPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setActionCommand("Roll");

		infectCounterList = new JButton("Infect Counters (N)");
		infectCounterList.setAlignmentX(Component.CENTER_ALIGNMENT);
		infectCounterList.addActionListener(this);
		infectCounterList.setActionCommand("Infect");
		healCounterList = new JButton("Heal Counters (H)");
		healCounterList.setAlignmentX(Component.CENTER_ALIGNMENT);
		healCounterList.addActionListener(this);
		healCounterList.setActionCommand("Heal");
		zombifyList = new JButton("Check Zombified (Z)");
		zombifyList.setAlignmentX(Component.CENTER_ALIGNMENT);
		zombifyList.addActionListener(this);
		zombifyList.setActionCommand("Zombie");
		countdownList = new JButton("Check Countdowns (X)");
		countdownList.setAlignmentX(Component.CENTER_ALIGNMENT);
		countdownList.addActionListener(this);
		countdownList.setActionCommand("Countdown");
		cure = new JButton("Cure Someone (C)");
		cure.setAlignmentX(Component.CENTER_ALIGNMENT);
		cure.addActionListener(this);
		cure.setActionCommand("Cure");
		invent = new JButton("Inventory (I)");
		invent.setAlignmentX(Component.CENTER_ALIGNMENT);
		invent.addActionListener(this);
		invent.setActionCommand("Inventory");
		moneh = new JButton("Money (M)");
		moneh.setAlignmentX(Component.CENTER_ALIGNMENT);
		moneh.addActionListener(this);
		moneh.setActionCommand("Money");
		foodz = new JButton("Food (F)");
		foodz.setAlignmentX(Component.CENTER_ALIGNMENT);
		foodz.addActionListener(this);
		foodz.setActionCommand("Food");
		fight = new JButton("Fight a Zombie (T)");
		fight.setAlignmentX(Component.CENTER_ALIGNMENT);
		fight.addActionListener(this);
		fight.setActionCommand("Fight");
		save = new JButton("Save Game (S)");
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.addActionListener(this);
		save.setActionCommand("Save");
		quit = new JButton("Quit (Q)");
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.addActionListener(this);
		quit.setActionCommand("Quit");
		ruleBook = new JButton("Rules (U)");
		ruleBook.setAlignmentX(Component.CENTER_ALIGNMENT);
		ruleBook.addActionListener(this);
		ruleBook.setActionCommand("RuleBook");
		sound = new JButton("");
		sound.setAlignmentX(Component.CENTER_ALIGNMENT);
		sound.addActionListener(this);
		sound.setActionCommand("Sound");

		sp.addKeyListener(this);

		sp.setFocusable(true);
		sp.add(currentPlayer);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(b);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(rolled);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(infectCounterList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(healCounterList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(zrolled);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(spaceEffect);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(zombifyList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(countdownList);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(cure);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(invent);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(moneh);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(foodz);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(fight);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(addInfect);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(dead);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(gameOver);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(ruleBook);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(sound);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(save);
		sp.add(Box.createRigidArea(new Dimension(0, 20)));
		sp.add(quit);
		mainPanel.add(sp, r);

		r.fill = GridBagConstraints.BOTH;
		r.anchor = GridBagConstraints.WEST;
		r.gridx = 0;
		r.gridy = 0;
		r.weightx = .80;
		r.weighty = 1;

		bp = new BoardPanel(this.filePath, numberOfLines, spaceSize);
		bp.setBackground(Color.BLACK);
		bp.doSoundEffects(sound);
		mainPanel.add(bp, r);

		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		th = new Thread(this);
		th.start();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Roll")) {
			if (timeToRoll == 1) {
				synchronized (th) {
					Random r = new Random();
					dice = r.nextInt(6) + 1;
					th.notify();
					timeToRoll = 0;
					b.setEnabled(false);
				}
			}
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Infect")) {
			String s = "";
			ArrayList<Player> players = bp.getPlayers();
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getInfectCounters() == 1) {
					s = s + (players.get(i).getText() + " Has " + players.get(i).getInfectCounters() + " Infect Counter");
				} else {
					s = s + (players.get(i).getText() + " Has " + players.get(i).getInfectCounters() + " Infect Counters");
				}
				if (i < players.size() - 1) {
					s = s + "\n";
				}
			}
			JOptionPane.showMessageDialog(null, s);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Heal")) {
			String s = "";
			ArrayList<Player> players = bp.getPlayers();
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getHealCounters() == 1) {
					s = s + (players.get(i).getText() + " Has " + players.get(i).getHealCounters() + " Heal Counter");
				} else {
					s = s + (players.get(i).getText() + " Has " + players.get(i).getHealCounters() + " Heal Counters");
				}
				if (i < players.size() - 1) {
					s = s + "\n";
				}
			}
			JOptionPane.showMessageDialog(null, s);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Zombie")) {
			String s = "";
			ArrayList<Player> players = bp.getPlayers();

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getZombify() == 1) {
					s = s + players.get(i).getText() + " is going through Zombification.\n";
				}
			}
			if (s.equals("")) {
				s = "There are no players going through zombification.";
			}
			JOptionPane.showMessageDialog(null, s);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Countdown")) {
			String s = "";
			ArrayList<Player> players = bp.getPlayers();

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getCountDown() < 10) {
					s = s + players.get(i).getText() + " has " + players.get(i).getCountDown() + " turns left.";
				} else if (players.get(i).getCountDown() == 10 && players.get(i).getZombify() == 1) {
					s = s + players.get(i).getText() + " has " + players.get(i).getCountDown() + " turns left.";
				}
			}
			if (s.equals("")) {
				s = "No players have a countdown at this time";
			}
			JOptionPane.showMessageDialog(null, s);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Cure")) {
			ArrayList<Player> players = bp.getPlayers();
			int j = 0;
			ArrayList<Object> o = new ArrayList<Object>();

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getZombify() == 1) {
					j++;
					o.add(players.get(i));
				}
			}
			if (j == 0) {
				JOptionPane.showMessageDialog(null, "No one needs curing right now");
			} else {
				j = 0;
				ArrayList<Object> oo = new ArrayList<Object>();
				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getHealCounters() >= 10) {
						j++;
						oo.add(players.get(i));
					}
				}

				if (j == 0) {
					JOptionPane.showMessageDialog(null, "No one has enough Heal Counters to cure anyone.");
				} else {
					Object[] toCure = o.toArray();
					Object[] curer = oo.toArray();

					int dec1 = JOptionPane.showOptionDialog(null, "Who is doing the curing?", "Who loses heal counters?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, curer,
							null);
					Player p = (Player) curer[dec1];
					p.addHealCounter(-10);
					int dec2 = JOptionPane.showOptionDialog(null, "Who is being cured?", "Cure who?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, toCure, null);
					Player pp = (Player) toCure[dec2];
					pp.cure();
					addInfect.setText(pp.getText() + " Was Cured!");

				}

			}

			sp.grabFocus();

		} else if (e.getActionCommand().equals("Inventory")) {
			ArrayList<Item> inv = player.getInventory();
			String s = "";
			for (int i = 0; i < inv.size(); i++) {
				if (i == inv.size() - 1) {
					if (inv.get(i) instanceof Weapon) {
						Weapon w = (Weapon) inv.get(i);
						s = s + "Weapon: " + w.getText() + " size: " + w.getSize() + " Durability: " + w.getDurability();
					} else {
						SpecialItem si = (SpecialItem) inv.get(i);
						s = s + "Special Item: " + si.getText() + " Uses Left: " + si.getUses();
					}
				} else {
					if (inv.get(i) instanceof Weapon) {
						Weapon w = (Weapon) inv.get(i);
						s = s + "Weapon: " + w.getText() + " size: " + w.getSize() + " Durability: " + w.getDurability() + "\n";
					} else {
						SpecialItem si = (SpecialItem) inv.get(i);
						s = s + "Special Item: " + si.getText() + " Uses Left: " + si.getUses() + "\n";
					}
				}
			}
			if (inv.size() == 0) {
				s = "You have nothing in your inventory";
			}
			JOptionPane.showMessageDialog(null, s);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Money")) {
			int money = player.getMoney();
			if (money == 1) {
				JOptionPane.showMessageDialog(null, "You have " + money + " dollar.");
			} else {
				JOptionPane.showMessageDialog(null, "You have " + money + " dollars.");
			}
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Food")) {
			int x = player.getFood();
			if (x == 1) {
				JOptionPane.showMessageDialog(null, "You have " + x + " pound of food.");
			} else {
				JOptionPane.showMessageDialog(null, "You have " + x + " pounds of food.");
			}
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Fight")) {
			ArrayList<Zombie> zom = bp.getZombies();
			int j = 0;
			for (int i = 0; i < zom.size(); i++) {
				j++;
			}

			if (j == 0) {
				JOptionPane.showMessageDialog(null, "There are no zombies to fight");
			} else {
				ArrayList<Item> inv = player.getInventory();
				j = 0;
				for (int i = 0; i < inv.size(); i++) {
					if (inv.get(i) instanceof Weapon) {
						j++;
					}
				}
				if (j == 0) {
					JOptionPane.showMessageDialog(null, "You have no weapons to fight with");
				} else {
					bp.fight(player, gameOver);
				}

			}
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Save")) {
			bp.save(save, resX, resY);
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("RuleBook")) {
			JOptionPane.showMessageDialog(null, "--Rules and Instructions--\n" + "SECTION I - PLAYERS\n" + "  1. Each player starts out with 100 pounds of food.\n"
					+ "  2. Each player starts out with $100.\n" + "  3.Players start out with an empty inventory, but can gain an unlimited number of items.\n" + "SECTION II - SPACES AND MOVEMENT\n"
					+ "  1. To move, each player must roll.  (Possible movements are 1-6)\n" + "  2. At the end of a player's movement, the space they land on will apply it's effect.\n"
					+ "  3. Spaces have many different effects.  Some examples are losing or gaining heal counters, moving ahead, gaining items or weapons, spawning zombies, and more.\n"
					+ "  4. If a space causes you to move forward and land on another space, only the first space's effect will apply.\n" + "SECTION III - SPECIAL SPACES\n  "
					+ "1. There are 2 types of special spaces: 'Village' and 'Win?'.\n" + "  2. 'Village' spaces allow you to buy items, food, and other supplies if you have the money.\n"
					+ "  3. 'Win?' spaces allow you to attempt to create an antidote if you have enough heal counters (See GAMEPLAY)\n" + "SECTION IV - ZOMBIES\n"
					+ "  1. Zombies are like players.  They move around the board every turn, but their movement is restrected to 1-3 moves instead of 1-6.\n"
					+ "  2. If a zombie is on the same space as a player when the turn ends, that player will gain an infect counter (See GAMEPLAY)\n"
					+ "  3. Zombies are created with a Countdown of 10.  After each turn, the countdown goes down by one.  When the countdown reaches zero, the zombie dies.\n"
					+ "  4. Zombies are unaffected by the effects of spaces.\n" + "SECTION V - GAMEPLAY\n  1. Infect Counters\n    a. Infect counters are given to players from various events.\n"
					+ "    b. If a player recieves 20 or more infect counters, they begin the zombification process.\n"
					+ "    c. Beginning the zombification process starts a countdown of 10 turns.\n"
					+ "    d. At the end of the 10 turns, the player becomes a zombie and is no longer part of the game.\n"
					+ "    e. Before the countdown reaches 0, the infected player can be cured if another player has enough heal counters.\n" + "  2.Heal Counters\n"
					+ "    a. Heal Counters are given to players from various events.\n"
					+ "    b. If a player has 10 heal counters, they can cure another player (or themself) by using them.  This will cause all of the cured player's infect counters to disapear\n"
					+ "    c. If a player has 40 heal counters and lands on a 'Win?' space, they have a chance of creating an antidoe and winning the game.\n" + "  3.Fighting\n"
					+ "    a. If a player has a weapon in their inventory and there are zombies on the board, a player can choose the 'Fight a Zombie' opiton\n"
					+ "    b. The size of the weapon can make a difference. (See Items)\n" + "    c. Attacks can be risky.  If the attack fails, the player who attacked may gain infect counters\n"
					+ "  4.Food\n" + "    a. At the end of every turn, 1 pound of food is taken from each player.\n"
					+ "    b. If the player has 0 pounds of food at the end of the turn, they will gain an infect counter\n"
					+ "    c. Some spaces have effects that cause you to lose or gain food.  Keep your eyes open.\n" + "SECTION VI - ITEMS\n" + "  1. Weapons\n" + "    a. Types of Weapons\n"
					+ "      I. Small(S)(0) weapons have the smallest chance of killing a zombie in a fight\n"
					+ "      II. Medium(M)(1) weapons have a medium chance of killing a zombie in a fight.\n"
					+ "      III. Large(L)(2) weapons have the largest chance of killing a zobmie in a fight.\n"
					+ "      IV. Large(L)(2) weapons have a special attack that can kill all zombies on the board if the attacking player gets lucky.\n" + "    b. Durablility\n"
					+ "      I. Every weapon has a durability number. Small(S)(0) start out with 80, Medium(M)(1) start out with 100, and Large(L)(2) start out with 120\n"
					+ "      II. During a fight, your weapon may lose durability.  If a weapon's durability reaches zero, the weapon will break.\n" + "  2. Special Items(SI)\n"
					+ "    a. There are many types of special items.  Each one will effect certain game spaces differently.  You never know what will happen.\n"
					+ "    b. Each special item has a set number of uses.  Each time it's used, that number is reduced by 1.  If the number reaches zero, that Special Item breaks.");
			sp.grabFocus();
		} else if (e.getActionCommand().equals("Sound")) {
			bp.switchSounds(sound);
			sp.grabFocus();
		} else {

		}
	}

	public void run() {
		ArrayList<Space> spaces = bp.getSpaces();
		ArrayList<Player> players = bp.getPlayers();
		ArrayList<Zombie> zombies = bp.getZombies();
		while (true) {
			if (zombies.size() == 1) {
				gameOver.setText("There is " + zombies.size() + " zombie left");
			} else {
				gameOver.setText("There are " + zombies.size() + " zombies left");
			}

			for (int i = 0; i < players.size(); i++) {
				if (bp.getSounds() == true) {
					bp.soundEffects(0);
				}
				currentPlayer.setText(players.get(i).getText());
				player = players.get(i);
				timeToRoll = 1;
				b.setEnabled(true);
				synchronized (th) {
					try {
						th.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				rolled.setText(players.get(i).getText() + " rolled a " + dice);
				players.get(i).move(dice, bp);
				bp.checkSpaceEffect(spaceEffect, players.get(i), zrolled);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rolled.setText("Next Turn");
			}
			for (int i = 0; i < zombies.size(); i++) {
				Random r = new Random();
				zdice = r.nextInt(3) + 1;
				String s = "Zombie" + zombies.get(i).getZombieNumber() + " Rolled a " + zdice;
				zrolled.setText(s);
				zombies.get(i).move(zdice, bp);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i == zombies.size() - 1) {
					zrolled.setText("All Zombies Have Moved");
				}
			}
			bp.checkForZombies(addInfect);
			bp.checkZombify(dead, gameOver);
			bp.zombieCountdown(rolled);
			bp.checkForZombieSpawn(zrolled);
			bp.takeFood();
			if (bp.checkForWin() == true) {
				int g = 0;
				while (true) {

				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == (e.VK_R) || e.getKeyCode() == (e.VK_ENTER) || e.getKeyCode() == (e.VK_SPACE)) {
			b.doClick();
		} else if (e.getKeyCode() == (e.VK_N)) {
			infectCounterList.doClick();
		} else if (e.getKeyCode() == (e.VK_H)) {
			healCounterList.doClick();
		} else if (e.getKeyCode() == (e.VK_Z)) {
			zombifyList.doClick();
		} else if (e.getKeyCode() == (e.VK_X)) {
			countdownList.doClick();
		} else if (e.getKeyCode() == (e.VK_C)) {
			if (cntrl == true) {
				String input = JOptionPane.showInputDialog(null, "Enter Cheat");
				if (input == null) {

				} else {
					ArrayList<Player> rr = bp.getPlayers();
					if (input.equalsIgnoreCase("money")) {
						JOptionPane.showMessageDialog(null, "Each player gains $10,000!");
						for (int bb = 0; bb < rr.size(); bb++) {
							rr.get(bb).addMoney(10000);
						}
					} else if (input.equalsIgnoreCase("healCounters")) {
						JOptionPane.showMessageDialog(null, "Each player gains 40 Heal Counters!");
						for (int bb = 0; bb < rr.size(); bb++) {
							rr.get(bb).addHealCounter(40);
						}
					} else if (input.equalsIgnoreCase("liveLifeOnHardMode")) {
						for (int i = 0; i < 20; i++) {
							bp.addZombie(zrolled);
						}
						JOptionPane.showMessageDialog(null, "20 zombies just spawned!");
					} else if (input.equalsIgnoreCase("food")) {
						JOptionPane.showMessageDialog(null, "Each player gains 200 pounds of food!");
						for (int bb = 0; bb < rr.size(); bb++) {
							rr.get(bb).addFood(200);
						}
					} else if (input.equalsIgnoreCase("weapons")) {
						JOptionPane.showMessageDialog(null, "Each player gains one small weapon, one medium weapon, and one large weapon!");
						for (int bb = 0; bb < rr.size(); bb++) {
							rr.get(bb).addWeapon("Dagger", 0, 80);
							rr.get(bb).addWeapon("Rifle", 1, 100);
							rr.get(bb).addWeapon("Cannon", 2, 120);
						}
					} else if (input.equalsIgnoreCase("8675309")) {

						Random r = new Random();
						int bgcolor = r.nextInt(5) + 1;
						if (bgcolor == 1) {
							bp.setBackground(Color.BLACK);
						} else if (bgcolor == 2) {
							bp.setBackground(Color.WHITE);
						} else if (bgcolor == 3) {
							bp.setBackground(Color.RED);
						} else if (bgcolor == 4) {
							bp.setBackground(Color.GREEN);
						} else if (bgcolor == 5) {
							bp.setBackground(Color.BLUE);
						}
						JOptionPane.showMessageDialog(null, "Board background color changed");
					} else if (input.equalsIgnoreCase("music")) {
						try {
							File yourFile = new File("Sounds/music.wav");
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
							// gainControl.setValue(-10.0f); // Reduce volume by
							// 10 decibels.
							clip.start();
						} catch (Exception poop) {
							System.err.println(poop.getMessage());
						}
					} else if (input.equalsIgnoreCase("killAllZombies")) {
						for (int rit = 0; rit < bp.getZombies().size(); rit++) {
							bp.getZombies().remove(0);
						}
						bp.repaint();
						JOptionPane.showMessageDialog(null, "All zombies have been killed.");
					} else if (input.equalsIgnoreCase("movePlayer")) {
						String silly = JOptionPane.showInputDialog(null, "Move how many spaces ahead? (limit is 1 to 6)");
						if (silly.equals("1") || silly.equals("2") || silly.equals("3") || silly.equals("4") || silly.equals("5") || silly.equals("6")) {
							player.move(Integer.parseInt(silly), bp);
						} else {
							JOptionPane.showMessageDialog(null, "'" + silly + "' is not a number between 1 and 6");
						}
					} else if (input.equalsIgnoreCase("CrashGame_0")){
						do{
							try {
								File yourFile = new File("Sounds/music.wav");
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
								// gainControl.setValue(-10.0f); // Reduce volume by
								// 10 decibels.
								clip.start();
							} catch (Exception poop) {
								System.err.println(poop.getMessage());
							}
						}while(true);
					}else if(input.equalsIgnoreCase("changeName")){
						String inp = JOptionPane.showInputDialog(null, "What would you like to change your name to?");
						player.setName(inp);
					}else{
						JOptionPane.showMessageDialog(null, "'" + input + "' is not a cheat");
					}
				}
			} else {
				cure.doClick();
			}
		} else if (e.getKeyCode() == (e.VK_I)) {
			invent.doClick();
		} else if (e.getKeyCode() == (e.VK_M)) {
			moneh.doClick();
		} else if (e.getKeyCode() == (e.VK_F)) {
			foodz.doClick();
		} else if (e.getKeyCode() == (e.VK_T)) {
			fight.doClick();
		} else if (e.getKeyCode() == (e.VK_U)) {
			ruleBook.doClick();
		} else if (e.getKeyCode() == (e.VK_S)) {
			save.doClick();
		} else if (e.getKeyCode() == (e.VK_Q)) {
			quit.doClick();
		} else if (e.getKeyCode() == (e.VK_UP)) {
			if (konamiCode == 0) {
				konamiCode = 1;
			} else if (konamiCode == 1) {
				konamiCode = 2;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_DOWN)) {
			if (konamiCode == 2) {
				konamiCode = 3;
			} else if (konamiCode == 3) {
				konamiCode = 4;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_LEFT)) {
			if (konamiCode == 4) {
				konamiCode = 5;
			} else if (konamiCode == 6) {
				konamiCode = 7;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_RIGHT)) {
			if (konamiCode == 5) {
				konamiCode = 6;
			} else if (konamiCode == 7) {
				konamiCode = 8;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_B)) {
			if (konamiCode == 8) {
				konamiCode = 9;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_A)) {
			if (konamiCode == 9) {
				JOptionPane.showMessageDialog(null, "All players gain 1,000 heal counters, 10,000 pounds of food, and $100,000");
				ArrayList<Player> rr = bp.getPlayers();
				for (int bb = 0; bb < rr.size(); bb++) {
					rr.get(bb).addHealCounter(1000);
					rr.get(bb).addFood(10000);
					rr.get(bb).addMoney(100000);
				}
				konamiCode = 0;
			} else {
				konamiCode = 0;
			}
		} else if (e.getKeyCode() == (e.VK_G)) {
			sound.doClick();
		} else if (e.getKeyCode() == (e.VK_CONTROL)) {
			cntrl = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == (e.VK_CONTROL)) {
			cntrl = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
