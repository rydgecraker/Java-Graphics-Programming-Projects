package zbg;

public class SpecialItem extends Item{
	
	private int uses;
	private boolean broken;

	public SpecialItem(String name, int uses) {
		super(name);
		this.uses = uses;
		broken = false;
	}
	
	public int getUses(){
		return uses;
	}
	
	public void addUses(int x){
		uses = uses + x;
		if(uses <= 0){
			broken = true;
		}
	}
	
	public boolean checkIfBroken(){
		return broken;
	}

}
