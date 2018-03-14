package gt;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	public static Render floor = loadBitmap("/resources/grass.png");
	private static Game game;
	
	public Texture(Game game){
		Texture.setGame(game);
	}
	
	private static Render loadBitmap(String filename) {
		
		BufferedImage img;
		
		try {
			img = ImageIO.read(Texture.class.getResource(filename));
			int width = img.getWidth();
			int height = img.getHeight();
			Render result = new Render(width, height, getGame());
			img.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
			
		} catch (IOException e) {
			System.out.println("Crashed Loading Image");
			throw new RuntimeException (e);
		}
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Texture.game = game;
	}
	
}
