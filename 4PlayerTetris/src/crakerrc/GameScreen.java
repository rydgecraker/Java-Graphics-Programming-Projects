package crakerrc;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import ch.aplu.xboxcontroller.XboxController;

public class GameScreen extends JPanel{
	
	public int x;
	public int y;
	public int width;
	public int height;
	public Color bgColor;
	public int numPlayers;
	
	public int score = 0;
	
	public boolean playWithoutMe = false;
	
	public Game game;
	
	public int level = 1;
	
	public int scorex;
	public int scorey;
	
	public int score1;
	public int score2;
	public int score3;
	public int score4;
	
	public int widthBetweenScreens;
	public int heightBetweenScreens;
	
	public ArrayList<Grid> playerGrids;
	public ArrayList<Player> players;
	
	public int fps;
	public Font fpsFont = new Font("Arial", Font.BOLD, 30);
	public Color fpsColor = Color.YELLOW;
	public int fpsx;
	public int fpsy;
	
	public boolean noController;

	public GameScreen(Color bgColor, int numPlayers, Game game, boolean noController){
		super();
		this.bgColor = bgColor;
		this.numPlayers = numPlayers;
		this.noController = noController;
		this.game = game;
		init();
		createPlayer();
	}
	
	public void setWidthAndHeight(int width, int height, XboxController[] xc){
		this.width = width;
		this.height = height;
		fpsx = (int) (width * .025);
		fpsy = (int) (height * .025);
		scorex = (int) (width * .5);
		scorey = (int) (height * .025);
		
		init();
		createPlayers(xc);
	}
	
	public void createPlayer(){
		//heightBetweenScreens = (int) (height * .05);
		//widthBetweenScreens = (int) (width * .05);
		//int gridWidth = (width - (widthBetweenScreens * (1 + numPlayers))) / numPlayers;
		//Grid grd = new Grid(x + (widthBetweenScreens * (1)), y + heightBetweenScreens, gridWidth, height - (heightBetweenScreens + heightBetweenScreens), Color.RED, 0);
		//playerGrids.add(grd);
		//players.add(new Player(this, 0, playerGrids.get(0)));
	}
	
	public void createPlayers(XboxController[] xc){
		heightBetweenScreens = (int) (height * .05);
		widthBetweenScreens = (int) (width * .05);
		int gridWidth = (width - (widthBetweenScreens * (1 + numPlayers))) / numPlayers;
		Color tempColor;
		for(int i = 0; i < numPlayers; i++){
			if(i == 0){
				tempColor = Color.RED;
			}else if(i == 1){
				tempColor = Color.GREEN;
			}else if(i == 2){
				tempColor = Color.BLUE;
			}else{
				tempColor = Color.YELLOW;
			}
			Grid grd = new Grid(x + (widthBetweenScreens * (i + 1)) + (gridWidth * i), y + heightBetweenScreens, gridWidth, height - (heightBetweenScreens + heightBetweenScreens), tempColor, i);
			playerGrids.add(grd);
			players.add(new Player(xc[i], this, i, playerGrids.get(i)));
		}
	}
	
	public void init(){
		x = 0;
		y = 0;
		playerGrids = new ArrayList<Grid>();
		players = new ArrayList<Player>();
	}
	
	public void doPlayerInput(){
		for(int i = 0; i < players.size(); i++){
			players.get(i).playerInput();
		}
	}
	
