package game;

public class World {
	
	private int[] seed;
	private int seedLength = 50;
	
	public World(String seed){
		this.seed = new int[seedLength];
		if(seed.length() > this.seed.length){
			for(int i = 0; i < this.seed.length; i++){
				char temp = seed.charAt(i);
				temp = (char) (temp - '0');
				this.seed[i] = (temp % 10);
			}
		}else{
			int i = 0;
			for(i = 0; i < seed.length(); i++){
				char temp = seed.charAt(i);
				temp = (char) (temp - '0');
				this.seed[i] = (temp % 10);
			}
			while(i < this.seed.length){
				this.seed[i] = 0;
				i++;
			}
		}
	}
	
	public int[] getSeed(){
		return seed;
	}
	
	public String getSeedAsString(){
		String s = "";
		for(int i = 0; i < seed.length; i++){
			s += seed[i];
		}
		return s;
	}
}
