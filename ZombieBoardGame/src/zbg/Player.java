package zbg;

import java.awt.Color;
import java.util.ArrayList;

public class Player extends BoardComponent{
	
	private String player;
	private int spaceNumber;
	private int infectCounters;
	private int healCounters;
	private int zombify;
	private int countdown;
	private int gameOver;
	private ArrayList<Item> inventory;
	private int money;
	private int poundsOfFood;
	private Color c;

	public Player(int x, int y, int width, int height, Color c, String s, int spaceSize) {
		super(x, y, width, height, c, spaceSize);
		player = s;
		spaceNumber = 1;
		infectCounters = 0;
		healCounters = 0;
		zombify = 0;
		countdown = 10;
		gameOver = 0;
		inventory = new ArrayList<Item>();
		money = 100;
		poundsOfFood = 100;
		this.c = c;
	}
	
	public String getText(){
		return player;
	}
	
	public void changeSpaceNumber(int x){
		spaceNumber = x;
	}
	
	public void move(int numberOfSpaces, BoardPanel bp){
		super.move(numberOfSpaces, spaceNumber, bp);
	}
	
	public void addInfectCounter(int x){
		infectCounters += x;
	}
	
	public void addHealCounter(int x){
		healCounters += x;
	}
	
	public int getInfectCounters(){
		return infectCounters;
	}
	
	public int getHealCounters(){
		return healCounters;
	}
	
	public int getSpaceNumber(){
		return spaceNumber;
	}

	public void zombify(){
		zombify = 1;
	}
	
	public void countDown(){
		if(zombify == 1){
			countdown = countdown - 1;
		}else{
			countdown = 10;
		}
		if(countdown == 0){
			gameOver = 1;
		}
	}
	
	public void cure(){
		zombify = 0;
		countdown = 10;
	}
	
	public int getCountDown(){
		return countdown;
	}
	
	public int getZombify(){
		return zombify;
	}
	
	public int getGameOver(){
		return gameOver;
	}
	
	public String toString(){
		return player;
	}
	
	public void addWeapon(String name, int size, int durability){
		inventory.add(new Weapon(name, size, durability));
	}
	
	public void addWeapon(Weapon w){
		inventory.add(w);
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public void addSpecialItem(String name, int uses){
		inventory.add(new SpecialItem(name, uses));
	}
	public void addSpecialItem(SpecialItem si){
		inventory.add(si);
	}
	
	public int getMoney(){
		return money;
	}
	
	public void addMoney(int x){
		money = money + x;
		if(money <= 0){
			money = 0;
		}
	}
	
	public int getFood(){
		return poundsOfFood;
	}
	
	public void addFood(int x){
		poundsOfFood = poundsOfFood + x;
		if(poundsOfFood <= 0){
			this.addInfectCounter(1);
		}
	}
	
	public int getColorNumber(){
		if(c.equals(new Color(0, 0, 50, 255))){
			return 0;
		}else if(c.equals(new Color(0, 0, 255, 255))){
			return 1;
		}else if(c.equals(new Color(0, 0, 150, 255))){
			return 2;
		}else{
			return 3;
		}
	}
	
	public void setInfectCounters(int x){
		infectCounters = x;
	}
	
	public void setHealCounters(int x){
		healCounters = x;
	}
	
	public void setCountdown(int x){
		countdown = x;
	}
	
	public void setZombify(int x){
		zombify = x;
	}
	
	public void setMoney(int x){
		money = x;
	}
	
	public void setFood(int x){
		poundsOfFood = x;
	}
	
	public void setInv(ArrayList<Item> a){
		inventory = a;
	}
	
	public void setName(String s){
		player = s;
	}
}
