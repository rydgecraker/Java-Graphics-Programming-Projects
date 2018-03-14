package com.forkBombSquad.asteroidAttack;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.forkBombSquad.gameUtilities.KeyBind;
import com.forkBombSquad.gameUtilities.KeyBindHandler;
import com.forkBombSquad.gameUtilities.Mathf;


public class GamePanel extends JPanel implements KeyListener {
	
	private static final long	serialVersionUID			= 1L;
	
	
	public KeyBindHandler		controls;
	
	private Game				game;
	
	
	private ArrayList<Asteroid>	asteroids;
	private ArrayList<GameLine>	lines;
	private ArrayList<Bullet>	bullets;
	private Player				player;
	
	
	private float				bulletCooldownTime			= 0.2f;
	private float				bulletCooldownCurrentTime	= 0.0f;
	
	
	
	
	private float				playerAccelerationRate		= 10.0f;
	private float				playerSpinRate				= 360.0f;
	
	private float				playerDeadTimer				= -1f;
	
	
	float						minVelocity					= -160.0f;
	float						maxVelocity					= 160.0f;
	
	float						minNumSides					= 10;
	float						maxNumSides					= 30;
	
	float						minMinRadius				= 20.0f;
	float						maxMinRadius				= 60.0f;
	
	float						minMaxRadius				= 61.0f;
	float						maxMaxRadius				= 100.0f;
	
	
	public GamePanel(Game game) {
		super();
		this.game = game;
		setControls();
		initPanelVariables();
		initGameVariables();
	}
	
	
	private void setControls() {
		controls = new KeyBindHandler();
		controls.addBinding(new KeyBind("UP", KeyEvent.VK_W));
		controls.addBinding(new KeyBind("DOWN", KeyEvent.VK_S));
		controls.addBinding(new KeyBind("LEFT", KeyEvent.VK_A));
		controls.addBinding(new KeyBind("RIGHT", KeyEvent.VK_D));
		controls.addBinding(new KeyBind("ESCAPE", KeyEvent.VK_ESCAPE));
		controls.addBinding(new KeyBind("SHIFT", KeyEvent.VK_SHIFT));
		controls.addBinding(new KeyBind("CTRL", KeyEvent.VK_CONTROL));
		controls.addBinding(new KeyBind("SPACE", KeyEvent.VK_SPACE));
	}
	
	
	private void initPanelVariables() {
		setBackground(Color.BLACK);
	}
	
	
	private void initGameVariables() {
		asteroids = new ArrayList<Asteroid>();
		lines = new ArrayList<GameLine>();
		bullets = new ArrayList<Bullet>();
		float max = Mathf.randRange(5, 10);
		
		
		
		
		for (int i = 0; i < max; i++) {
			asteroids.add(new Asteroid(Mathf.randRange(0, game.screenWidth), Mathf.randRange(0, game.screenHeight), Mathf.randRange(minVelocity, maxVelocity), Mathf.randRange(minVelocity, maxVelocity), (int) Mathf.randRange(minNumSides, maxNumSides), Mathf.randRange(minMinRadius, maxMinRadius), Mathf.randRange(minMaxRadius, maxMaxRadius), 2));
			asteroids.get(i).angularVelocty = Mathf.randRange(-90.0f, 90.0f);
		}
		
		
		player = getNewPlayer();
	}
	
	
	public void update(float dt) {
		updateVariables(dt);
		updateKeyPresses(dt);
		updateMovement(dt);
		updateCollisions(dt);
		updateKills(dt);
		checkPlayerRespawn(dt);
	}
	
	
	private void checkPlayerRespawn(float dt) {
		float beforeSign = Mathf.getSign(playerDeadTimer);
		if (playerDeadTimer >= 0) {
			playerDeadTimer -= dt;
			if (Mathf.getSign(playerDeadTimer) != beforeSign) {
				player = getNewPlayer();
			}
		}
	}
	
	
	private void updateVariables(float dt) {
		bulletCooldownCurrentTime += dt;
	}
	
	
	private void updateKeyPresses(float dt) {
		if (controls.isPressed("ESCAPE")) {
			game.endGame();
		}
		
		if (controls.isPressed("UP")) {
			player.accelerateVelocity(playerAccelerationRate);
		} else {
			player.decelerateVelocity(-playerAccelerationRate / 2.0f);
		}
		
		if (controls.isPressed("SPACE")) {
			if (bulletCooldownCurrentTime >= bulletCooldownTime) {
				bullets.add(player.shootBullet());
				bulletCooldownCurrentTime = 0.0f;
			}
		} else {
			bulletCooldownCurrentTime = bulletCooldownTime;
		}
		
		if (controls.isPressed("LEFT")) {
			player.rotate(playerSpinRate * dt * -1);
		}
		
		if (controls.isPressed("RIGHT")) {
			player.rotate(playerSpinRate * dt);
		}
	}
	
	
	private void updateMovement(float dt) {
		for (Asteroid asteroid : asteroids) {
			asteroid.update(dt);
			
			float moveX = 0.0f;
			float moveY = 0.0f;
			if (asteroid.xLocation < 0) {
				moveX = (Mathf.distance1d(asteroid.xLocation, game.screenWidth));
			} else if (asteroid.xLocation > game.screenWidth) {
				moveX = -1 * (Mathf.distance1d(asteroid.xLocation, 0.0f));
			}
			
			if (asteroid.yLocation < 0) {
				moveY = (Mathf.distance1d(asteroid.yLocation, game.screenHeight));
			} else if (asteroid.yLocation > game.screenHeight) {
				moveY = -1 * (Mathf.distance1d(asteroid.yLocation, 0.0f));
			}
			
			asteroid.move(moveX, moveY);
		}
		
		for (GameLine gl : lines) {
			gl.update(dt);
			
			float moveX = 0.0f;
			float moveY = 0.0f;
			if (gl.xLocation < 0) {
				moveX = (Mathf.distance1d(gl.xLocation, game.screenWidth));
			} else if (gl.xLocation > game.screenWidth) {
				moveX = -1 * (Mathf.distance1d(gl.xLocation, 0.0f));
			}
			
			if (gl.yLocation < 0) {
				moveY = (Mathf.distance1d(gl.yLocation, game.screenHeight));
			} else if (gl.yLocation > game.screenHeight) {
				moveY = -1 * (Mathf.distance1d(gl.yLocation, 0.0f));
			}
			
			gl.move(moveX, moveY);
		}
		
		player.update(dt);
		
		float moveX = 0.0f;
		float moveY = 0.0f;
		if (player.xLocation < 0) {
			moveX = (Mathf.distance1d(player.xLocation, game.screenWidth));
		} else if (player.xLocation > game.screenWidth) {
			moveX = -1 * (Mathf.distance1d(player.xLocation, 0.0f));
		}
		
		if (player.yLocation < 0) {
			moveY = (Mathf.distance1d(player.yLocation, game.screenHeight));
		} else if (player.yLocation > game.screenHeight) {
			moveY = -1 * (Mathf.distance1d(player.yLocation, 0.0f));
		}
		
		player.move(moveX, moveY);
		
		for (Bullet b : bullets) {
			b.update(dt);
			
			moveX = 0.0f;
			moveY = 0.0f;
			if (b.xLocation < 0) {
				moveX = (game.screenWidth - (b.xLocation + 5.0f));
			} else if (b.xLocation > game.screenWidth) {
				moveX = 5.0f - b.xLocation;
			}
			
			if (b.yLocation < 0) {
				moveY = (game.screenHeight - (b.yLocation + 5.0f));
			} else if (b.yLocation > game.screenHeight) {
				moveY = 5.0f - b.yLocation;
			}
			
			b.move(moveX, moveY);
		}
		
	}
	
	
	private void updateCollisions(float dt) {
		for (Asteroid asteroid : asteroids) {
			if (asteroid.alive) {
				for (Asteroid asteroid2 : asteroids) {
					if (asteroid2.alive) {
						if (!asteroid.equals(asteroid2)) {
							if (Mathf.polygonIntersectsPolygon(asteroid.xLocations, asteroid.yLocations, asteroid2.xLocations, asteroid2.yLocations)) {
								// asteroid.collide();
								// asteroid2.collide();
							}
						}
					}
				}
				
				
				for (Bullet bullet : bullets) {
					if (Mathf.polygonContainsPoint(bullet.xLocation, bullet.yLocation, asteroid.xLocations, asteroid.yLocations)) {
						asteroid.collide();
						bullet.collide();
					}
				}
				
				if (player.alive) {
					if (Mathf.polygonIntersectsPolygon(asteroid.xLocations, asteroid.yLocations, player.xLocations, player.yLocations)) {
						player.collide();
					}
				}
			}
		}
	}
	
	
	@Override
	public final void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
		paintStars(g);
		paintGameComponents(g);
	}
	
	
	private void paintStars(Graphics2D g) {
		
	}
	
	
	private Player getNewPlayer() {
		return new Player(game.screenWidth / 2.0f, game.screenHeight / 2.0f);
	}
	
	
	private void updateKills(float dt) {
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			if (!a.alive) {
				if (a.sizeMod <= 0) {
					for (int j = 0; j < a.xLocations.length; j++) {
						GameLine gl = null;
						if (j == a.xLocations.length - 1) {
							gl = new GameLine(a.xLocations[j], a.yLocations[j], a.xLocations[0], a.yLocations[0], 2.5f);
						} else {
							gl = new GameLine(a.xLocations[j], a.yLocations[j], a.xLocations[j + 1], a.yLocations[j + 1], 2.5f);
						}
						gl.angularVelocty = Mathf.randRange(360.0f, 360.0f);
						gl.xVelocity = Mathf.randRange(-120.0f, 120.0f);
						gl.yVelocty = Mathf.randRange(-120.0f, 120.0f);
						lines.add(gl);
					}
					
				} else {
					Asteroid as = new Asteroid(a.xLocation, a.yLocation, Mathf.randRange(minVelocity, maxVelocity), Mathf.randRange(minVelocity, maxVelocity), (int) Mathf.randRange(minNumSides, maxNumSides), Mathf.randRange(minMinRadius, maxMinRadius), Mathf.randRange(minMaxRadius, maxMaxRadius), a.sizeMod - 1);
					as.angularVelocty = Mathf.randRange(-90.0f, 90.0f);
					asteroids.add(as);
					as = new Asteroid(a.xLocation, a.yLocation, Mathf.randRange(minVelocity, maxVelocity), Mathf.randRange(minVelocity, maxVelocity), (int) Mathf.randRange(minNumSides, maxNumSides), Mathf.randRange(minMinRadius, maxMinRadius), Mathf.randRange(minMaxRadius, maxMaxRadius), a.sizeMod - 1);
					as.angularVelocty = Mathf.randRange(-90.0f, 90.0f);
					asteroids.add(as);
				}
				asteroids.remove(i);
				i--;
				break;
			}
		}
		
		for (int i = 0; i < lines.size(); i++) {
			GameLine gl = lines.get(i);
			if (!gl.alive) {
				lines.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (!b.alive) {
				bullets.remove(i);
				i--;
			}
		}
		
		if (!player.alive && playerDeadTimer <= 0.0f) {
			for (int j = 0; j < player.xLocations.length; j++) {
				GameLine gl = null;
				if (j == player.xLocations.length - 1) {
					gl = new GameLine(player.xLocations[j], player.yLocations[j], player.xLocations[0], player.yLocations[0], 2.5f);
				} else {
					gl = new GameLine(player.xLocations[j], player.yLocations[j], player.xLocations[j + 1], player.yLocations[j + 1], 2.5f);
				}
				gl.angularVelocty = Mathf.randRange(360.0f, 360.0f);
				gl.xVelocity = Mathf.randRange(-120.0f, 120.0f);
				gl.yVelocty = Mathf.randRange(-120.0f, 120.0f);
				lines.add(gl);
			}
			playerDeadTimer = 1.5f;
		}
		
	}
	
	
	private void paintGameComponents(Graphics2D g) {
		g.setColor(Color.WHITE);
		for (Asteroid asteroid : asteroids) {
			asteroid.draw(g);
		}
		for (GameLine gl : lines) {
			gl.draw(g);
		}
		for (Bullet b : bullets) {
			b.draw(g);
		}
		
		player.draw(g);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		controls.setPressed(e.getKeyCode(), true);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		controls.setPressed(e.getKeyCode(), false);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
}
