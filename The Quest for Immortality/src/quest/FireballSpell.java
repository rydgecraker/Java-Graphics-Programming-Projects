package quest;

import java.io.File;

import quest.Player.MoveDirection;

public class FireballSpell extends DamageSpell{ //Does damage based on player magic level.  Speed of 10.  Accuracy of 80%.  Mp cost of 5

	public Element element;
	
	public FireballSpell(int x, int y, int magicLevel, MoveDirection direction) {
		super("Fireball", x, y, DamageType.ELEMENTAL, 10, magicLevel, 80, direction, 5, new File("images/Spells/fireball.png"));
		element = Element.FIRE;
	}

}
