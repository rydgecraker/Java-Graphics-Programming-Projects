package rydgeCrakerStudios.rydgecraft;

import java.awt.Point;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Crosshair {
	
	public int seperation;
	public Point centerPoint;
	public int distance;
	
	public static boolean gl3d = false;
	public static String image = "src/rydgeCrakerStudios/rydgecraft/textures/crosshair.png";
	
	private Texture texture;
	
	public Crosshair(Point centerPoint, int seperation, int distance) {
		this.centerPoint = centerPoint;
		this.seperation = seperation;
		this.distance = distance;
	}
	
	public void releaseTexture(){
		texture.release();
	}

	public void setTexture(Texture t){
		texture = t;
	}
	
	public void draw(){
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1f, 1f, 1f);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(centerPoint.x - distance, centerPoint.y - seperation);
		GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(centerPoint.x - distance, centerPoint.y + seperation);
	    GL11.glTexCoord2f(0, 1);
	    GL11.glVertex2f(centerPoint.x + distance, centerPoint.y + seperation);
	    GL11.glTexCoord2f(1, 1);
	    GL11.glVertex2f(centerPoint.x + distance, centerPoint.y - seperation);
	    
	    GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(centerPoint.x - seperation, centerPoint.y - distance);
		GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(centerPoint.x - seperation, centerPoint.y + distance);
	    GL11.glTexCoord2f(0, 1);
	    GL11.glVertex2f(centerPoint.x + seperation, centerPoint.y + distance);
	    GL11.glTexCoord2f(1, 1);
	    GL11.glVertex2f(centerPoint.x + seperation, centerPoint.y - distance);
		
		GL11.glEnd();
	}

}
