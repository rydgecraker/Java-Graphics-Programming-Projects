package quest;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import quest.Ground.GroundType;
import quest.Building.Dir;

public class Game implements Runnable, KeyListener { // Where the game is put
														// together, and run
														// //Call this class to
														// start a new game

	private JFrame frame;
	private Clip clipbg;
	private Player p;
	private IntroAndStartScreen iss;
	private JPanel mainGame;
	private PlayerInformation pi;
	private POVPanel pov;
	private boolean playerMoveUp;
	private boolean playerMoveDown;
	private boolean playerMoveLeft;
	private boolean playerMoveRight;
	private QuadTree povQuad;
	private int fullMapX;
	private int fullMapY;
	private IntroMusic im;
	private PauseMenuPanel pmp;

	private GridBagConstraints top;
	private GridBagConstraints bottom;

	public boolean paused;

	public ArrayList<Spell> spells;

	private GameObject interactionSpace;
	
	private boolean reset;

	public Game() {
		reset = false;
		paused = false;
		playIntro();
		spells = new ArrayList<Spell>();
		interactionSpace = null;

		fullMapX = 15000;
		fullMapY = 10500;
		
		frame = new JFrame();
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null); // <-------------- Centers
											// it!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		iss = new IntroAndStartScreen(frame);
		p = waitForStuff();
		System.out.println(p.displayStats());
		frame.dispose();
		frame = new JFrame();
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null); // <-------------- Centers
											// it!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setVisible(false);
		mainGame = new JPanel();
		mainGame.setBackground(Color.BLACK);
		mainGame.setLayout(new GridBagLayout());

		bottom = new GridBagConstraints();
		bottom.fill = GridBagConstraints.BOTH;
		bottom.gridwidth = 1;
		bottom.gridx = 0;
		bottom.gridy = 1;
		bottom.weightx = 1;
		bottom.weighty = .90;
		bottom.anchor = GridBagConstraints.SOUTH;

		top = new GridBagConstraints();
		top.fill = GridBagConstraints.BOTH;
		top.gridwidth = 1;
		top.gridx = 0;
		top.gridy = 0;
		top.weightx = 1;
		top.weighty = .10;
		top.anchor = GridBagConstraints.NORTH;

		pi = new PlayerInformation(p);
		povQuad = createPOVquadTree();
		pov = new POVPanel(p, povQuad);
		pov.addKeyListener(this);

		mainGame.add(pi, top);
		mainGame.add(pov, bottom);
		frame.setContentPane(mainGame);
		frame.repaint();
		pov.setFocusable(true);

		Thread th = new Thread(this);
		th.start();
		frame.setVisible(true);
	}

	public synchronized Player waitForStuff() {
		do {

		} while (iss.getPlayer() == null);
		return iss.getPlayer();
	}

	public void run() {
		int counter = 0;
		int animateCounter = 0;
		while (!reset) {
			if (paused == false) {
				pi.repaint();
				pov.repaint();
				pov.requestFocus();
				povQuad.checkCol(p);
				interactionSpace = checkForInteractionSpace();
				pov.addSpells(spells);
				if (animateCounter == 1000) {
					povQuad.animate(p);
					animateCounter = 0;
				}
				if (counter == 25) {
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).move();
						if (spells.get(i).getRelArea().intersects(p.getAbsoluteArea())) {
							p.spellDamage(spells.get(i));
						}
					}
					counter = 0;
				}
				animateCounter++;
				counter++;
				checkIfSpellsOutOfRange();
				p.checkForLevelUp();
			} else {
				pi.repaint();
				pmp.repaint();
				pmp.requestFocus();
			}

		}
		frame.dispose();
		new Main();

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_W) { // Move UP
			if (!paused) {
				playerMoveUp = true;
			}
		}
		if (e.getKeyCode() == e.VK_A) { // Move Down
			if (!paused) {
				playerMoveLeft = true;
			}
		}
		if (e.getKeyCode() == e.VK_S) { // Move Left
			if (!paused) {
				playerMoveDown = true;
			}
		}
		if (e.getKeyCode() == e.VK_D) { // Move Right
			if (!paused) {
				playerMoveRight = true;
			}
		}
		if (e.getKeyCode() == e.VK_SPACE && interactionSpace != null) { // Action
																		// Button\
			if (!paused) {
				if (interactionSpace instanceof Building) {
					if (!p.spells.contains(new FireballSpell(0, 0, 0, null))) {
						JOptionPane.showMessageDialog(null, "You got fireball!");
						p.addSpell(new FireballSpell(0, 0, 0, null));
					} else if (!p.spells.contains(new IceballSpell(0, 0, 0, null))) {
						JOptionPane.showMessageDialog(null, "You got iceball!");
						p.addSpell(new IceballSpell(0, 0, 0, null));
					}
				}
			}

		}
		if (e.getKeyCode() == e.VK_F5) { // Reset
		 reset();
		}

		if (e.getKeyCode() == e.VK_J) { // Use currently selected Item
			if (!paused) {

			}

		}
		if (e.getKeyCode() == e.VK_K) { // Use currently selected Spell
			if (!paused) {
				if (p.canCastSpell()) {
					spells.add((p.castSpell()));
				}
			}
		}
		if (e.getKeyCode() == e.VK_ESCAPE) { // Inventory and Pause Menu
			if (paused == false) {
				pmp = new PauseMenuPanel(p, povQuad, spells);
				pmp.addKeyListener(this);
				pmp.setFocusable(true);
				mainGame.remove(pov);
				mainGame.add(pmp, bottom);
				frame.revalidate();
				paused = true;
			} else {
				mainGame.remove(pmp);
				mainGame.add(pov, bottom);
				frame.revalidate();
				paused = false;
			}

		}

		if (!paused) {
			if (playerMoveRight == true && playerMoveUp == true) {
				if (p.getRelX() < (fullMapX - p.getWidth()) && p.getRelY() > 0 && p.canMoveUp && p.canMoveRight) {
					p.moveDUR(5);
					povQuad.moveRelativeXandY(-5, 5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDDL(5);
					}
				} else if (p.getRelX() < (fullMapX - p.getWidth()) && p.canMoveRight) {
					p.moveRight(5);
					povQuad.moveRelativeX(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelLeft(5);
					}
				} else if (p.getRelY() > 0 && p.canMoveUp) {
					p.moveUp(5);
					povQuad.moveRelativeY(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDown(5);
					}
				}

			} else if (playerMoveRight == true && playerMoveDown == true) {
				if (p.getRelX() < (fullMapX - p.getWidth()) && p.getRelY() < (fullMapY - p.getHeight()) && p.canMoveDown && p.canMoveRight) {
					p.moveDDR(5);
					povQuad.moveRelativeXandY(-5, -5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDUL(5);
					}
				} else if (p.getRelX() < (fullMapX - p.getWidth()) && p.canMoveRight) {
					p.moveRight(5);
					povQuad.moveRelativeX(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelLeft(5);
					}
				} else if (p.getRelY() < (fullMapY - p.getHeight()) && p.canMoveDown) {
					p.moveDown(5);
					povQuad.moveRelativeY(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelUp(5);
					}
				}
			} else if (playerMoveLeft == true && playerMoveUp == true) {
				if (p.getRelX() > 0 && p.getRelY() > 0 && p.canMoveLeft && p.canMoveUp) {
					p.moveDUL(5);
					povQuad.moveRelativeXandY(5, 5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDDR(5);
					}
				} else if (p.getRelX() > 0 && p.canMoveLeft) {
					p.moveLeft(5);
					povQuad.moveRelativeX(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelRight(5);
					}
				} else if (p.getRelY() > 0 && p.canMoveUp) {
					p.moveUp(5);
					povQuad.moveRelativeY(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDown(5);
					}
				}
			} else if (playerMoveLeft == true && playerMoveDown == true) {
				if (p.getRelX() > 0 && p.getRelY() < (fullMapY - p.getHeight()) && p.canMoveLeft && p.canMoveDown) {
					p.moveDDL(5);
					povQuad.moveRelativeXandY(5, -5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDUR(5);
					}
				} else if (p.getRelX() > 0 && p.canMoveLeft) {
					p.moveLeft(5);
					povQuad.moveRelativeX(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelRight(5);
					}
				} else if (p.getRelY() < (fullMapY - p.getHeight()) && p.canMoveDown) {
					p.moveDown(5);
					povQuad.moveRelativeY(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelUp(5);
					}
				}

			} else if (playerMoveDown == true) {
				if (p.getRelY() < (fullMapY - p.getHeight()) && p.canMoveDown) {
					p.moveDown(5);
					povQuad.moveRelativeY(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelUp(5);
					}
				}
			} else if (playerMoveUp == true) {
				if (p.getRelY() > 0 && p.canMoveUp) {
					p.moveUp(5);
					povQuad.moveRelativeY(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelDown(5);
					}
				}
			} else if (playerMoveLeft == true && p.canMoveLeft) {
				if (p.getRelX() > 0) {
					p.moveLeft(5);
					povQuad.moveRelativeX(5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelRight(5);
					}
				}
			} else if (playerMoveRight == true) {
				if (p.getRelX() < (fullMapX - p.getWidth()) && p.canMoveRight) {
					p.moveRight(5);
					povQuad.moveRelativeX(-5);
					for (int i = 0; i < spells.size(); i++) {
						spells.get(i).moveRelLeft(5);
					}
				}
			}
		}

		frame.repaint();
		frame.requestFocus();

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_W) {
			playerMoveUp = false;
		}
		if (e.getKeyCode() == e.VK_A) {
			playerMoveLeft = false;
		}
		if (e.getKeyCode() == e.VK_S) {
			playerMoveDown = false;
		}
		if (e.getKeyCode() == e.VK_D) {
			playerMoveRight = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public QuadTree createPOVquadTree() {
		QuadTree qt = new QuadTree(fullMapX, fullMapY, 20); // size and max num
																// of objects
		JFrame frame = new JFrame();
		JProgressBar jpb = new JProgressBar(0, fullMapX / 50);
		jpb.setStringPainted(true);
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.add(jpb);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.setVisible(true);
		// Adding ground

		GetImage gi = new GetImage();

		BufferedImage img = gi.image(new File("images/Terrain/Grass.jpg"));
		int tempx = 0;
		int tempy = 0;
		 for(int i = 0; i < fullMapX / 50; i++){
		 for(int j = 0; j < fullMapY / 50; j++){
		 qt.addGameObject(new Ground(tempx, tempy, GroundType.GRASS, img));
		 //Cover the map in grass
		 tempy += 50;
		 }
		 tempx += 50;
		 tempy = 0;
		 jpb.setValue(i);
		 int numberPerPercentage = (fullMapX / 50) / 100;
		 int attempt = i / numberPerPercentage;
		 jpb.setString("Loading Grass: " + Integer.toString(attempt) + "%");
		 jpb.repaint();
		 }
		frame.dispose();
		// ///////////////////////////////////////////////////////////////////////////////////////////////


		// ////////////////////////////////////////////////////////////////////////

		img = gi.image(new File("images/Terrain/GrassPaths/Path(LeftRight).jpg"));

		tempx = 300;
		tempy = 275;

		for (int i = 0; i < 50; i++) {
			qt.addGameObject(new Ground(tempx, tempy, GroundType.PATHLEFTRIGHT, img)); // Path
			tempx += 50;
		}

		qt.addGameObject(new YourHouse(200, 500));

		return qt;
	}

	public GameObject checkForInteractionSpace() {
		for (int i = 0; i < povQuad.allObjects.size(); i++) {
			if (povQuad.allObjects.get(i).getInteractionSpace() != null && povQuad.allObjects.get(i).getInteractionSpace().intersects(p.getRelArea())) {
				return povQuad.allObjects.get(i);
			}
		}
		return null;
	}

	public void playIntro() {
		im = new IntroMusic(clipbg);
	}

	public void checkIfSpellsOutOfRange() {
		for (int i = 0; i < spells.size(); i++) {
			if (!spells.get(i).getRelArea().intersects(p.getViewArea())) {
				// System.out.println(spells.get(i).getArea());
				spells.remove(i);
				// System.out.println(p.getViewArea());
				break;
			}
		}
	}
	
	public void reset(){
		im.clipbg.close();
		reset = true;
	}

}
