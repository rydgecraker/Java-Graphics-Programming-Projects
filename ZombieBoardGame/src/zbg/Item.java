package zbg;

public abstract class Item {
	
	private String name;

	public Item(String name){
		this.name = name;
	}
	
	public String getText(){
		return name;
	}
	
}
