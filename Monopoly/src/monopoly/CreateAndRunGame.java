package monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class CreateAndRunGame implements ActionListener, Runnable {
	
	private JFrame frame;
	private JPanel mainPanel;
	private MainBoardPanel mbp;
	
	private JPanel btm;
	private JPanel lft;
	private JPanel tp;
	private JPanel rt;
	
	private int resX;
	private int resY;
	
	private int players;
	private int computers;
	
	private boolean doubles;
	private int diceRoll;
	
	private int hr1;
	private int hr2;
	private int hr3;
	private int hr4;
	private int hr5;
	private int hr6;
	private int hr7;
	
	private GamePiece currentPlayer;
	
	private int timeToRoll = 0;
	
	private JButton roll;
	private JButton trade;
	private JButton property;
	private JButton saveAndQuit;
	
	Thread th;

	public CreateAndRunGame(String resolution, int players, int computers, int hr1, int hr2, int hr3, int hr4, int hr5, int hr6, int hr7){ //New Game
		
		if(resolution.equals("1920x1080")){
			resX = 1920;
			resY = 1030;
		}else{
			String[] temp = resolution.split("x");
			resX = Integer.parseInt(temp[0]);
			resY = Integer.parseInt(temp[1]);
		}
		this.players = players;
		this.computers = computers;
		this.hr1 = hr1;
		this.hr2 = hr2;
		this.hr3 = hr3;
		this.hr4 = hr4;
		this.hr5 = hr5;
		this.hr6 = hr6;
		this.hr7 = hr7;
		
		frame = new JFrame();
		frame.setSize(resX, resY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Monopoly");
		frame.setVisible(true);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints top = new GridBagConstraints();
		top.fill = GridBagConstraints.BOTH;
		top.gridwidth = 4;
		top.gridx = 0;
		top.gridy = 0;
		top.weightx = 1;
		top.weighty = .10;
		top.anchor = GridBagConstraints.NORTH;
		
		GridBagConstraints bl = new GridBagConstraints();
		bl.fill = GridBagConstraints.BOTH;
		bl.gridwidth = 3;
		bl.gridx = 0;
		bl.gridy = 2;
		bl.weightx = .90;
		bl.weighty = .15;
		bl.anchor = GridBagConstraints.SOUTH;
		
		GridBagConstraints br = new GridBagConstraints();
		br.fill = GridBagConstraints.BOTH;
		br.gridwidth = 1;
		br.gridx = 3;
		br.gridy = 2;
		br.weightx = .10;
		br.weighty = .15;
		br.anchor = GridBagConstraints.SOUTH;
		
		GridBagConstraints cent = new GridBagConstraints();
		cent.fill = GridBagConstraints.BOTH;
		cent.gridwidth = 2;
		cent.gridx = 1;
		cent.gridy = 1;
		cent.weightx = .80;
		cent.weighty = .75;
		cent.anchor = GridBagConstraints.CENTER;
		
		GridBagConstraints left = new GridBagConstraints();
		left.fill = GridBagConstraints.BOTH;
		left.gridx = 0;
		left.gridy = 1;
		left.weightx = .1;
		left.weighty = .80;
		left.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints right = new GridBagConstraints();
		right.fill = GridBagConstraints.BOTH;
		right.gridx = 3;
		right.gridy = 1;
		right.weightx = .10;
		right.weighty = .80;
		right.anchor = GridBagConstraints.EAST;
		
		
		tp = new JPanel();
		tp.setBackground(Color.YELLOW);
		lft = new JPanel();
		lft.setBackground(Color.GREEN);
		
		mbp = new MainBoardPanel(resX, resY, this.players, this.computers, frame);
		mbp.setBackground(Color.WHITE);
		
		rt = new JPanel();
		rt.setBackground(Color.BLUE);
		btm = new JPanel();
		btm.setBackground(Color.RED);
		JPanel o = new JPanel();
		o.setBackground(Color.ORANGE);
		
		mainPanel.add(tp, top);
		mainPanel.add(lft, left);
		mainPanel.add(mbp, cent);
		mainPanel.add(rt, right);
		mainPanel.add(btm, bl);
		mainPanel.add(o, br);
		
		int tempw = o.getWidth();
		int temph = o.getHeight();
		
		o.setPreferredSize(new Dimension(tempw, temph));
		o.setMaximumSize(new Dimension(tempw, temph));
		o.setMinimumSize(new Dimension(tempw, temph));
		o.setBackground(Color.BLACK);
		
		roll = new JButton("Roll");
		roll.setActionCommand("r");
		roll.addActionListener(this);
		roll.setEnabled(false);
		trade = new JButton("Trade");
		trade.setActionCommand("t");
		trade.addActionListener(this);
		property = new JButton("Property");
		property.setActionCommand("p");
		property.addActionListener(this);
		saveAndQuit = new JButton("Save/Quit");
		saveAndQuit.setActionCommand("S+Q");
		saveAndQuit.addActionListener(this);
		
		if(resX == 1920){
			roll.setPreferredSize(new Dimension(150, 30));
			trade.setPreferredSize(new Dimension(150, 30));
			property.setPreferredSize(new Dimension(150, 30));
			saveAndQuit.setPreferredSize(new Dimension(150, 30));
		}else if(resX == 1280){
			roll.setPreferredSize(new Dimension(100, 30));
			trade.setPreferredSize(new Dimension(100, 30));
			property.setPreferredSize(new Dimension(100, 30));
			saveAndQuit.setPreferredSize(new Dimension(100, 30));
		}else if(resX == 1024){
			roll.setPreferredSize(new Dimension(100, 20));
			trade.setPreferredSize(new Dimension(100, 20));
			property.setPreferredSize(new Dimension(100, 20));
			saveAndQuit.setPreferredSize(new Dimension(100, 20));
		}else{
			roll.setPreferredSize(new Dimension(85, 15));
			trade.setPreferredSize(new Dimension(85, 15));
			property.setPreferredSize(new Dimension(85, 15));
			saveAndQuit.setPreferredSize(new Dimension(88, 15));
		}
		
		o.add(roll);
		o.add(trade);
		o.add(property);
		o.add(saveAndQuit);
		
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		Thread th = new Thread(this);
		th.start();
		roll.setEnabled(true);
	}
	
	public CreateAndRunGame(String filePath){ //Load Game
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("r")){
			if(timeToRoll == 1){
				synchronized (this) {
					diceRoll = mbp.rollDice();
					doubles = mbp.checkForDoubles();
					this.notify();
					roll.setEnabled(false);
				}
			}
		}else if(e.getActionCommand().equals("t")){
			
		}else if(e.getActionCommand().equals("p")){
			
		}else if(e.getActionCommand().equals("S+Q")){
			Object[] sqOptions = new Object[]{"Save and Quit", "Just Save", "Just Quit", "Cancel"};
			int sqo = JOptionPane.showOptionDialog(null, "What would you like to do?", "Save and Quit?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, sqOptions, null);
			if(sqo != 3 && sqo != 1){
				if(sqo == 0){
					
				}else if(sqo == 1){
					
				}else if(sqo == 2){
					System.exit(0);
				}
			}
		}else{
			
		}
	}

	public void run() {
		ArrayList<Player> players = mbp.getPlayers();
		ArrayList<Computer> computers = mbp.getComputers();
		ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
		for(int i = 0; i < players.size(); i++){
			gamePieces.add(players.get(i));
		}
		for(int i = 0; i < computers.size(); i++){
			gamePieces.add(computers.get(i));
		}
		do{
			for(int i = 0; i < gamePieces.size(); i++){
				currentPlayer = gamePieces.get(i);
				for(int jj = 0; jj < gamePieces.size(); jj++){
					if(gamePieces.size() == 2){
						lft.setBackground(Color.BLACK);
						tp.setBackground(Color.BLACK);
						btm.setBackground(currentPlayer.getColor());
						if(jj == 0){
							if(gamePieces.get(jj + 1).equals(currentPlayer)){
								
							}else{
								rt.setBackground(gamePieces.get(jj + 1).getColor());
							}
						}else{
							if(gamePieces.get(jj - 1).equals(currentPlayer)){
								
							}else{
								rt.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}
					}else if(gamePieces.size() == 3){
						lft.setBackground(Color.BLACK);
						btm.setBackground(currentPlayer.getColor());
						if(jj == 0){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj + 1).getColor());
								tp.setBackground(gamePieces.get(jj + 2).getColor());
							}
								
						}else if(jj == 1){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj + 1).getColor());
								tp.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}else{
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj - 2).getColor());
								tp.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}
						
					}else if(gamePieces.size() == 4){
						btm.setBackground(currentPlayer.getColor());
						if(jj == 0){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj + 1).getColor());
								tp.setBackground(gamePieces.get(jj + 2).getColor());
								lft.setBackground(gamePieces.get(jj + 3).getColor());
							}
						}else if(jj == 1){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj + 1).getColor());
								tp.setBackground(gamePieces.get(jj + 2).getColor());
								lft.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}else if(jj == 2){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj + 1).getColor());
								tp.setBackground(gamePieces.get(jj - 2).getColor());
								lft.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}else if(jj == 3){
							if(gamePieces.get(jj).equals(currentPlayer)){
								rt.setBackground(gamePieces.get(jj - 3).getColor());
								tp.setBackground(gamePieces.get(jj - 2).getColor());
								lft.setBackground(gamePieces.get(jj - 1).getColor());
							}
						}
					}
				}
				if(gamePieces.get(i) instanceof Player){
					roll.setEnabled(true);
					synchronized (this) {
						try {
							timeToRoll = 1;
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					timeToRoll = 0;
					mbp.move(currentPlayer, diceRoll);
					mbp.turnOperations(currentPlayer, this);
				}else{
					roll.setEnabled(true);
					timeToRoll = 1;
					roll.doClick();
					timeToRoll = 0;
					mbp.move(currentPlayer, diceRoll);
					mbp.turnOperations(currentPlayer, this);
				}
				
			}	
		}while(true);	
	}
	
	public int getHr1(){
		return hr1;
	}
	public int getHr2(){
		return hr2;
	}
	public int getHr3(){
		return hr3;
	}
	public int getHr4(){
		return hr4;
	}
	public int getHr5(){
		return hr5;
	}
	public int getHr6(){
		return hr6;
	}
	public int getHr7(){
		return hr7;
	}

}
