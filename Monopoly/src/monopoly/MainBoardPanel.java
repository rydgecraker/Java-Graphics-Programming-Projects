package monopoly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class MainBoardPanel extends JPanel implements ActionListener{

	private ArrayList<Space> board;
	private ArrayList<Player> players;
	private ArrayList<Computer> computers;
	private int boardSectionX;
	private int boardSectionY;
	private JFrame frame;
	private ArrayList<Color> colorsLeft;
	private int numOfPlayers;
	private int numOfComs;
	private int resX;
	private int resY;
	private ArrayList<Dice> dice;
	private boolean doubles;
	private int boostery;
	private int boosterx;
	private JButton cash;
	private GamePiece gpp;
	private JFrame buyStuff;
	private boolean playerBought;
	private Property prop;
	private Player pl;
	private JFrame auctionFrame;
	private JPanel auctionPanel;
	private JButton playername;
	private ArrayList<GamePiece> auctionPieces;
	private GamePiece currentAuctionPlayer;
	private int currentBid;
	private GamePiece currentBidOwner;
	private Property auctionProperty;
	
	public MainBoardPanel(int resX, int resY, int plrs, int coms, JFrame frame) {
		this.resX = resX;
		this.resY = resY;
		numOfPlayers = plrs;
		numOfComs = coms;
		colorsLeft = new ArrayList<Color>();
		colorsLeft.add(Color.RED);
		colorsLeft.add(Color.ORANGE);
		colorsLeft.add(Color.YELLOW);
		colorsLeft.add(Color.GREEN);
		colorsLeft.add(Color.BLUE);
		colorsLeft.add(Color.CYAN);
		colorsLeft.add(Color.MAGENTA);
		colorsLeft.add(Color.PINK);

		this.frame = frame;
		players = new ArrayList<Player>();
		computers = new ArrayList<Computer>();
		// + ((this.frame.getWidth() * .1) - 36.25))
		// + ((this.frame.getWidth() * .1) - 48 + (2/3)))
		int jwidth = (int) (((this.frame.getWidth() - 36.25) * .8));
		int jheight = (int) (((this.frame.getHeight() - (48 + (2 / 3))) * .75));
		boardSectionX = jwidth / 11;
		boardSectionY = jheight / 11;
		int px = (10 * boardSectionX) + ((boardSectionX / 3));
		int py = (10 * boardSectionY) + ((boardSectionY / 3));
		int pheight = 10;
		int pwidth = 10;

		dice = new ArrayList<Dice>();
		dice.add(new Dice(jwidth / 2, jheight / 2, 100, 100, 6));
		dice.add(new Dice(jwidth / 2 + 120, jheight / 2 + 120, 100, 100, 6));

		determineBoard();
		for (int i = 1; i <= numOfPlayers; i++) {
			if (i == 1) {
				players.add(new Player(i, px, py, pwidth, pheight, "Player " + i, colorsLeft));
			} else if (i == 2) {
				py = py + boardSectionY / 3;
				players.add(new Player(i, px, py, pwidth, pheight, "Player " + i, colorsLeft));
			} else if (i == 3) {
				px = px + boardSectionX / 3;
				players.add(new Player(i, px, py, pwidth, pheight, "Player " + i, colorsLeft));
			} else if (i == 4) {
				py = py - boardSectionY / 3;
				players.add(new Player(i, px, py, pwidth, pheight, "Player " + i, colorsLeft));
			}
		}

		int startingPoint = numOfPlayers + 1;

		for (int i = 1; i <= numOfComs; i++) {
			if (startingPoint == 2) {
				if (i == 1) {
					py = py + boardSectionY / 3;
					computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
				} else if (i == 2) {
					px = px + boardSectionX / 3;
					computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
				} else if (i == 3) {
					py = py - boardSectionY / 3;
					computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
				}
			} else if (startingPoint == 3) {
				if (i == 1) {
					px = px + boardSectionX / 3;
					computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
				} else if (i == 2) {
					py = py - boardSectionY / 3;
					computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
				}
			} else {
				py = py - boardSectionY / 3;
				computers.add(new Computer(i + numOfPlayers, px, py, pwidth, pheight, "Computer" + i, colorsLeft));
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < dice.size(); i++) {
			dice.get(i).draw(g);
		}
		for (int i = 0; i < board.size(); i++) {
			board.get(i).draw(g);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw(g);
		}
		for (int i = 0; i < computers.size(); i++) {
			computers.get(i).draw(g);
		}
	}

	public void determineBoard() {
		board = new ArrayList<Space>();
		int counter = 0;
		boosterx = 0;
		boostery = 0;

		if (resX == 1920) {
			boostery = 11;
		} else if (resX == 1280) {
			boostery = 7;
			boosterx = 5;
		} else if (resX == 1024) {
			boostery = -1;
			boosterx = 9;
		} else if (resX == 800) {
			boostery = 3;
			boosterx = 6;
		}

		for (int i = 0; i < 11; i++) {
			if (i == 10) {
				board.add(new Space(i * boardSectionX, 0 * boardSectionY, boardSectionX + boosterx, boardSectionY, counter));
			} else {
				board.add(new Space(i * boardSectionX, 0 * boardSectionY, boardSectionX, boardSectionY, counter));
			}

			counter++;
			if (i != 0) {
				if (i == 10) {
					board.add(new Space(0 * boardSectionX, i * boardSectionY, boardSectionX, boardSectionY + boostery, counter));
					counter++;
				} else {
					board.add(new Space(0 * boardSectionX, i * boardSectionY, boardSectionX, boardSectionY, counter));
					counter++;
				}

			}
			if (i != 0) {
				if (i == 10) {
					board.add(new Space(10 * boardSectionX, i * boardSectionY, boardSectionX + boosterx, boardSectionY + boostery, counter));
					counter++;
				} else {
					board.add(new Space(10 * boardSectionX, i * boardSectionY, boardSectionX + boosterx, boardSectionY, counter));
					counter++;
				}

			}
			if (i != 0 && i != 10) {
				board.add(new Space(i * boardSectionX, 10 * boardSectionY, boardSectionX, boardSectionY + boostery, counter));
				counter++;
			}
		}
	}

	public int rollDice() {
		Random r = new Random();
		int roll1 = r.nextInt(6) + 1;
		dice.get(0).changeRoll(roll1);
		int roll2 = r.nextInt(6) + 1;
		dice.get(1).changeRoll(roll2);
		this.repaint();

		int dice1 = dice.get(0).getRoll();
		int dice2 = dice.get(1).getRoll();
		if (dice1 == dice2) {
			doubles = true;
		} else {
			doubles = false;
		}

		return (dice1 + dice2);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Computer> getComputers() {
		return computers;
	}

	public boolean checkForDoubles() {
		return doubles;
	}

	public void move(GamePiece gp, int roll) {
		gp.move(roll, boardSectionX, boardSectionY, this);
	}

	public void changeJail(GamePiece gp) {
		gp.changeJail();
	}

	public void turnOperations(GamePiece gp, CreateAndRunGame carg) {
		// Check space
		int sn = gp.getSpaceNumber();
		if (sn == 1 || sn == 3 || sn == 6 || sn == 8 || sn == 9 || sn == 11 || sn == 13 || sn == 14 || sn == 16 || sn == 18 || sn == 19 || sn == 21 || sn == 23 || sn == 24 || sn == 26 || sn == 27 || sn == 29
				|| sn == 31 || sn == 32 || sn == 34 || sn == 37 || sn == 39) { // Properties
			Boolean owned = false;
			for (int i = 0; i < players.size(); i++) {
				ArrayList<Place> place = new ArrayList<Place>();
				place = players.get(i).getProperties();
				for (int j = 0; j < place.size(); j++) {
					if (new Property(" ", 0, 0, 0, 0, 0, 0, 0, 0, 0, sn, null).equals(place.get(j))) {
						owned = true;
						if (gp.equals(players.get(i))) {
							JOptionPane.showMessageDialog(null, gp.getName() + " landed on their own property.");
						} else {
							if (place.get(j).isMortgaged()) {
								JOptionPane.showMessageDialog(null, "The property is mortgaged.");
							} else {
								if (gp.getMoney() < place.get(j).getRent()) {
									for (int jj = 0; jj < gp.getProperties().size(); jj++) {
										Property p = (Property) gp.getProperties().get(jj);
										if(p.getNumberOfHouses() > 0){
											for(int hzs = 0; hzs < p.getNumberOfHouses(); hzs ++){
												p.sellHouse(gp);
												JOptionPane.showMessageDialog(null, "A house was autotically sold from " + p.getName());
												if (gp.getMoney() >= place.get(j).getRent()) {
													break;
												}
											}
										}
										if (gp.getProperties().get(jj).isMortgaged() == false) {
											gp.getProperties().get(jj).mortgage(gp);
											JOptionPane.showMessageDialog(null, gp.getProperties().get(i).getName() + " was automatically mortgaged.");
										}
										if (gp.getMoney() >= place.get(j).getRent()) {
											break;
										}
									}
									if (gp.getMoney() < place.get(j).getRent()) {
										gp.bankrupt(players.get(i));
										players.remove(gp);
									} else {
										place.get(j).payRent(gp, players.get(i));
										JOptionPane.showMessageDialog(null, gp.getName() + " just paid rent of " + place.get(j).getRent() + " to " + players.get(i).getName());
									}
								} else {
									place.get(j).payRent(gp, players.get(i));
									JOptionPane.showMessageDialog(null, gp.getName() + " just paid rent of " + place.get(j).getRent() + " to " + players.get(i).getName());
								}
							}
						}
					}
				}
			}
			for (int i = 0; i < computers.size(); i++) {
				ArrayList<Place> place = new ArrayList<Place>();
				place = computers.get(i).getProperties();
				for (int j = 0; j < place.size(); j++) {
					if (new Property(" ", 0, 0, 0, 0, 0, 0, 0, 0, 0, sn, null).equals(place.get(j))) {
						owned = true;
						if (gp.equals(computers.get(i))) {
							JOptionPane.showMessageDialog(null, gp.getName() + " landed on their own property.");
						} else {
							if (place.get(j).isMortgaged()) {
								JOptionPane.showMessageDialog(null, "The property is mortgaged.");
							} else {
								if (gp.getMoney() < place.get(j).getRent()) {
									for (int jj = 0; jj < gp.getProperties().size(); jj++) {
										Property p = (Property) gp.getProperties().get(jj);
										if(p.getNumberOfHouses() > 0){
											for(int hzs = 0; hzs < p.getNumberOfHouses(); hzs ++){
												p.sellHouse(gp);
												JOptionPane.showMessageDialog(null, "A house was autotically sold from " + p.getName());
												if (gp.getMoney() >= place.get(j).getRent()) {
													break;
												}
											}
										}
										if (gp.getProperties().get(jj).isMortgaged() == false) {
											gp.getProperties().get(jj).mortgage(gp);
											JOptionPane.showMessageDialog(null, gp.getProperties().get(i).getName() + " was automatically mortgaged.");
										}
										if (gp.getMoney() >= place.get(j).getRent()) {
											break;
										}
									}
									if (gp.getMoney() < place.get(j).getRent()) {
										JOptionPane.showMessageDialog(null, gp.getName() + " went bankrupt! " + computers.get(i).getName() + " recieves all their money and properties.");
										gp.bankrupt(computers.get(i));
										computers.remove(gp);
									} else {
										place.get(j).payRent(gp, computers.get(i));
										JOptionPane.showMessageDialog(null, gp.getName() + " just paid rent of " + place.get(j).getRent() + " to " + computers.get(i).getName());
									}
								} else {
									place.get(j).payRent(gp, computers.get(i));
									JOptionPane.showMessageDialog(null, gp.getName() + " just paid rent of " + place.get(j).getRent() + " to " + computers.get(i).getName());
								}
							}
						}
					}
				}
			}
			
		if(owned == false){	
			Property p = instateProperty(sn);
			prop = p;
			if(gp instanceof Player){
				playerBuyProperty(p, gp);
				pl = (Player) gp;
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(carg.getHr5() == 1 && playerBought == false){
					JOptionPane.showMessageDialog(null, gp.getName() + " Decided not to buy " + p.getName() + ", It is now going up for auciton.");
					this.propertyAuction(p, gp);
				}else if(playerBought == false){
					JOptionPane.showMessageDialog(null, gp.getName() + " Decided not to buy " + p.getName());
				}else if(playerBought == true){
					JOptionPane.showMessageDialog(null, gp.getName() + " just bought " + p.getName() + " for $" + p.getPrice());
				}
			}else{
				Computer c = (Computer) gp;
				if(c.decideIfGoodDeal(p, board) == true){
					JOptionPane.showMessageDialog(null, gp.getName() + " just bought " + p.getName() + " for $" + p.getPrice());
				}else{
					if(carg.getHr5() == 1){
						JOptionPane.showMessageDialog(null, gp.getName() + " Decided not to buy " + p.getName() + ", It is now going up for auciton.");
						this.propertyAuction(p, gp);
					}else{
						JOptionPane.showMessageDialog(null, gp.getName() + " Decided not to buy " + p.getName());
					}
				}
			}
		}

		}
	}
	
	public Property instateProperty(int spaceNumber){
		Property p;
		if(spaceNumber == 1){
			p = new Property("Mediterranean Avenue", 2, 10, 30, 90, 160, 250, 60, 30, 50, spaceNumber, board.get(36).getColor());
	    }else if(spaceNumber == 3){
	    	p = new Property("Baltic Avenue", 4, 20, 60, 180, 320, 450, 60, 30, 50, spaceNumber, board.get(28).getColor());
		}else if(spaceNumber == 6){
			p = new Property("Oriental Avenue", 6, 30, 90, 270, 400, 550, 100, 50, 50, spaceNumber, board.get(16).getColor());
		}else if(spaceNumber == 8){
			p = new Property("Vermont Avenue", 6, 30, 90, 270, 400, 550, 100, 50, 50, spaceNumber, board.get(8).getColor());
		}else if(spaceNumber == 9){
			p = new Property("Connecticut Avenue", 8, 40, 100, 300, 450, 600, 120, 60, 50, spaceNumber, board.get(4).getColor());
		}else if(spaceNumber == 11){
			p = new Property("St. Charles Place", 10, 50, 150, 450, 625, 750, 140, 70, 100, spaceNumber, board.get(34).getColor());
		}else if(spaceNumber == 13){
			p = new Property("States Avenue", 10, 50, 150, 450, 625, 750, 140, 70, 100, spaceNumber, board.get(26).getColor());
		}else if(spaceNumber == 14){
			p = new Property("Virginia Avenue", 12, 60, 180, 500, 700, 900, 160, 80, 100, spaceNumber, board.get(22).getColor());
		}else if(spaceNumber == 16){
			p = new Property("St. James Place", 14, 70, 200, 550, 750, 950, 180, 90, 100, spaceNumber, board.get(14).getColor());
		}else if(spaceNumber == 18){
			p = new Property("Tennessee Avenue", 14, 70, 200, 550, 750, 950, 180, 90, 100, spaceNumber, board.get(6).getColor());
		}else if(spaceNumber == 19){
			p = new Property("New York Avenue", 16, 80, 220, 600, 800, 1000, 200, 100, 100, spaceNumber, board.get(2).getColor());
		}else if(spaceNumber == 21){
			p = new Property("Kentucky Avenue", 18, 90, 250, 700, 875, 1050, 220, 110, 150, spaceNumber, board.get(1).getColor());
		}else if(spaceNumber == 23){
			p = new Property("Indiana Avenue", 18, 90, 250, 700, 875, 1050, 220, 110, 150, spaceNumber, board.get(9).getColor());
		}else if(spaceNumber == 24){
			p = new Property("Illinois Avenue", 20, 100, 300, 750, 925, 1100, 240, 120, 150, spaceNumber, board.get(13).getColor());
		}else if(spaceNumber == 26){
			p = new Property("Atlantic Avenue", 22, 110, 330, 800, 975, 1150, 260, 130, 150, spaceNumber, board.get(21).getColor());
		}else if(spaceNumber == 27){
			p = new Property("Ventnor Avenue", 22, 110, 330, 800, 975, 1150, 260, 130, 150, spaceNumber, board.get(25).getColor());
		}else if(spaceNumber == 29){
			p = new Property("Marvin Gardens", 24, 120, 360, 850, 1025, 1200, 280, 140, 150, spaceNumber, board.get(33).getColor());
		}else if(spaceNumber == 31){
			p = new Property("Pacific Avenue", 26, 130, 390, 900, 1100, 1275, 300, 150, 200, spaceNumber, board.get(3).getColor());
		}else if(spaceNumber == 32){
			p = new Property("North Carolina Avenue", 26, 130, 390, 700, 1100, 1275, 300, 150, 200, spaceNumber, board.get(7).getColor());
		}else if(spaceNumber == 34){
			p = new Property("Pennsylvania Avenue", 28, 150, 450, 1000, 1200, 1400, 320, 160, 200, spaceNumber, board.get(15).getColor());
		}else if(spaceNumber == 37){
			p = new Property("Park Place", 35, 175, 500, 1100, 1300, 1500, 350, 175, 200, spaceNumber, board.get(27).getColor());
		}else{
			p = new Property("Boardwalk", 50, 200, 600, 1400, 1700, 2000, 1700, 200, 200, spaceNumber, board.get(35).getColor());
		}
		return p;
	}
	
	public void propertyAuction(Property p, GamePiece gp){
		auctionProperty = p;
		currentBidOwner = null;
		currentBid = 0;
		auctionPieces = new ArrayList<GamePiece>();
		for(int i = 0; i < players.size(); i++){
			auctionPieces.add((GamePiece) players.get(i));
		}
		for(int i = 0; i < computers.size(); i++){
			auctionPieces.add((GamePiece) computers.get(i));
		}
		auctionPieces.remove(gp);
		currentAuctionPlayer = auctionPieces.get(0);
		
		auctionFrame = new JFrame();
		auctionFrame.setSize(500, 500);
		auctionPanel = new JPanel();
		auctionPanel.setLayout(new BorderLayout());
		auctionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		PropertyPane pp = new PropertyPane(p);
		playername = new JButton(currentAuctionPlayer.getName() + " -- Current bid is: $" + currentBid);
		JButton bid = new JButton("Bid");
		bid.setActionCommand("Bid");
		JButton pass = new JButton("Pass");
		pass.setActionCommand("Pass");
		JButton property = new JButton("Property");
		property.setActionCommand("Property");
		playername.setEnabled(false);
		
		bid.addActionListener(this);
		pass.addActionListener(this);
		property.addActionListener(this);
		
		
		auctionPanel.add(playername, BorderLayout.NORTH);
		auctionPanel.add(pass, BorderLayout.WEST);
		auctionPanel.add(property, BorderLayout.SOUTH);
		auctionPanel.add(bid, BorderLayout.EAST);
		auctionPanel.add(pp);
		
		auctionFrame.setContentPane(auctionPanel);
		auctionFrame.setVisible(true);
		auctionFrame.repaint();
	}
	
	public void playerBuyProperty(Property p, GamePiece gp){
		gpp = gp;
		buyStuff = new JFrame();
		buyStuff.setSize(500, 500);
		JPanel buyStuffPanel = new JPanel();
		buyStuffPanel.setLayout(new BorderLayout());
		JButton buyIt = new JButton("Buy It");
		buyIt.setActionCommand("bi");
		buyIt.addActionListener(this);
		JButton dont = new JButton("Don't Buy It");
		dont.setActionCommand("dbi");
		dont.addActionListener(this);
		cash = new JButton("You have: $" + gp.getMoney());
		buyStuffPanel.add(buyIt, BorderLayout.EAST);
		buyStuffPanel.add(dont, BorderLayout.WEST);
		cash.setEnabled(false);
		buyStuffPanel.add(cash, BorderLayout.NORTH);
		JButton changeProperties = new JButton("Property");
		changeProperties.addActionListener(this);
		changeProperties.setActionCommand("cp");
		buyStuffPanel.add(changeProperties, BorderLayout.SOUTH);
		
		
		PropertyPane pp = new PropertyPane(p);
		buyStuffPanel.add(pp);
		buyStuff.setContentPane(buyStuffPanel);
		buyStuff.setVisible(true);
		pp.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("bi")){
			if(pl.getMoney() >= prop.getPrice()){
				playerBought = true;
				buyStuff.dispose();
				synchronized (this) {
					this.notify();
				}
			}else{
				JOptionPane.showMessageDialog(null, "You can't afford that!");
			}
			
		}else if(e.getActionCommand().equals("dbi")){
			playerBought = false;
			buyStuff.dispose();
			synchronized (this) {
				this.notify();
			}
		}else if(e.getActionCommand().equals("Property")){ 
			
		}else if(e.getActionCommand().equals("Bid")){
			String tempList[] = new String[currentAuctionPlayer.getMoney() + 1];
		    for (int i = currentBid + 1; i < tempList.length - currentBid; i++) {
		      tempList[i] = Integer.toString(i);
		    }
		    if(tempList.length == 0){
		    	JOptionPane.showMessageDialog(null, "You don't have enough money to bid on that");
		    }else{
		    	List<String> temp = Arrays.asList(tempList);
		    	Collections.reverse(temp);
		    	String[] bigList = (String[]) temp.toArray();
		    	String input;
		    	if(currentBidOwner == null){
		    	    input = (String) JOptionPane.showInputDialog(null, "How much are you going to bid?\n The current bid is $" + 0, "Bid", JOptionPane.QUESTION_MESSAGE, null, bigList, bigList[0]);
			    }else{
			    	input = (String) JOptionPane.showInputDialog(frame, "How much are you going to bid?\n The current bid is $" + currentBid + " given by " + currentBidOwner.getName(), "Bid", JOptionPane.QUESTION_MESSAGE, null, bigList, bigList[0]);
			    }
		    	
		    	if(input == null){
		    		actionPerformed(e);
		    	}else{
		    		currentBid = Integer.parseInt(input);
		    		if(auctionPieces.size() == 1){
		    			JOptionPane.showMessageDialog(null, currentBidOwner.getName() + " Just bought " + auctionProperty.getName() + " for $" + currentBid);
		    			currentBidOwner.changeMoney(-currentBid);
		    			currentBidOwner.addProperty(auctionProperty);
		    			auctionFrame.dispose();
		    		}else{
		    			currentBidOwner = currentAuctionPlayer;
		    			int apl = auctionPieces.indexOf(currentAuctionPlayer) + 1;
		    			if(auctionPieces.size() == apl + 1){
		    				currentAuctionPlayer = auctionPieces.get(0);
		    			}else{
		    				currentAuctionPlayer = auctionPieces.get(apl);
		    			}
		    			playername.setText(currentAuctionPlayer.getName() + " -- Current bid is: $" + currentBid);
		    		}
		    	}
		    	
		    }
		    

		}else if(e.getActionCommand().equals("Pass")){
			if(auctionPieces.size() == 1 && currentBidOwner != currentAuctionPlayer){
				JOptionPane.showMessageDialog(null, "You're the last player in the auction, you can't pass");
			}else if(auctionPieces.size() == 1 && currentBidOwner == currentAuctionPlayer){
				JOptionPane.showMessageDialog(null, currentBidOwner.getName() + " Just bought " + auctionProperty.getName() + " for $" + currentBid);
    			currentBidOwner.changeMoney(-currentBid);
    			currentBidOwner.addProperty(auctionProperty);
    			auctionFrame.dispose();
			}else{
				auctionPieces.remove(currentAuctionPlayer);
				currentBidOwner = currentAuctionPlayer;
    			int apl = auctionPieces.indexOf(currentAuctionPlayer) + 1;
    			if(auctionPieces.size() == apl + 1){
    				currentAuctionPlayer = auctionPieces.get(0);
    			}else{
    				currentAuctionPlayer = auctionPieces.get(apl);
    			}
    			playername.setText(currentAuctionPlayer.getName() + " -- Current bid is: $" + currentBid);
			}
		}
		
	}

}
