package quest;

import java.io.File;

public class Weapon extends Item{
	
	public enum WeaponType{MELEESHORT, MELEEMED, MELEELONG, RANGED, OTHER};
	
	public WeaponType weaponType;
	public int damage;
	public int speed;
	public int accuracy;

	public Weapon(String name, int x, int y, int width, int height, File f, WeaponType weaponType, int damage, int speed, int accuracy) {
		super(name, x, y, width, height, f, ItemType.WEAPON);
		this.weaponType = weaponType;
		this.damage = damage;
		this.speed = speed;
		this.accuracy = accuracy;
		
		
	}

}
