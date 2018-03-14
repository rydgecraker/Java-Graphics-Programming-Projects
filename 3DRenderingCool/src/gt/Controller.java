package gt;

public class Controller {

	public double x, z, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;
	
	public Controller(){
		
	}
	
	public void tick(boolean forward, boolean back, boolean left, boolean right){
		double rotationSpeed = .025;
		double walkSpeed = 1.0;
		double xMove = 0.0;
		double zMove = 0.0;
		
		if(forward){
			zMove ++;
		}
		if(back){
			zMove --;
		}
		if(left){
			xMove --;
		}
		if(right){
			xMove ++;
		}
		if(turnLeft){
			rotationa -= rotationSpeed;
		}
		if(turnRight){
			rotationa += rotationSpeed;
		}
		
		xa = (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		za = (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
		
		x += xa;
		z += za;
		
		xa *= .1;
		za *= .1;
		
		rotation+= rotationa;
		rotationa *= .5;
		
	}
	
}
