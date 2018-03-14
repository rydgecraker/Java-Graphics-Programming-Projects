package gt;

import java.util.Random;

public class Screen extends Render {

	public Render render;
	public int rw = 500;
	public int rh = 500;
	
	private Render3D render3d;

	public Screen(int width, int height, Game game) {
		super(width, height, game);
		render = new Render(rw, rh, game);
		render3d = new Render3D(width, height, game);
		Random r = new Random();
		for (int i = 0; i < rw * rh; i++) {
			render.pixels[i] = r.nextInt();
		}
	}

	public void render(int xOffset, int yOffset, GameTime gt) {
		for (int i = 0; i < this.getWidth() * this.getHeight(); i++) {
			this.pixels[i] = 0;
		}
		
		render3d.floor(gt);
		render3d.renderDistanceLimiter();
		draw(render3d, 0, 0);
	}

}
