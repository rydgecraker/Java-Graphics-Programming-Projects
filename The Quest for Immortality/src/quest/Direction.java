package quest;

import quest.Building.Dir;

public class Direction {

	public int width;
	public int height;
	public Dir d;
	
	
	public Direction(Dir d){
		this.d = d;
		if(d.equals(Dir.DOWN)){
			width = 200;
			height = 100;
		}else if(d.equals(Dir.UP)){
			width = 200;
			height = 100;
		}else if(d.equals(Dir.LEFT)){
			width = 100;
			height = 200;
		}else if(d.equals(Dir.RIGHT)){
			width = 100;
			height = 200;
		}
	}

}
