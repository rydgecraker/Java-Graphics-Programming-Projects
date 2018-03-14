package quest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class QuadTree implements Runnable{

	class Node {
		
		public Node nw;
		public Node ne;
		public Node sw;
		public Node se;
		public Rectangle area;
		public int x;
		public int y;
		public int relX;
		public int relY;
		public ArrayList<GameObject> gameObjects;
		
		public Node(Rectangle area){
			this.area = area;
			this.nw = null;
			this.ne = null;
			this.sw = null;
			this.se = null;
			this.x = (int) area.getX();
			this.y = (int) area.getY();
			this.relX = (int) area.getX();
			this.relY = (int) area.getY();
			gameObjects = new ArrayList<GameObject>();
		}
		
	}
	
	private Node root;
	private int maxNumOfObjects;
	public ArrayList<GameObject> allObjects;
	
	
	public QuadTree(int sizeX, int sizeY, int maxNumOfObjects){
		this.maxNumOfObjects = maxNumOfObjects; //Max number of objects in a node
		root = new Node(new Rectangle(0, 0, sizeX, sizeY));
		allObjects = new ArrayList<GameObject>();
		
	}
	
	public void balance(){
		//Balances the tree so that the nodes have the correct number of objects in them, and splits nodes accordingly
		Thread th = new Thread(this);
		th.start();
	}
	
	public void balanceRec(Node current){
		if(current != null){
			if(current.gameObjects.size() > maxNumOfObjects){
				if(current.nw == null){
					int newX = current.area.x;
					int newY = current.area.y;
					int newWidth = (current.area.width / 2);
					int newHeight = (current.area.height / 2);
					
					//makes the new nodes for the tree
					Rectangle nw = new Rectangle(newX, newY, newWidth, newHeight);
					Rectangle ne = new Rectangle((newX + newWidth), newY, newWidth, newHeight);
					Rectangle se = new Rectangle((newX + newWidth), (newY + newHeight), newWidth, newHeight);
					Rectangle sw = new Rectangle(newX, (newY + newHeight), newWidth, newHeight);
					
					current.nw = new Node(nw);
					current.ne = new Node(ne);
					current.se = new Node(se);
					current.sw = new Node(sw);
				}
				for(int i = 0; i < current.gameObjects.size(); i++){
					if(current.gameObjects.get(i).getAbsoluteLocation().intersects(current.nw.area)){
						current.nw.gameObjects.add(current.gameObjects.get(i));
					}else
					if(current.gameObjects.get(i).getAbsoluteLocation().intersects(current.ne.area)){
						current.ne.gameObjects.add(current.gameObjects.get(i));
					}else
					if(current.gameObjects.get(i).getAbsoluteLocation().intersects(current.sw.area)){
						current.sw.gameObjects.add(current.gameObjects.get(i));
					}else{
						//if(current.gameObjects.get(i).getAbsoluteLocation().intersects(current.se.area))
						current.se.gameObjects.add(current.gameObjects.get(i));
					}
				}
				current.gameObjects.clear();
				
				balanceRec(current.nw);
				balanceRec(current.ne);
				balanceRec(current.sw);
				balanceRec(current.se);
				
			}
		}
	}
	
	public void addGameObject(GameObject go){
		allObjects.add(go);
		addGameObjectRec(go, root);
		balance();
	}
	
	public void addGameObjectRec(GameObject go, Node current){
		if(current != null){
			if(current.area.intersects(go.getAbsoluteLocation())){
				if(current.nw != null){
					addGameObjectRec(go, current.ne);
					addGameObjectRec(go, current.nw);
					addGameObjectRec(go, current.se);
					addGameObjectRec(go, current.sw);
				}else{
					current.gameObjects.add(go);
				}
			}
		}
	}
	
	public void draw(Graphics g, Player p){
		drawRec(g, root, p);
	}
	
	public void drawRec(Graphics g, Node current, Player p){
		if(current != null && p.getViewArea().intersects(current.area)){
			for(int i = 0; i < current.gameObjects.size(); i++){
				current.gameObjects.get(i).draw(g);
			}
			drawRec(g, current.ne, p);
			drawRec(g, current.nw, p);
			drawRec(g, current.se, p);
			drawRec(g, current.sw, p);
		}
		if(current != null){
			g.setColor(Color.MAGENTA);
			g.drawRect(current.relX, current.relY, current.area.width, current.area.height);
		}
	}
	
	public void moveRelativeX(int speed){
		moveRelativeXRec(speed, root);
		allowMovement();
	}
	public void moveRelativeXRec(int speed, Node current){
		if(current != null){
			for(int i = 0; i < current.gameObjects.size(); i++){
				if(current.gameObjects.get(i).hasBeenMoved == false){
					current.gameObjects.get(i).relX += speed;
					current.gameObjects.get(i).hasBeenMoved = true;
				}	
			}
			current.relX += speed;
			
			moveRelativeXRec(speed, current.nw);
			moveRelativeXRec(speed, current.ne);
			moveRelativeXRec(speed, current.sw);
			moveRelativeXRec(speed, current.se);
		}
	}
	
	public void moveRelativeY(int speed){
		moveRelativeYRec(speed, root);
		allowMovement();
	}
	public void moveRelativeYRec(int speed, Node current){
		if(current != null){
			for(int i = 0; i < current.gameObjects.size(); i++){
				if(current.gameObjects.get(i).hasBeenMoved == false){
					current.gameObjects.get(i).relY += speed;
					current.gameObjects.get(i).hasBeenMoved = true;
				}
				
			}
			current.relY += speed;
			
			moveRelativeYRec(speed, current.nw);
			moveRelativeYRec(speed, current.ne);
			moveRelativeYRec(speed, current.sw);
			moveRelativeYRec(speed, current.se);
		}
	}
	
	public void moveRelativeXandY(int speedX, int speedY){
		moveRelativeXandYRec(speedX, speedY, root);
		allowMovement();
	}
	public void moveRelativeXandYRec(int speedX, int speedY, Node current){
		if(current != null){
			for(int i = 0; i < current.gameObjects.size(); i++){
				if(current.gameObjects.get(i).hasBeenMoved == false){
					current.gameObjects.get(i).relY += speedY;
					current.gameObjects.get(i).relX += speedX;
					current.gameObjects.get(i).hasBeenMoved = true;
				}
				
			}
			current.relY += speedY;
			current.relX += speedX;
			
			moveRelativeXandYRec(speedX, speedY, current.nw);
			moveRelativeXandYRec(speedX, speedY, current.ne);
			moveRelativeXandYRec(speedX, speedY, current.sw);
			moveRelativeXandYRec(speedX, speedY, current.se);
		}
	}
	
	public void allowMovement(){
		allowMovementRec(root);
	}
	
	public void allowMovementRec(Node current){
		if(current != null){
			for(int i = 0; i < current.gameObjects.size(); i++){
				current.gameObjects.get(i).hasBeenMoved = false;
			}
			
			allowMovementRec(current.nw);
			allowMovementRec(current.ne);
			allowMovementRec(current.sw);
			allowMovementRec(current.se);
		}
	}

	public synchronized void run() {
		balanceRec(root);
	}
	
	public void checkCol(Player p){
		checkColRec(p, root);
	}
	
	public void checkColRec(Player p, Node current){
		if(current != null && p.getViewArea().intersects(current.area)){
			for(int i = 0; i < current.gameObjects.size(); i++){
				if(current.gameObjects.get(i).solid()){
					if(p.getRelArea().intersects(new Rectangle(current.gameObjects.get(i).x, current.gameObjects.get(i).y, 10, current.gameObjects.get(i).height))){ //Left Side
						p.canMoveRight = false;
//						System.out.println("Colide left");
					}else if(p.getRelArea().intersects(new Rectangle((current.gameObjects.get(i).x + current.gameObjects.get(i).width) - 10, current.gameObjects.get(i).y, 10, current.gameObjects.get(i).height))){ //Right side
						p.canMoveLeft = false;
//						System.out.println("Colide right");
					}else if(p.getRelArea().intersects(new Rectangle(current.gameObjects.get(i).x, current.gameObjects.get(i).y, current.gameObjects.get(i).width, 10))){ //Top
						p.canMoveDown = false;
//						System.out.println("Colide top");
					}else if(p.getRelArea().intersects(new Rectangle(current.gameObjects.get(i).x, (current.gameObjects.get(i).y + current.gameObjects.get(i).height) - 10, current.gameObjects.get(i).width, 10))){ //Bottom
						p.canMoveUp = false;
//						System.out.println("Colide bottom");
					}else{
						p.canMove();
					}
				}
			}
			
			checkColRec(p, current.nw);
			checkColRec(p, current.ne);
			checkColRec(p, current.sw);
			checkColRec(p, current.se);
		}
	}
	
	public void animate(Player p){
		animateRec(root, p);
	}
	
	public void animateRec(Node current, Player p){
		if(current != null && p.getViewArea().intersects(current.area)){
			for(int i = 0; i < current.gameObjects.size(); i++){
				if(current.gameObjects.get(i) instanceof Water){
					Water w = (Water) current.gameObjects.get(i);
					w.changeImage();
				}
			}
			animateRec(current.ne, p);
			animateRec(current.nw, p);
			animateRec(current.se, p);
			animateRec(current.sw, p);
		}
		
	}
	
}
