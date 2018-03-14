package quest;

import java.io.File;

import quest.Building.Dir;

public class YourHouse extends Building {

	public YourHouse(int x, int y) {
		super(x, y, new Direction(Dir.DOWN), new File("images/Houses/HomeHouse.png"));
	}

}
