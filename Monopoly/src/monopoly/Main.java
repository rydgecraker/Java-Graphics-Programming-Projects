package monopoly;

import javax.swing.*;

public class Main {

	private Object[] newLoadGame;
	private String[] resolution;
	private String[] numberOfPlayers;
	private String[] nonComputerPlayers;
	private String[] houseRules;
	private String[] houseRule1;
	private String[] houseRule2;
	private String[] houseRule3;
	private String[] houseRule4;
	private String[] houseRule5;
	private String[] houseRule6;
	private String[] houseRule7;

	private String nonComPlayers;
	private String players;
	private String res;
	private String hRules;
	private String h1;
	private String h2;
	private String h3;
	private String h4;
	private String h5;
	private String h6;
	private String h7;
	
	private int hr1;
	private int hr2;
	private int hr3;
	private int hr4;
	private int hr5;
	private int hr6;
	private int hr7;
	
	private int computers;
	private int playerz;

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		Boolean b = true;
		newLoadGame = new Object[] { "New Game", "Load Game", "Quit" };
		resolution = new String[] { "1920x1080", "1280x1024", "1024x768", "800x600" };
		numberOfPlayers = new String[] {"2", "3", "4"};
		houseRules = new String[] { "Default (None)", "Rydge's Rules", "Custom" };
		do {
			int game = JOptionPane.showOptionDialog(null, "Would you like to start a new game or load a saved game?", "Monopoly Setup", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, newLoadGame, null);
			if (game == -1 || game == 2) { // Hit the x or quit
				System.exit(0);
			} else if (game == 0) { // Save Game
				res = (String) JOptionPane.showInputDialog(null, "Which resolution would you like to play at?", "Resolution", JOptionPane.QUESTION_MESSAGE, null, resolution, resolution[0]);
				if (res != null) {
					players = (String) JOptionPane.showInputDialog(null, "How many total players? (Including Computers?)", "Players", JOptionPane.QUESTION_MESSAGE, null, numberOfPlayers,numberOfPlayers[0]);
					if (players != null) {
						int x = Integer.parseInt(players);
						nonComputerPlayers = new String[x];
						for (int i = 1; i <= x; i++) {
							nonComputerPlayers[i - 1] = i + "";
						}

						nonComPlayers = (String) JOptionPane.showInputDialog(null, "How many non-computer players?", "Players (Continued)", JOptionPane.QUESTION_MESSAGE, null, nonComputerPlayers,nonComputerPlayers[1]);
						if (nonComPlayers != null) {
							playerz = Integer.parseInt(nonComPlayers);
							computers = x - playerz;
							hRules = (String) JOptionPane.showInputDialog(null, "House Rules?", "More Options", JOptionPane.QUESTION_MESSAGE, null, houseRules, houseRules[0]);
							if (hRules != null) {
								if (hRules.equals("Default (None)")) {
									CreateAndRunGame c = new CreateAndRunGame(res, playerz, computers, 0, 0, 0, 0, 0, 0, 0);
									b = false;
								} else if (hRules.equals("Rydge's Rules")) {
									CreateAndRunGame c = new CreateAndRunGame(res, playerz, computers, 2, 2, 1, 0, 0, 0, 0);
									b = false;
								} else {
									houseRule1 = new String[] { "$500 from the Bank", "Taxes and Card Fines", "Taxes, Card Fines, and Jail Fees" };
									houseRule2 = new String[] { "$200 (Standard)", "$300 (1.5x Standard)", "$400 (Double)" };
									houseRule3 = new String[] { "Nothing (Standard)", "Player recieves $686 (1 of every Bill)" };
									houseRule4 = new String[] { "No Mercy Rule (Standard)", "$10,000", "$20,000" };
									houseRule5 = new String[] { "Never", "After a Player decides not to buy a Property" };
									houseRule6 = new String[] { "10% or $200", "$200" };
									houseRule7 = new String[] { "None", "1", "All" };

									h1 = (String) JOptionPane.showInputDialog(null, "What should be paid to Free Parking?", "House Rules (1/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule1,houseRule1[0]);
									if (h1 != null) {
										h2 = (String) JOptionPane.showInputDialog(null, "How much money should a player recieve for landing on GO?", "House Rules (2/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule2,houseRule2[0]);
										if(h2 != null){
											h3 = (String) JOptionPane.showInputDialog(null, "If a player rolls snake eyes (double 1's), what should happen?", "House Rules (3/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule3,houseRule3[0]);
											if(h3 != null){
												h4 = (String) JOptionPane.showInputDialog(null, "Should there be a Mercy Rule? \n(Player wins because they accumulate a certain amount of money)", "House Rules (4/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule4,houseRule4[0]);
												if(h4 != null){
													h5 = (String) JOptionPane.showInputDialog(null, "When should a Property be auctioned off?", "House Rules (5/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule5,houseRule5[0]);
													if(h5 != null){
														h6 = (String) JOptionPane.showInputDialog(null, "On the space Income Tax, what should be paid?", "House Rules (6/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule6,houseRule6[0]);
														if(h6 != null){
															h7 = (String) JOptionPane.showInputDialog(null, "How many Properties should be dealt out at the beginning of the Game?", "House Rules (7/7)", JOptionPane.QUESTION_MESSAGE, null, houseRule7,houseRule7[0]);
															if(h7 != null){
																if(h1.equals("$500 from the Bank")){
																	hr1 = 0;
																}else if(h1.equals("Taxes and Card Fines")){
																	hr1 = 1;
																}else{
																	hr1 = 2;
																}
																if(h2.equals("$200 (Standard)")){
																	hr2 = 0;
																}else if(h2.equals("$300 (1.5x Standard)")){
																	hr2 = 1;
																}else{
																	hr2 = 2;
																}
																if(h3.equals("Nothing (Standard)")){
																	hr3 = 0;
																}else{
																	hr3 = 1;
																}
																if(h4.equals("No Mercy Rule (Standard)")){
																	hr4 = 0;
																}else if(h4.equals("$10,000")){
																	hr4 = 1;
																}else{
																	hr4 = 2;
																}
																if(h5.equals("Never")){
																	hr5 = 0;
																}else{
																	hr5 = 1;
																}
																if(h6.equals("10% or $200")){
																	hr6 = 0;
																}else{
																	hr6 = 1;
																}
																if(h7.equals("None")){
																	hr7 = 0;
																}else if(h7.equals("1")){
																	hr7 = 1;
																}else{
																	hr7 = 2;
																}
																
																CreateAndRunGame c = new CreateAndRunGame(res, playerz, computers, hr1, hr2, hr3, hr4, hr5, hr6, hr7);
																
																b = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else if (game == 1) { // /Load game

				b = false;
			}
		} while (b == true);

	}

}
