package quest;

import java.io.File;

import quest.Player.MoveDirection;

public class IceballSpell extends DamageSpell{ //Does damage based on player magic level plus 5, speed of 5, accuracy 80, mpcost 7
	
	public Element element;

	public IceballSpell(int x, int y, int magicLevel, MoveDirection direction) {
		super("Iceball", x, y, DamageType.ELEMENTAL, 5, magicLevel + 5, 80, direction, 7, new File("images/Spells/iceball.png"));
		element = Element.ICE;
	}

}
