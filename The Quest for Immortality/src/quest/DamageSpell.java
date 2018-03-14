package quest;

import java.io.File;
import java.util.Random;

import quest.Player.MoveDirection;

public abstract class DamageSpell extends Spell{

	public enum DamageType{ELEMENTAL, NEUTRAL, OTHER}
	
	public DamageType dt;
	public int damage;
	public int speed;
	public int accuracy; //out of 100
	public MoveDirection direction;
	
	public DamageSpell(String name, int x, int y, DamageType dt, int speed, int damage, int accuracy, MoveDirection direction, int mpCost, File f) {
		super(name, x, y, 20, 20, SpellType.DAMAGE, mpCost, f);
		this.dt = dt;
		this.speed = speed;
		this.accuracy = accuracy;
		this.damage = damage;
		this.direction = direction;
	}
	
	public void move(){
		if(direction.equals(MoveDirection.UP)){
			moveUp();
		}else if(direction.equals(MoveDirection.DOWN)){
			moveDown();
		}else if(direction.equals(MoveDirection.LEFT)){
			moveLeft();
		}else if(direction.equals(MoveDirection.RIGHT)){
			moveRight();
		}else if(direction.equals(MoveDirection.DDL)){
			moveDDL();
		}else if(direction.equals(MoveDirection.DDR)){
			moveDDR();
		}else if(direction.equals(MoveDirection.DUL)){
			moveDUL();
		}else if(direction.equals(MoveDirection.DUR)){
			moveDUR();
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
		this.x -= x;
		this.relX -= x;
		
	}

	public void moveRight(int x) {
		this.x += x;
		this.relX += x;
		
	}

	public void moveUp(int x) {
		this.y -= x;
		this.relY -= x;
		
	}

	public void moveDown(int x) {
		this.y += x;
		this.relY += x;		
	}

	public void moveDUR(int x) {
		this.x += x;
		this.relX += x;
		this.y -= x;
		this.relY -= x;
	}

	public void moveDUL(int x) {
		this.x -= x;
		this.relX -= x;
		this.y -= x;
		this.relY -= x;
		
	}

	public void moveDDR(int x) {
		this.x += x;
		this.relX += x;
		this.y += x;
		this.relY += x;	
		
	}

	public void moveDDL(int x) {
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
	
	public void doDamageToEnemy(Enemy e){
		Random r = new Random();
		int x = r.nextInt(100);
		x++;
		if(x <= accuracy){
			e.damage(damage);
		}else{
			//attack Misses
		}
		this.isFinished = true;
	}
	
	public void doDamageToPlayer(Player p){
		Random r = new Random();
		int x = r.nextInt(100);
		x++;
		if(x <= accuracy){
			p.damage(damage);
		}else{
			//attack misses
		}
		this.isFinished = true;
	}

}
