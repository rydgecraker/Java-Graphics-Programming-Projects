package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Computer extends GamePiece{
	
	private String name;
	private Color color;
	private int playerNumber;
	private int spaceNumber;
	private boolean jail;
	private int jailCountdown;

	public Computer(int number, int x, int y, int width, int height, String name,  ArrayList<Color> colorsLeft) {
		super(number, x, y, width, height);
		jail = false;
		jailCountdown = 0;
		spaceNumber = 0;
		this.name = name;
		playerNumber = number;
		
		Random r = new Random();
		int randomNumber = r.nextInt(colorsLeft.size());
		color = colorsLeft.get(randomNumber);
		colorsLeft.remove(randomNumber);
		
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void changeSpaceNumber(int x) {
		spaceNumber = x;
		
	}

	public int getSpaceNumber() {
		return spaceNumber;
	}

	public boolean checkIfInJail() {
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
	
	public boolean decideIfGoodDeal(Property p, ArrayList<Space> board){
		Color c = p.getColor();
		ArrayList<Place> properties = this.getProperties();
		int numOtherProp = 0;
		for(int i = 0; i < properties.size(); i++){
			if(properties.get(i) instanceof Property){
				Property prop = (Property) properties.get(i);
				if(prop.getColor().equals(c)){
					numOtherProp++;
				}
			}else{
				
			}
		}
		if(p.getSpaceNumber() == 1 || p.getSpaceNumber() == 3 || p.getSpaceNumber() == 37 || p.getSpaceNumber() == 39){
			if(numOtherProp == 1){
				if(p.getPrice() < (int) (this.getMoney() * .90)){
					return true;
				}else{
					return false;
				}
			}else{
				if(p.getPrice() < (int) (this.getMoney() * .75)){
					return true;
				}else{
					return false;
				}
			}
		}else{
			if(numOtherProp == 2){
				if(p.getPrice() < (int) (this.getMoney() * .90)){
					return true;
				}else{
					return false;
				}
			}else if(numOtherProp == 1){
				if(p.getPrice() < (int) (this.getMoney() * .83)){
					return true;
				}else{
					return false;
				}
			}else{
				if(p.getPrice() < (int) (this.getMoney() * .75)){
					return true;
				}else{
					return false;
				}
			}
		}
	}
}
