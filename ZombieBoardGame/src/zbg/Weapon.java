package zbg;

public class Weapon extends Item{
	
	private int size;
	private int durability;
	private boolean broken;
	
	public Weapon(String name, int size, int durability){
		super(name);
		this.size = size;
		this.durability = durability;
		broken = false;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getDurability(){
		return durability;
	}
	
	public void addDurability(int x){
		durability = durability + x;
		if(durability <= 0){
			broken = true;
		}
	}
	
	public boolean checkIfBroken(){
		return broken;
	}

}
