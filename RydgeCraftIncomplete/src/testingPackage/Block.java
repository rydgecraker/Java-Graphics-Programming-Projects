package testingPackage;

import java.awt.*;

public class Block extends GameObject {

	public Block(int x, int y, int width, int height, int z, int depth, Color bg,Color outline, Color highlight) {
		super(x, y, width, height, z, depth, bg, outline, highlight);
	}

	protected void drawShape(Graphics2D g) {
		if (draw) {
			figureAndDrawPolygons(g);
			if (selected) {
				
			}
		}
	}
	
	public void figureAndDrawPolygons(Graphics2D g){
		//In future g.setClip() for images will work.
		
		g.setColor(bg);
		
		if(playerLocationX < x){
			//player is to the left
			if(playerLocationY > (y + (height))){
				//player is to the left and below
				if(playerLocationZ < z){
					//player is to the left, below, in front
					//left, bottom, front
					g.fillPolygon(left);
					g.fillPolygon(bottom);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(bottom);
					g.drawPolygon(front);
					
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//player is to the left,  below
					//left, bottom
					g.fillPolygon(left);
					g.fillPolygon(bottom);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(bottom);
					
				}else{
					//player is to the left, below, behind
					//left, bottom, back
					g.fillPolygon(left);
					g.fillPolygon(bottom);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(bottom);
					g.drawPolygon(back);
				}
			}else if(playerLocationY <= y + height && playerLocationY >= y){
				//Player is to the left
				if(playerLocationZ < z){
					//player is to the left and in front
					//left, front
					g.fillPolygon(left);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//player is to the left
					//left
					g.fillPolygon(left);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
				}else{
					//player is to the left, behind
					//left, back
					g.fillPolygon(left);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(back);
				}
			}else{
				//Player is to the left and above
				if(playerLocationZ < z){
					//player is to the left, above, in front
					//left, top, front
					g.fillPolygon(left);
					g.fillPolygon(top);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(top);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//player is to the left,  above
					//left, top
					g.fillPolygon(left);
					g.fillPolygon(top);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(top);
				}else{
					//player is to the left, above, behind
					//left, top, back
					g.fillPolygon(left);
					g.fillPolygon(top);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(top);
					g.drawPolygon(back);
				}
			}
		}else if(playerLocationX >= x && playerLocationX <= x + width){
			//Center
			if(playerLocationY > (y + (height))){
				//below
				if(playerLocationZ < z){
					//below, in front
					//bottom, front
					g.fillPolygon(bottom);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(bottom);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					// below
					// bottom
					g.fillPolygon(bottom);
					
					g.setColor(outline);
					
					g.drawPolygon(bottom);
				}else{
					//below, behind
					//bottom, back
					g.fillPolygon(bottom);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(bottom);
					g.drawPolygon(back);
				}
			}else if(playerLocationY <= y + height && playerLocationY >= y){
				//Center Center
				if(playerLocationZ < z){
					//front
					//front
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//front back left right up down
					//front back left right up down
					//Player is inside block
					g.fillPolygon(left);
					g.fillPolygon(bottom);
					g.fillPolygon(front);
					g.fillPolygon(right);
					g.fillPolygon(top);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(bottom);
					g.drawPolygon(front);					
					g.drawPolygon(right);
					g.drawPolygon(top);
					g.drawPolygon(back);
				}else{
					// behind
					// back
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(back);
				}
			}else{
				//above
				if(playerLocationZ < z){
					//above, in front
					//top, front
					g.fillPolygon(top);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(top);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//above
					//top
					g.fillPolygon(top);
					
					g.setColor(outline);

					g.drawPolygon(top);
				}else{
					// above, behind
					//top, back
					g.fillPolygon(top);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(top);
					g.drawPolygon(back);
				}
			}
		}else{
			//right 
			if(playerLocationY > (y + (height))){
				//Right, below
				if(playerLocationZ < z){
					//Right, below, in front
					//Right, bottom, front
					g.fillPolygon(right);
					g.fillPolygon(bottom);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(bottom);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					// Right, below
					// Right, bottom
					g.fillPolygon(right);
					g.fillPolygon(bottom);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(bottom);
				}else{
					//Right, below, behind
					//Right, bottom, back
					g.fillPolygon(left);
					g.fillPolygon(bottom);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(left);
					g.drawPolygon(bottom);
					g.drawPolygon(back);
				}
			}else if(playerLocationY <= y + height && playerLocationY >= y){
				//Right Center
				if(playerLocationZ < z){
					//Right, Front
					//Right, Front
					g.fillPolygon(right);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//Right
					//Right
					g.fillPolygon(right);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
				}else{
					// Right, Behind
					// Right, Back
					g.fillPolygon(right);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(back);
				}
			}else{
				//Right, above
				if(playerLocationZ < z){
					//Right, above, in front
					//Right, top, front
					g.fillPolygon(right);
					g.fillPolygon(top);
					g.fillPolygon(front);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(top);
					g.drawPolygon(front);
				}else if(playerLocationZ >= z && playerLocationZ <= z + depth){
					//Right, above
					//Right, top
					g.fillPolygon(right);
					g.fillPolygon(top);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(top);
				}else{
					//Right, above, behind
					//Right, top, back
					g.fillPolygon(right);
					g.fillPolygon(top);
					g.fillPolygon(back);
					
					g.setColor(outline);
					
					g.drawPolygon(right);
					g.drawPolygon(top);
					g.drawPolygon(back);
				}
			}
		}
	}

}
