package rydgeCrakerStudios.rydgecraft;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Block {
	
	public int x;
	public int y;
	public int z;
	public int size;
	public Texture textureTop;
	public Texture textureBottom;
	public Texture textureSides;
	public String imageLocationTop = "src/rydgeCrakerStudios/rydgecraft/textures/grassTop.png";
	public String imageLocationSide = "src/rydgeCrakerStudios/rydgecraft/textures/grassSide.png";
	public String imageLocationBottom = "src/rydgeCrakerStudios/rydgecraft/textures/grassBottom.png";
	
	public boolean canDrawLineTo = true;
	
	public Block(int x, int y, int z, int size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		init();
	}
	
	public void init(){
		
	}
	
	public void releaseTextures(){
		textureTop.release();
		textureBottom.release();
		textureSides.release();
	}
	
	public void setTextures(Texture t1){
		textureTop = t1;
		textureBottom = t1;
		textureSides = t1;
	}
	
	public void setTextures(Texture topAndBottom, Texture sides){
		textureTop = topAndBottom;
		textureBottom = sides;
		textureSides = topAndBottom;
	}
	
	public void setTextures(Texture top, Texture sides, Texture bottom){
		textureTop = top;
		textureBottom = sides;
		textureSides = bottom;
	}
	
	public void draw(){
		if(canDrawLineTo){
			textureTop.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(1, 1, 1);
			
			textureSides.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(1, 1, 1);
			
			//Front
			
			GL11.glNormal3f(0, 0, z);
			GL11.glTexCoord2f(1, 0);		
			GL11.glVertex3f(x, y, z);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x, y + size, z);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x + size, y + size, z);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x + size, y, z);
			
			//Back
			GL11.glNormal3f(0, 0, z + size);
			GL11.glTexCoord2f(1, 0);		
			GL11.glVertex3f(x, y, z + size);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x, y + size, z + size);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x + size, y + size, z + size);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x + size, y, z + size);
			
			//Left
			GL11.glTexCoord2f(1, 0);
			GL11.glNormal3f(x, 0, 0);
			GL11.glVertex3f(x, y, z);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x, y + size, z);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x, y + size, z + size);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x, y, z + size);
			
			//Right
			GL11.glTexCoord2f(1, 0);
			GL11.glNormal3f(x + size, 0, 0);
			GL11.glVertex3f(x + size, y, z);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x + size, y + size, z);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x + size, y + size, z + size);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x + size, y, z + size);
			
			GL11.glEnd();
			
			//Top
			GL11.glTexCoord2f(1, 0);
			GL11.glNormal3f(0, y + size, 0);
			GL11.glVertex3f(x, y + size, z);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x + size, y + size, z);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x + size, y + size, z + size);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x, y + size, z + size);
			
			GL11.glEnd();
			
			textureBottom.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor3f(1, 1, 1);
			
			GL11.glTexCoord2f(1, 0);
			GL11.glNormal3f(0, y, 0);
			GL11.glVertex3f(x, y, z);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(x + size, y, z);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(x + size, y, z + size);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(x, y, z + size);
			
			GL11.glEnd();
		}
	}
	
	
	

}
