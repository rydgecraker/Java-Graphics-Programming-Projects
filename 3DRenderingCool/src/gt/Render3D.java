package gt;

public class Render3D extends Render{
	
	public double renderDistance = 5000;
	public double[] zbuffer;

	public Render3D(int width, int height, Game game) {
		super(width, height, game);
		zbuffer = new double[this.getWidth() * this.getHeight()];
	}
	
	public void floor(GameTime gt){
		
		double rotation = gt.controller.rotation;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);
		
		double celingHeight = 10;
		double floorHeight = 10;
		
		double forward = gt.controller.z;
		double right = gt.controller.x;
		
		for(int y = 0; y < this.getHeight(); y++){
			double top = (y - this.getHeight() / 2.0) / this.getHeight();	
			
			double z = floorHeight / top;
			
			if(top < 0){
				z = celingHeight / -top;
			}
			
			
			for(int x = 0; x < this.getWidth(); x++){
				double xDepth = (x - this.getWidth() / 2.0) / this.getHeight();
				xDepth *= z;
				double xx = (xDepth * cosine) + (z * sine) + right;	
				double yy = (z * cosine) - (xDepth * sine) + forward; 
				
				int xPix = (int) xx;
				int yPix = (int) yy;
				
				zbuffer[(y * this.getWidth()) + x] = z;
				
				pixels[(y * this.getWidth()) + x] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
			}
		}
	}
	
	public void renderDistanceLimiter(){
		
		for(int i = 0; i < this.getWidth() * this.getHeight(); i++){
			int color = pixels[i];
			int brightness = (int) ((renderDistance / zbuffer[i]));
			
			if(brightness < 0){
				brightness = 0;
			}
			if(brightness > 255){
				brightness = 255;
			}
			
			int r = (color >> 16) & 0xff;
			int g = (color >> 8) & 0xff;
			int b = (color) & 0xff;
			
			r = (r * brightness) / 255;
			g = (g * brightness) / 255;
			b = (b * brightness) / 255;
			
			pixels[i] = r << 16 | g << 8 | b;
			
		}
		
	}

}
