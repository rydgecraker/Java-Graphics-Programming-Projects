package zbg;

import java.awt.Color;

public class Space extends BoardComponent{

	private String text;
	private int spaceNumber;
	private Color c;
	
	public Space(int x, int y, int width, int height, Color c, String s, int spaceNumber, int spaceSize) {
		super(x, y, width, height, c, spaceSize);
		text = s;
		this.spaceNumber = spaceNumber;
		this.c = c;
		
	}
	
	public String getText(){
		return text;
	}
	
	public int getSpaceNumber(){
		return spaceNumber;
	}
	
	public int getColorNumber(){
		if(c.equals(Color.BLACK)){
			return 0;
		}else if(c.equals(Color.WHITE)){
			return 1;
		}else if(c.equals(Color.CYAN)){
			return 2;
		}else if(c.equals(Color.GRAY)){
			return 3;
		}else if(c.equals(Color.DARK_GRAY)){
			return 4;
		}else if(c.equals(Color.BLUE)){
			return 5;
		}else if(c.equals(Color.RED)){
			return 6;
		}else if(c.equals(Color.ORANGE)){
			return 7;
		}else if(c.equals(Color.YELLOW)){
			return 8;
		}else if(c.equals(Color.GREEN)){
			return 9;
		}else if(c.equals(Color.LIGHT_GRAY)){
			return 10;
		}else if(c.equals(Color.MAGENTA)){
			return 11;
		}else{
			return 12;
		}
	}

}
