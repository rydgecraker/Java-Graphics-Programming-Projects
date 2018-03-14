package onTheHill;

import java.awt.Graphics;

public class Player {
	
	public CharacterHex character;
	public int playerNumber;
	
	public Player(int playerNumber, CharacterHex character){
		this.playerNumber = playerNumber;
		this.character = character;
	}
	
	public int getMight(){
		return character.getMight();
	}
	
	public int getSpeed(){
		return character.getSpeed();
	}
	
	public int getSanity(){
		return character.getSanity();
	}
	
	public int getKnowledge(){
		return character.getKnowledge();
	}
	
	public int getBaseMight(){
		return character.getBaseMight();
	}
	
	public int getBaseSpeed(){
		return character.getBaseSpeed();
	}
	
	public int getBaseSanity(){
		return character.getBaseSanity();
	}
	
	public int getBaseKnowledge(){
		return character.getBaseKnowledge();
	}
	
	public boolean isDead(){
		return character.dead;
	}
	
	public void changeMight(int change, boolean hauntRevealed){
		character.changeMight(change, hauntRevealed);
	}
	
	public void changeSpeed(int change, boolean hauntRevealed){
		character.changeSpeed(change, hauntRevealed);
	}
	
	public void changeSanity(int change, boolean hauntRevealed){
		character.changeSanity(change, hauntRevealed);
	}
	
	public void changeKnowledge(int change, boolean hauntRevealed){
		character.changeKnowledge(change, hauntRevealed);
	}
	public void resizeCharacterHex(int x, int y, int width, int height){
		//Warning Resets Stats!!!!!!!!!!!
		character = new CharacterHex(x, y, width, height, character.charColor, character.charNum);
	}
	
	public void draw(Graphics g){
		character.draw(g);
		//More Stuff?
	}

}
