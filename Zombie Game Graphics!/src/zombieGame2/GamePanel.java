package zombieGame2;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel{
	
	private ArrayList<GameComponent> components;
	ArrayList<String> strings;
	int numberOfLines;
	Player play;
	int swit;
	

	public GamePanel(File f){
		components = new ArrayList<GameComponent>();
		strings = new ArrayList<String>();
		
	}
	
	public GamePanel() throws IOException{
		components = new ArrayList<GameComponent>();
		strings = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader("Map/0x0.txt"));
		numberOfLines = 0;
		while (reader.readLine() != null) numberOfLines++;
		reader.close();
		reader = new BufferedReader(new FileReader("Map/0x0.txt"));
		for (int i = 0; i < numberOfLines; i++) {// ///////////////////////////////////////////////////////////
			String s = "";
			s = reader.readLine();
			strings.add(s);	
		}
		reader.close();
		
		
		for (int i = 0; i < strings.size(); i++) {
			String[] array = new String[6];
			array = strings.get(i).split(" ");
			if(array[0].equals("Wall")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Wall w = new Wall(x, y, width, height);
				components.add(w);
			}
			if(array[0].equals("NextFrame")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int frameNumber = Integer.parseInt(array[5]);
				int frameSide = Integer.parseInt(array[6]);
				NextFrame f = new NextFrame(x, y, width, height);
				f.decideFrameNumber(frameNumber, frameSide);
				components.add(f);
			}
			if(array[0].equals("Building")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Building b = new Building(x, y, width, height);
				components.add(b);
			}
			if(array[0].equals("Door")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int dn = Integer.parseInt(array[5]);
				Door d = new Door(x, y, width, height);
				d.decideDoorNumber(dn);
				components.add(d);
			}
			if(array[0].equals("Player")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Player p = new Player(x, y, width, height);
				components.add(p);
			}
			if(array[0].equals("NPC")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int npcNumber = Integer.parseInt(array[5]);
				NPC npc = new NPC(x, y, width, height);
				npc.decideNpcNumber(npcNumber);
				components.add(npc);
			}
			if(array[0].equals("Zombie")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Zombie z = new Zombie(x, y, width, height);
				components.add(z);
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(swit == 1){
			swit = 0;
			components.clear();
			System.out.println(components.size());
			try {
				nextFrame(play);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		for (int i = 0; i < components.size(); i++) {
			components.get(i).draw(g);
		}
	}
	
	public void move(int direction){
		if(direction == 0){
			for(int i = 0; i < components.size(); i++){
				if(components.get(i) instanceof Player){
					Player p = (Player) components.get(i);
					p.updatePosition(0, -3);
				}
			}
		}
		if(direction == 1){
			for(int i = 0; i < components.size(); i++){
				if(components.get(i) instanceof Player){
					Player p = (Player) components.get(i);
					p.updatePosition(0, 3);
				}
			}
		}
		if(direction == 2){
			for(int i = 0; i < components.size(); i++){
				if(components.get(i) instanceof Player){
					Player p = (Player) components.get(i);
					p.updatePosition(-3, 0);
				}
			}
		}
		if(direction == 3){
			for(int i = 0; i < components.size(); i++){
				if(components.get(i) instanceof Player){
					Player p = (Player) components.get(i);
					p.updatePosition(3, 0);
				}
			}
		}
	}
	
	public void collideCheck(){
		for(int i = 0; i < components.size(); i++){
			if(components.get(i) instanceof Player){
				Player p = (Player) components.get(i);
				p.collideCheck(components);
			}
		}
	}
	
	public void checkNextFrame() throws IOException{
		for(int i = 0; i < components.size(); i++){
			if(components.get(i) instanceof Player){
				Player p = (Player) components.get(i);
				if(p.changeFrame == 1){
					play = p;
					swit = 1;
				}
			}
		}
	}
	public void nextFrame(Player p) throws IOException{
		p.changeFrame = 0;
		int frameNumber = p.frameNumber;
		int frameSide = p.frameSide;
		String filepath = "";
		components.clear();
		this.repaint();
		
		if(frameNumber == 0){
			filepath = "Map/0x0.txt";
		}else if(frameNumber == 1){
			filepath = "Map/1x0.txt";
			System.out.println("here");
		}else if(frameNumber == 2){
			filepath = "Map/2x0.txt";
		}else if(frameNumber == 3){
			filepath = "Map/3x0.txt";
		}else if(frameNumber == 4){
			filepath = "Map/4x0.txt";
		}else if(frameNumber == 5){
			filepath = "Map/0x1.txt";
		}else if(frameNumber == 6){
			filepath = "Map/1x1.txt";
		}else if(frameNumber == 7){
			filepath = "Map/2x1.txt";
		}else if(frameNumber == 8){
			filepath = "Map/3x1.txt";
		}else if(frameNumber == 9){
			filepath = "Map/4x1.txt";
		}else if(frameNumber == 10){
			filepath = "Map/0x2.txt";
		}else if(frameNumber == 11){
			filepath = "Map/1x2.txt";
		}else if(frameNumber == 12){
			filepath = "Map/2x2.txt";
		}else if(frameNumber == 13){
			filepath = "Map/3x2.txt";
		}else if(frameNumber == 14){
			filepath = "Map/4x2.txt";
		}else if(frameNumber == 15){
			filepath = "Map/0x3.txt";
		}else if(frameNumber == 16){
			filepath = "Map/1x3.txt";
		}else if(frameNumber == 17){
			filepath = "Map/2x3.txt";
		}else if(frameNumber == 18){
			filepath = "Map/3x3.txt";
		}else if(frameNumber == 19){
			filepath = "Map/4x3.txt";
		}else if(frameNumber == 20){
			filepath = "Map/0x4.txt";
		}else if(frameNumber == 21){
			filepath = "Map/1x4.txt";
		}else if(frameNumber == 22){
			filepath = "Map/2x4.txt";
		}else if(frameNumber == 23){
			filepath = "Map/3x4.txt";
		}else if(frameNumber == 24){
			filepath = "Map/4x4.txt";
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		numberOfLines = 0;
		while (reader.readLine() != null) numberOfLines++;
		reader.close();
		reader = new BufferedReader(new FileReader(filepath));
		for (int i = 0; i < numberOfLines; i++) {   /////////////////////////////////////////////////////////////
			String s = "";
			s = reader.readLine();
			strings.add(s);	
		}
		reader.close();
		
		for (int i = 0; i < strings.size(); i++) {
			String[] array = new String[6];
			array = strings.get(i).split(" ");
			if(array[0].equals("Wall")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Wall w = new Wall(x, y, width, height);
				components.add(w);
			}
			if(array[0].equals("NextFrame")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int tframeNumber = Integer.parseInt(array[5]);
				int tframeSide = Integer.parseInt(array[6]);
				NextFrame f = new NextFrame(x, y, width, height);
				f.decideFrameNumber(tframeNumber, tframeSide);
				components.add(f);
			}
			if(array[0].equals("Building")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Building b = new Building(x, y, width, height);
				components.add(b);
			}
			if(array[0].equals("Door")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int dn = Integer.parseInt(array[5]);
				Door d = new Door(x, y, width, height);
				d.decideDoorNumber(dn);
				components.add(d);
			}
			if(array[0].equals("Player")){
				
			}
			if(array[0].equals("NPC")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				int npcNumber = Integer.parseInt(array[5]);
				NPC npc = new NPC(x, y, width, height);
				npc.decideNpcNumber(npcNumber);
				components.add(npc);
			}
			if(array[0].equals("Zombie")){
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				int width = Integer.parseInt(array[3]);
				int height = Integer.parseInt(array[4]);
				Zombie z = new Zombie(x, y, width, height);
				components.add(z);
			}
		}
		
		
		if(frameSide == 0){
			Player player = new Player(245, 480, 10, 10);
			components.add(player);
		}else if(frameSide == 1){
			Player player = new Player(20, 245, 10, 10);
			components.add(player);
		}else if(frameSide == 2){
			Player player = new Player(245, 20, 10, 10);
			components.add(player);
		}else if(frameSide == 3){
			Player player = new Player(480, 245, 10, 10);
			components.add(player);
		}
	}
}
