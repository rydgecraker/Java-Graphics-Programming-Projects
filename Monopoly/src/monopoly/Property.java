package monopoly;

import java.awt.Color;

import javax.swing.JOptionPane;

public class Property extends Place{
	
	private int house1rent;
	private int house2rent;
	private int house3rent;
	private int house4rent;
	private int hotelrent;
	private int numberOfHouses;
	private int houseCost;
	private Color color;
	private boolean allOwned;
	
	public Property(String name, int rent, int house1rent, int house2rent, int house3rent, int house4rent, int hotelrent, int price, int mortgageValue, int houseCost, int spaceNumber, Color c){
		super(name, rent, price, mortgageValue, spaceNumber);
		this.house1rent = house1rent;
		this.house2rent = house2rent;
		this.house3rent = house3rent;
		this.house4rent = house4rent;
		this.hotelrent = hotelrent;
		numberOfHouses = 0;
		this.houseCost = houseCost;
		color = c;
		allOwned = false;
	}

	public void payRent(GamePiece payer, GamePiece payee) {
		
	}
	
	public void addHouse(GamePiece gp){
		if(gp.getMoney() < houseCost){
			JOptionPane.showMessageDialog(null, "You don't have enough money for that.");
		}else{
			if(numberOfHouses == 5){
				JOptionPane.showMessageDialog(null, "This property already has a hotel.");
			}else{
				gp.changeMoney(-1 * houseCost);
				numberOfHouses++;
			}
		}
	}
	
	public void sellHouse(GamePiece gp){
		if(numberOfHouses == 0){
			
		}else{
			gp.changeMoney(houseCost);
			numberOfHouses--;
		}
	}
	
	public int getNumberOfHouses(){
		return numberOfHouses;
	}
	
	public int getHouseCost(){
		return houseCost;
	}
	
	public void mortgage(GamePiece gp){
		if(numberOfHouses > 0){
			
		}else{
		super.mortgage(gp);
		}
	}
	
	public boolean checkIfAllOwned(){
		return allOwned;
	}
	
	public void allOwned(){
		allOwned = true;
	}
	
	public void allNotOwned(){
		allOwned = false;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getOneHouseRent(){
		return house1rent;
	}
	public int getTwoHouseRent(){
		return house2rent;
	}
	public int getThreeHouseRent(){
		return house3rent;
	}
	public int getFourHouseRent(){
		return house4rent;
	}
	public int getHotelRent(){
		return hotelrent;
	}
	
	
}
