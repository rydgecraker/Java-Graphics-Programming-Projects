package quest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	public enum MoveDirection{UP, DOWN, LEFT, RIGHT, DUR, DUL, DDR, DDL}
	
	private String name;

	private int str;
	private int def;
	private int speed;
	private int cha;
	private int magic;
	private int luck;
	private int deathStat;
	private int con;
	
	private int origStr;
	private int origDef;
	private int origSpeed;
	private int origCha;
	private int origMagic;
	private int origLuck;
	private int origCon;
	
	private int level;
	private int experience;
	
	private int hp;
	private int maxHp;
	
	private int maxMp;
	private int mp;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int relX;
	private int relY;
	
	public boolean canMoveLeft;
	public boolean canMoveRight;
	public boolean canMoveUp;
	public boolean canMoveDown;
	
	public boolean dead;
	
	public MoveDirection lastMove;
	
	public ArrayList<Spell> spells;
	
	public Spell selectedSpell;
	
	public int money;
	
	
	public Player(String name, ArrayList<Integer> stats, int deathStat, int x, int y, int width, int height){
		
		selectedSpell = null;
		
		spells = new ArrayList<Spell>();
		
		lastMove = MoveDirection.UP;
		
		dead = false;
		
		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;
		
		this.name = name;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.relX = x;
		this.relY = y;
		
		str = stats.get(0);
		def = stats.get(1);
		speed = stats.get(2);
		cha = stats.get(3);
		magic = stats.get(4);
		luck = stats.get(5);
		con = stats.get(6);
		
		origStr = str;
		origDef = def;
		origSpeed = speed;
		origCha = cha;
		origMagic = magic;
		origLuck = luck;
		origCon = con;
		
		money = 0;
		
		this.deathStat = deathStat;
		
		level = 1;
		experience = 0;
		
		maxHp = 10 + con;
//		maxMp = 10 + magic;
		maxMp = 10000;
		
		hp = maxHp;
		mp = maxMp;
		
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeMoney(int x){
		money += x;
	}
	
	public void setMoney(int x){
		money = x;
	}
	
	public int getStrength(){
		return str;
	}
	
	public void changeStrength(int x){
		str += x;
	}
	
	public void setStrength(int x){
		str = x;
	}
	
	public void setOrigStr(int x){
		origStr = x;
	}
	
	public int getDefense(){
		return def;
	}
	
	public void changeDefense(int x){
		def += x;
	}
	
	public void setDefense(int x){
		def = x;
	}
	
	public void setOrigDef(int x){
		origDef = x;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void changeSpeed(int x){
		speed += x;
	}
	
	public void setSpeed(int x){
		speed = x;
	}
	
	public void setOrigSpeed(int x){
		origSpeed = x;
	}
	
	public int getCharisma(){
		return cha;
	}
	
	public void changeCharisma(int x){
		cha += x;
	}
	
	public void setCharisma(int x){
		cha = x;
	}
	
	public void setOrigCharisma(int x){
		origCha = x;
	}
	
	public int getMagic(){
		return magic;
	}
	
	public void changeMagic(int x){
		magic += x;
		
	}
	
	public void setMagic(int x){
		magic = x;
	}
	
	public void setOrigMagic(int x){
		origMagic = x;
	}
	
	public int getLuck(){
		return luck;
	}
	
	public void changeLuck(int x){
		luck += x;
	}
	
	public void setLuck(int x){
		luck = x;
	}
	
	public void setOrigLuck(int x){
		origLuck = x;
	}
	
	public int getConstitution(){
		return con;
	}
	
	public void changeConstitution(int x){
		con += x;
	}
	
	public void setConstitution(int x){
		con = x;
	}
	
	public void setOrigConstitution(int x){
		origCon = x;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void changeLevel(int x){
		level += x;
	}
	
	public void setLevel(int x){
		level = x;
	}
	
	public int getExperience(){
		return experience;
	}
	
	public void changeExperience(int x){
		experience += x;
	}
	
	public void setExperience(int x){
		experience = x;
	}
	
	public int getDeathStat(){
		return deathStat;
	}

	public void changeDeathStat(int x){
		deathStat += x;
	}
	
	public void setDeathStat(int x){
		deathStat = x;
	}
	
	public int getHp(){
		return hp;
	}
	
	public void damage(int x){
		x -= def;
		if(x < 0){
			x = 0;
		}
		Random r = new Random();
		int b = r.nextInt(100);
		b++;
		if(b < luck){
			x = x / 2;
		}
		hp = hp - x;
		if(hp <= 0){
			dead = true;
		}
	}
	
	public void spellDamage(Spell s){
		if(s instanceof DamageSpell){
			int x = ((DamageSpell) s).damage;
			x -= def;
			if(x < 0){
				x = 0;
			}
			Random r = new Random();
			int b = r.nextInt(100);
			b++;
			if(b < luck){
				x = x / 2;
			}
			hp = hp - x;
			if(hp <= 0){
				dead = true;
			}
		}
	}
	
	public void heal(int x){
		if(hp + x > maxHp){
			hp = maxHp;
		}else{
			hp += x;
		}
	}
	
	public void setHp(int x){
		hp = x;
	}
	
	public int getMaxHp(){
		return maxHp;
	}
	
	public void changeMaxHp(int x){
		maxHp += x;
	}
	
	public void setMaxHp(int x){
		maxHp = x;
	}
	
	public int getMp(){
		return mp;
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
	
	public void setMp(int x){
		mp = x;
	}
	
	public int getMaxMp(){
		return maxMp;
	}
	
	public void changeMaxMp(int x){
		maxMp += x;
	}
	
	public void setMaxMp(int x){
		maxMp = x;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		name = s;
	}
	
	
	public void checkForLevelUp(){
		if(experience >= 100){
			levelUp();
		}
	}
	
	public void levelUp(){
		changeLevel(1);
		setExperience(experience - 100);
		
		changeStrength(getStatRaise());
		changeDefense(getStatRaise());
		changeSpeed(getStatRaise());
		changeCharisma(getStatRaise());
		changeMagic(getStatRaise());
		changeLuck(getStatRaise());
		changeConstitution(getStatRaise());
		maxHp = 10 + con;
		maxMp = 10 + magic;
	}
	
	public int getStatRaise(){
		Random r = new Random();
		int value = r.nextInt(4);
		return value;
	}
	
	public String displayStats(){
		String s = "Name: " + name + "\n";
		s = s + "Level: " + level + "\n";
		s = s + "HP: " + hp + "/" + maxHp + "\n";
		s = s + "MP: " + mp + "/" + maxMp + "\n";
		s = s + "Strength: " + str + "\n";
		s = s + "Defense: " + def + "\n";
		s = s + "Speed: " + speed + "\n";
		s = s + "Charisma: " + cha + "\n";
		s = s + "Magic: " + magic + "\n";
		s = s + "Luck: " + luck + "\n";
		s = s + "Constitution: " + con;
		
		return s;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getRelX(){
		return relX;
	}
	
	public int getRelY(){
		return relY;
	}
	
	public Rectangle getRelArea(){
		return new Rectangle(relX, relY, width, height);
	}
	
	public Rectangle getAbsoluteArea(){
		return new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
	public void moveUp(int speed){
		lastMove = MoveDirection.UP;
		relY -= speed;
	}
	
	public void moveDown(int speed){
		lastMove = MoveDirection.DOWN;
		relY += speed;
	}
	
	public void moveLeft(int speed){
		lastMove = MoveDirection.LEFT;
		relX -= speed;
	}
	
	public void moveRight(int speed){
		lastMove = MoveDirection.RIGHT;
		relX += speed;
	}
	
	public void moveDUR(int speed){
		lastMove = MoveDirection.DUR;
		relY -= speed;
		relX += speed;
	}
	
	public void moveDUL(int speed){
		lastMove = MoveDirection.DUL;
		relY -= speed;
		relX -= speed;
	}
	
	public void moveDDR(int speed){
		lastMove = MoveDirection.DDR;
		relY += speed;
		relX += speed;
	}
	
	public void moveDDL(int speed){
		lastMove = MoveDirection.DDL;
		relY += speed;
		relX -=speed;
	}
	
	public Rectangle getViewArea(){
		int x = relX - 2000;
		int y = relY - 2000;
		return new Rectangle(x, y, 4000, 4000);
	}
	
	public void canMove(){
		canMoveLeft = true;
		canMoveRight = true;
		canMoveDown = true;
		canMoveUp = true;
	}
	
	public void cantMove(){
		canMoveLeft = false;
		canMoveRight = false;
		canMoveDown = false;
		canMoveUp = false;
	}
	
	public void addSpell(Spell s){
		spells.add(s);
		selectedSpell = s;
	}
	
	public String listSpells(){
		String s = "";
		for(int i = 0; i < spells.size(); i++){
			s = s + spells.get(i) + "\n";
		}
		return s;	
	}
	
	public boolean canCastSpell(){
		if(selectedSpell instanceof DamageSpell){
			
			if(selectedSpell instanceof FireballSpell){
				selectedSpell = new FireballSpell(0, 0, magic, lastMove);
			}else if(selectedSpell instanceof IceballSpell){
				selectedSpell = new IceballSpell(0, 0, magic, lastMove);
			}
			
			if(mp >= selectedSpell.mpCost){
				return true;
			}else{
				return false;
			}
			
		}else{
			//Doesnt cast spell
			return false;
		}
	}
	
	public Spell castSpell(){
		if(selectedSpell instanceof DamageSpell){
			int spellx = 0;
			int spelly = 0;
			
			if(lastMove.equals(MoveDirection.UP)){
				spellx = x;
				spelly = y - 20;
			}else if(lastMove.equals(MoveDirection.DOWN)){
				spellx = x;
				spelly = y + 40;
			}else if(lastMove.equals(MoveDirection.LEFT)){
				spellx = x - 20;
				spelly = y;
			}else if(lastMove.equals(MoveDirection.RIGHT)){
				spellx = x + 40;
				spelly = y;
			}else if(lastMove.equals(MoveDirection.DUR)){
				spellx = x + 40;
				spelly = y - 20;
			}else if(lastMove.equals(MoveDirection.DUL)){
				spellx = x - 20;
				spelly = y - 20;
			}else if(lastMove.equals(MoveDirection.DDL)){
				spellx = x - 20;
				spelly = y + 40;
			}else if(lastMove.equals(MoveDirection.DDR)){
				spellx = x + 40;
				spelly = y + 40;
			}
			
			if(selectedSpell instanceof FireballSpell){
				selectedSpell = new FireballSpell(spellx, spelly, magic, lastMove);
			}else if(selectedSpell instanceof IceballSpell){
				selectedSpell = new IceballSpell(spellx, spelly, magic, lastMove);
			}
			
			if(mp >= selectedSpell.mpCost){
				mp -= selectedSpell.mpCost;
				return selectedSpell;
			}else{
				return null;
			}
			
		}else{
			//Doesnt cast spell
			return null;
		}
	}
	
	
}
