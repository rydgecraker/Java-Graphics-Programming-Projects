package testingPackage;

public class Point3d {
	
	private int x;
	private int y;
	private int z;

	public Point3d(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
	
	public void setZ(int z){
		this.z = z;
	}
	
	public int getZ(){
		return z;
	}
	
	public String toString(){
		String s = "X=" + x + " Y=" + y + " Z=" + z;
		return s;
	}
	
}