	public void paintComponent(Graphics gra){
		super.paintComponent(gra);
		Graphics2D g = (Graphics2D) gra;
		g.setColor(bgColor);
		g.fillRect(x, y, width, height);
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).xc.isConnected()){
				players.get(i).grid.draw(g, players.get(i).col, players.get(i).offset);
			}else{
				players.get(i).grid.draw(g, Color.BLACK, 0);
			}
		}
		for(int i = 0; i < playerGrids.size(); i++){
			playerGrids.get(i).draw(g, Color.BLACK, 0);
		}
		g.setFont(fpsFont);
		g.setColor(fpsColor);
		g.drawString(fps + "", fpsx, fpsy);
		g.setColor(Color.WHITE);
		FontMetrics fm = g.getFontMetrics();
		int wid = fm.stringWidth("Score: " + score + " Level: " + level);
		scorex = ((int) (width * .5)) - (wid / 2);
		g.drawString("Score: " + score + " Level: " + level, scorex, scorey);
	}

	public void changeTime(float time, int playerNum) {
		playerNum++;
		if(playerNum == 1){
			game.gameEventsMulti1 = time;
		}else if(playerNum == 2){
			game.gameEventsMulti2 = time;
		}else if(playerNum == 3){
			game.gameEventsMulti3 = time;
		}else if(playerNum == 4){
			game.gameEventsMulti4 = time;
		}
	}

	public Grid getGridToLeft(int gridNum) {
		int pn = gridNum;
		if(pn == 0){
			pn = (players.size() - 1);
		}else{
			pn -= 1;
		}
		return playerGrids.get(pn);
	}

	public Grid getGridToRight(int gridNum) {
		int pn = gridNum;
		if((pn + 1) >= players.size()){
			pn = 0;
		}else{
			pn += 1;
		}
		return playerGrids.get(pn);
	}
	
	public void doEventsWithoutMe(int[] nums){
		for(int i = 0; i < playerGrids.size(); i++){		
			if((players.get(i).xc.isConnected() && i != nums[0] && i != nums[1] && i != nums[2] && i != nums[3]) || (!players.get(i).xc.isConnected())){
				int tmp = i - 1;
				int counter = 0;
				while(tmp >= 0){
					if(i == nums[tmp]){
						counter++;
					}
					tmp--;
				}
				
				if(counter == 0){
					playerGrids.get(i).doEvents();
					playerGrids.get(i).checkForClear();
				}
			}
		}
	}

	public void doEvents(int playerNum) {
		if(players.get(playerNum).xc.isConnected()){
			players.get(playerNum).grid.doEvents();
			players.get(playerNum).grid.checkForClear();
		}
	}
	
	public void checkForScores(){
		score1 = playerGrids.get(0).getScore();
		score2 = playerGrids.get(1).getScore();
		score3 = playerGrids.get(2).getScore();
		score4 = playerGrids.get(3).getScore();
		
		score = score1 + score2 + score3 + score4;
		
		if(score >= 2000 && level < 2){
			level = 2;
			playerGrids.get(0).setLevel(2);
			playerGrids.get(1).setLevel(2);
			playerGrids.get(2).setLevel(2);
			playerGrids.get(3).setLevel(2);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 4000 && level < 3){
			level = 3;
			playerGrids.get(0).setLevel(3);
			playerGrids.get(1).setLevel(3);
			playerGrids.get(2).setLevel(3);
			playerGrids.get(3).setLevel(3);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 8000 && level < 4){
			level = 4;
			playerGrids.get(0).setLevel(4);
			playerGrids.get(1).setLevel(4);
			playerGrids.get(2).setLevel(4);
			playerGrids.get(3).setLevel(4);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 16000 && level < 5){
			level = 5;
			playerGrids.get(0).setLevel(5);
			playerGrids.get(1).setLevel(5);
			playerGrids.get(2).setLevel(5);
			playerGrids.get(3).setLevel(5);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 32000 && level < 6){
			level = 6;
			playerGrids.get(0).setLevel(6);
			playerGrids.get(1).setLevel(6);
			playerGrids.get(2).setLevel(6);
			playerGrids.get(3).setLevel(6);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 64000 && level < 7){
			level = 7;
			playerGrids.get(0).setLevel(7);
			playerGrids.get(1).setLevel(7);
			playerGrids.get(2).setLevel(7);
			playerGrids.get(3).setLevel(7);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 128000 && level < 8){
			level = 8;
			playerGrids.get(0).setLevel(8);
			playerGrids.get(1).setLevel(8);
			playerGrids.get(2).setLevel(8);
			playerGrids.get(3).setLevel(8);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 256000 && level < 9){
			level = 9;
			playerGrids.get(0).setLevel(9);
			playerGrids.get(1).setLevel(9);
			playerGrids.get(2).setLevel(9);
			playerGrids.get(3).setLevel(9);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		if(score >= 512000 && level < 10){
			level = 10;
			playerGrids.get(0).setLevel(10);
			playerGrids.get(1).setLevel(10);
			playerGrids.get(2).setLevel(10);
			playerGrids.get(3).setLevel(10);
			game.gameEventsTime1 -= 100;
			game.gameEventsTime2 -= 100;
			game.gameEventsTime3 -= 100;
			game.gameEventsTime4 -= 100;
			game.gameEventsTime5 -= 100;
		}
		
	}
	
	public boolean checkForGO(){
		int counter = 0;
		for(int i = 0; i < playerGrids.size(); i++){
			if(playerGrids.get(i).checkForDeath()){
				counter++;
			}
		}
		if(counter > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
}
