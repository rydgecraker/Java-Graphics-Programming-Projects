package quest;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import quest.Player.MoveDirection;

public abstract class Enemy extends Character {

	public enum EnemyType{MOVING, NONMOVING}
	
	public EnemyType et;
	public int health;
	public int maxHealth;
	public int speed;
	public int maxMp;
	public int defense;
	public int strength;
	public int luck;
	public int mp;
	public MoveDirection lastMove;
	public ArrayList<Spell> spells;
	
	public boolean dead;
	
	public Enemy(int x, int y, int width, int height, EnemyType et, int health, int maxHealth, int mp, int maxMp, int defense, int speed, int strength, int luck, File filepath) {
		super(x, y, width, height, filepath);
		spells = new ArrayList<Spell>();
		this.et = et;
		this.health = health;
		this.maxHealth = maxHealth;
		this.mp = mp;
		this.maxMp = maxMp;
		this.defense = defense;
		this.speed = speed;
		this.strength = strength;
		this.luck = luck;
		dead = false;
		lastMove = MoveDirection.UP;
		
	}
	
	public void damage(int x){
		x -= defense;
		if(x < 0){
			x = 0;
		}
		Random r = new Random();
		int b = r.nextInt(100);
		b++;
		if(b < luck){
			x = x / 2;
		}
		health = health - x;
		if(health <= 0){
			dead = true;
		}
	}
	
	public void heal(int x){
		if(health + x > maxHealth){
			health = maxHealth;
		}else{
			health += x;
		}
	}
	
	public void changeMp(int x){
		if(mp + x < 0){
			mp = 0;
		}else if(mp + x > maxMp){
			mp = maxMp;
		}else{
			mp += x;
		}
	}
	
	public void moveLeft() {
		moveLeft(speed);
	}

	public void moveRight() {
		moveRight(speed);
		
	}

	public void moveUp() {
		moveUp(speed);
		
	}

	public void moveDown() {
		moveDown(speed);
		
	}

	public void moveDUR() {
		moveDUR(speed);
		
	}

	public void moveDUL() {
		moveDUL(speed);
		
	}

	public void moveDDR() {
		moveDDR(speed);
		
	}

	public void moveDDL() {
		moveDDL(speed);
	}

	public void moveLeft(int x) {
		lastMove = MoveDirection.LEFT;
		this.x -= x;
		this.relX -= x;
		
	}

	public void moveRight(int x) {
		lastMove = MoveDirection.RIGHT;
		this.x += x;
		this.relX += x;
		
	}

	public void moveUp(int x) {
		lastMove = MoveDirection.UP;
		this.y -= x;
		this.relY -= x;
		
	}

	public void moveDown(int x) {
		lastMove = MoveDirection.DOWN;
		this.y += x;
		this.relY += x;		
	}

	public void moveDUR(int x) {
		lastMove = MoveDirection.DUR;
		this.x += x;
		this.relX += x;
		this.y -= x;
		this.relY -= x;
	}

	public void moveDUL(int x) {
		lastMove = MoveDirection.DUL;
		this.x -= x;
		this.relX -= x;
		this.y -= x;
		this.relY -= x;
		
	}

	public void moveDDR(int x) {
		lastMove = MoveDirection.DDR;
		this.x += x;
		this.relX += x;
		this.y += x;
		this.relY += x;	
		
	}

	public void moveDDL(int x) {
		lastMove = MoveDirection.DDL;
		this.x -= x;
		this.relX -= x;
		this.y += x;
		this.relY += x;	
		
	}
	
	public void moveRelLeft(int x) {
		this.relX -= x;
		
	}

	public void moveRelRight(int x) {
		this.relX += x;
		
	}

	public void moveRelUp(int x) {
		this.relY -= x;
		
	}

	public void moveRelDown(int x) {
		this.relY += x;		
	}

	public void moveRelDUR(int x) {
		this.relX += x;
		this.relY -= x;
	}

	public void moveRelDUL(int x) {
		this.relX -= x;
		this.relY -= x;
		
	}

	public void moveRelDDR(int x) {
		this.relX += x;
		this.relY += x;	
		
	}

	public void moveRelDDL(int x) {
		this.x -= x;
		this.relX -= x;
		this.y += x;
		this.relY += x;	
		
	}
	
	public void addSpell(Spell s){
		spells.add(s);
	}
	
	public String listSpells(){
		String s = "";
		for(int i = 0; i < spells.size(); i++){
			s = s + spells.get(i) + "\n";
		}
		return s;	
	}
	

}
