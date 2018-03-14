package zombieGame2;

import java.awt.Color;
import java.util.ArrayList;

public class Player extends GameComponent {

	public int changeFrame = 0;
	public int frameNumber = 0;
	public int frameSide = 0;

	public Player(int x, int y, int w, int h) {
		super(x, y, w, h, Color.ORANGE);
	}

	public void updatePosition(int x, int y) {
		this.update(x, y);
	}

	public void collideCheck(ArrayList<GameComponent> gc) {
		for (int i = 0; i < gc.size(); i++) {
			if (gc.get(i).storeAsRectangle().getX() == this.storeAsRectangle().getX() && gc.get(i).storeAsRectangle().getY() == this.storeAsRectangle().getY()) {

			} else {
				Boolean intersection;
				if (gc.isEmpty()) {

				} else {
					intersection = this.storeAsRectangle().intersectsLine(gc.get(i).storeAsRectangle().getX(), gc.get(i).storeAsRectangle().getY(),
							(gc.get(i).storeAsRectangle().getX() + gc.get(i).storeAsRectangle().getWidth()), gc.get(i).storeAsRectangle().getY());
					if (intersection == true) {
						if (gc.get(i) instanceof Wall) {
							this.youCollided(0);// Top
						} else if (gc.get(i) instanceof Building) {
							this.youCollided(0);// Top
						} else if (gc.get(i) instanceof Door) {
							this.youCollided(0); // Top
						} else if (gc.get(i) instanceof NextFrame) {
							this.youCollided(0);// Top
							changeFrame = 1;
							NextFrame nf = (NextFrame) gc.get(i);
							frameNumber = nf.getFrameNumber();
							frameSide = nf.getFrameSide();
						} else if (gc.get(i) instanceof NPC) {
							this.youCollided(0);// Top
							NPC n = (NPC) gc.get(i);
							n.talk();
							this.updatePosition(0, -5);
						} else if (gc.get(i) instanceof Zombie) {
							this.youCollided(0); // Top
						}
					}
				}
				if (gc.isEmpty()) {

				} else {
					intersection = this.storeAsRectangle().intersectsLine((gc.get(i).storeAsRectangle().getX()), (gc.get(i).storeAsRectangle().getY() + gc.get(i).storeAsRectangle().getHeight()),
							((gc.get(i).storeAsRectangle().getX() + gc.get(i).storeAsRectangle().getWidth())), ((gc.get(i).storeAsRectangle().getY()) + gc.get(i).storeAsRectangle().getHeight()));
					if (intersection == true) {
						if (gc.get(i) instanceof Wall) {
							this.youCollided(1); // Bottom
						} else if (gc.get(i) instanceof Building) {
							this.youCollided(1); // Bottom
						} else if (gc.get(i) instanceof Door) {
							this.youCollided(1); // Bottom
						} else if (gc.get(i) instanceof NextFrame) {
							this.youCollided(1); // Bottom
							changeFrame = 1;
							NextFrame nf = (NextFrame) gc.get(i);
							frameNumber = nf.getFrameNumber();
							frameSide = nf.getFrameSide();
						} else if (gc.get(i) instanceof NPC) {
							this.youCollided(1); // Bottom
							NPC n = (NPC) gc.get(i);
							n.talk();
							this.updatePosition(0, 5);
						} else if (gc.get(i) instanceof Zombie) {
							this.youCollided(1); // Bottom
						}
					}
				}
				if (gc.isEmpty()) {

				} else {
					intersection = this.storeAsRectangle().intersectsLine(gc.get(i).storeAsRectangle().getX(), gc.get(i).storeAsRectangle().getY(), gc.get(i).storeAsRectangle().getX(),
							gc.get(i).storeAsRectangle().getY() + gc.get(i).storeAsRectangle().getHeight());
					if (intersection == true) {
						if (gc.get(i) instanceof Wall) {
							this.youCollided(2); // Left
						} else if (gc.get(i) instanceof Building) {
							this.youCollided(2); // Left
						} else if (gc.get(i) instanceof Door) {
							this.youCollided(2); // Left
						} else if (gc.get(i) instanceof NextFrame) {
							this.youCollided(2); // Left
							changeFrame = 1;
							NextFrame nf = (NextFrame) gc.get(i);
							frameNumber = nf.getFrameNumber();
							frameSide = nf.getFrameSide();
						} else if (gc.get(i) instanceof NPC) {
							this.youCollided(2); // Left
							NPC n = (NPC) gc.get(i);
							n.talk();
							this.updatePosition(-5, 0);
						} else if (gc.get(i) instanceof Zombie) {
							this.youCollided(2); // Left
						}
					}
				}
				if (gc.isEmpty()) {

				} else {
					intersection = this.storeAsRectangle().intersectsLine(gc.get(i).storeAsRectangle().getX() + gc.get(i).storeAsRectangle().getWidth(), gc.get(i).storeAsRectangle().getY(),
							gc.get(i).storeAsRectangle().getX() + gc.get(i).storeAsRectangle().getWidth(), gc.get(i).storeAsRectangle().getY() + gc.get(i).storeAsRectangle().getHeight());
					if (intersection == true) {
						if (gc.get(i) instanceof Wall) {
							this.youCollided(3); // Right
						} else if (gc.get(i) instanceof Building) {
							this.youCollided(3); // Right
						} else if (gc.get(i) instanceof Door) {
							this.youCollided(3); // Right
						} else if (gc.get(i) instanceof NextFrame) {
							this.youCollided(3); // Right
							changeFrame = 1;
							NextFrame nf = (NextFrame) gc.get(i);
							frameNumber = nf.getFrameNumber();
							frameSide = nf.getFrameSide();
						} else if (gc.get(i) instanceof NPC) {
							this.youCollided(3); // Right
							NPC n = (NPC) gc.get(i);
							n.talk();
							this.updatePosition(5, 0);
						} else if (gc.get(i) instanceof Zombie) {
							this.youCollided(3); // Right
						}
					}
				}
			}
		}
	}

	public void youCollided(int x) {
		if (x == 0) { // Hit Top
			this.updatePosition(0, -1);
		}
		if (x == 1) { // Hit bottom
			this.updatePosition(0, 1);
		}
		if (x == 2) {
			this.updatePosition(-1, 0);
		}
		if (x == 3) {
			this.updatePosition(1, 0);
		}
	}
}
