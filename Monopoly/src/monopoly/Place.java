package monopoly;

import javax.swing.JOptionPane;

public abstract class Place {

	
	private String name;
	private int rent;
	private int price;
	private int mortgageValue;
	private int spaceNumber;
	private boolean mortgage;
	
	public Place(String name, int rent, int price, int mortgageValue, int spaceNumber){
		this.name = name;
		this.rent = rent;
		this.price = price;
		this.mortgageValue = mortgageValue;
		this.spaceNumber = spaceNumber;
		mortgage = false;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRent(){
		return rent;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getMortgageValue(){
		return mortgageValue;
	}
	
	public int getSpaceNumber(){
		return spaceNumber;
	}
	
	public boolean isMortgaged(){
		return mortgage;
	}
	
	public void mortgage(GamePiece gp){
		if(mortgage == false){
			mortgage = true;
			gp.changeMoney(mortgageValue);
		}else{
			JOptionPane.showMessageDialog(null, "It's already mortgaged.");
		}
	}
	
	public void unMortgage(GamePiece gp){
		if(mortgage == false){
			JOptionPane.showMessageDialog(null, "It isn't mortgaged.");
		}else{
			if(gp.getMoney() < mortgageValue){
				JOptionPane.showMessageDialog(null, "You don't have enough money to unmortgage this property.");
			}else{
				gp.changeMoney(-1 * mortgageValue);
				mortgage = false;
			}
		}
	}
	
	public boolean equals(Object o){
		Place p = (Place) o;
		if(this.getSpaceNumber() == (p.getSpaceNumber())){
			return true;
		}else{
			return false;
		}
	}
	
	public abstract void payRent(GamePiece payer, GamePiece payee);
	

	
}
