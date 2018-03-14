package monopoly;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Player extends GamePiece{
	
	private String name;
	private Color color;
	private int playerNumber;
	private int spaceNumber;
	private boolean jail;
	private int jailCountdown;
	

	public Player(int number, int x, int y, int width, int height, String name, ArrayList<Color> colorsLeft){
		super(number, x, y, width, height);
		this.name = name;
		jail = false;
		jailCountdown = 0;
		spaceNumber = 0;
		String[] colors = new String[colorsLeft.size()];
		playerNumber = number;
		for(int i = 0; i < colorsLeft.size(); i++){
			if(colorsLeft.get(i) == Color.RED){
				colors[i] = "Red";
			}else if(colorsLeft.get(i) == Color.ORANGE){
				colors[i] = "Orange";
			}else if(colorsLeft.get(i) == Color.YELLOW){
				colors[i] = "Yellow";
			}else if(colorsLeft.get(i) == Color.GREEN){
				colors[i] = "Green";
			}else if(colorsLeft.get(i) == Color.BLUE){
				colors[i] = "Blue";
			}else if(colorsLeft.get(i) == Color.CYAN){
				colors[i] = "Cyan";
			}else if(colorsLeft.get(i) == Color.MAGENTA){
				colors[i] = "Magenta";
			}else if(colorsLeft.get(i) == Color.PINK){
				colors[i] = "Pink";
			}	
		}
		
		String c = (String) JOptionPane.showInputDialog(null, "What color would you like to be?", "Player Color", JOptionPane.QUESTION_MESSAGE, null, colors, colors[0]);
		if(c.equals("Red")){
			colorsLeft.remove(Color.RED);
			color  = Color.RED;
		}else if(c.equals("Orange")){
			colorsLeft.remove(Color.ORANGE);
			color  = Color.ORANGE;
		}else if(c.equals("Yellow")){
			colorsLeft.remove(Color.YELLOW);
			color  = Color.YELLOW;
		}else if(c.equals("Green")){
			colorsLeft.remove(Color.GREEN);
			color  = Color.GREEN;
		}else if(c.equals("Blue")){
			colorsLeft.remove(Color.BLUE);
			color  = Color.BLUE;
		}else if(c.equals("Cyan")){
			colorsLeft.remove(Color.CYAN);
			color  = Color.CYAN;
		}else if(c.equals("Magenta")){
			colorsLeft.remove(Color.MAGENTA);
			color  = Color.MAGENTA;
		}else if(c.equals("Pink")){
			colorsLeft.remove(Color.PINK);
			color  = Color.PINK;
		}
		
	}

	public Color getColor() {
		return color;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPlayerNumber(){
		return playerNumber;
	}

	public void changeSpaceNumber(int x) {
		spaceNumber = x;
		
	}

	public int getSpaceNumber() {
		return spaceNumber;
	}

	public boolean checkIfInJail() {
		if(jail = true){
			jailCountdown--;
		}
		return jail;
		
	}

	public void changeJail() {
		if(jail == true){
			jail = false;
			jailCountdown = 0;
		}else{
			jail = true;
			jailCountdown = 3;
		}
	}

	public int getJailCountdown() {
		return jailCountdown;
	}
	
	
}
