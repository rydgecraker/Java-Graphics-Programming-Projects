package gt;

public class Render {

	private int width;
	private int height;
	public int[] pixels;
	
	public Game game;

	public Render(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.game = game;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void changeSize(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void draw(Render render, int xOffset, int yOffset) {
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffset;
			
			if(yPix < 0 || yPix >= game.cpHeight){
				continue; //goes to next loop ideration
			}
			
			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffset;
				
				if(xPix < 0 || xPix >= game.cpWidth){
					continue; //goes to next loop ideration
				}
				
				int alpha = render.pixels[(y * render.width) + x];
				if(alpha > 0){
					pixels[(yPix * width) + xPix] = alpha;
				}
			}
		}
	}

}
