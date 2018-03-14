package gt;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class Game implements Runnable {

	public int cpWidth = 1280;
	public int cpHeight = 720;

	private JFrame frame;
	private CanvasPanel cp;

	private Thread gameThread;
	private boolean gameRunning;

	private Screen screen;
	private BufferedImage img;
	private int[] pixels;

	private int magnification;

	private int magX;
	private int magY;

	private int fps;
	private GameTime gt;

	private InputHandler input;
	private int newMouseX = 0;
	private int newMouseY = 0;
	private int oldMouseX = 0;
	private int oldMouseY = 0;
	private Robot robot;

	public Game(String title) {
		frameInit(title);
		threadInit();
		otherInit();
		inputInit();
		startGame();
	}

	public void frameInit(String title) {
		cp = new CanvasPanel(cpWidth, cpHeight);

		frame = new JFrame();

		frame.add(cp);
		frame.setSize(cpWidth, cpHeight);
		frame.setTitle(title);
		// frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		cp.requestFocus();
		BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank");
		frame.getContentPane().setCursor(blank);
	}

	public void threadInit() {
		gameRunning = false;
		gameThread = new Thread(this);
	}

	public void otherInit() {
		screen = new Screen(cpWidth, cpHeight, this);
		img = new BufferedImage(cpWidth, cpHeight, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		changeMagnification(0);
		gt = new GameTime();
	}

	public void inputInit() {
		input = new InputHandler();
		cp.addKeyListener(input);
		cp.addFocusListener(input);
		cp.addMouseListener(input);
		cp.addMouseMotionListener(input);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void changeMagnification(int mag) {
		this.magnification = mag;
		double cw = cpWidth;
		double ch = cpHeight;
		double temp = cw / ch;
		magY = magnification;
		magX = (int) (magnification * temp);
	}

	public void startGame() {
		if (!gameRunning) {
			gameRunning = true;
			gameThread.start();
		}
	}

	public void stopGame() {
		if (gameRunning) {
			gameRunning = false;
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void run() {
		int frames = 0;
		fps = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;

		while (gameRunning) {
			long currentTime = System.nanoTime();
			long pastTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds = pastTime / 1000000000.0;

			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % 60 == 0) {
					fps = frames;
					previousTime += 1000;
					frames = 0;
				}
			}
			if (ticked) {
				render();
				frames++;
			}
			render();
			frames++;
			newMouseX = InputHandler.mouseX;
			newMouseY = InputHandler.mouseY;

			if (newMouseX > oldMouseX) {
				Controller.turnRight = true;
			}
			if (newMouseX < oldMouseX) {
				Controller.turnLeft = true;
			}
			if (newMouseX == oldMouseX) {
				Controller.turnLeft = false;
				Controller.turnRight = false;
			}

			oldMouseX = newMouseX;
			
//			if(InputHandler.mouseX <= 20 || InputHandler.mouseX >= cp.getWidth() - 20){
//				robot.mouseMove((cpWidth / 2) + frame.getLocationOnScreen().x, (cpHeight / 2) + frame.getLocationOnScreen().y);
//			}
			
		}
	}

	private void tick() {
		gt.tick(input.key);
	}

	private void render() {
		BufferStrategy bs = cp.getBufferStrategy();
		if (bs == null) {
			cp.createBufferStrategy(3); // "3" for 3d game
			return;
		}

		for (int i = 0; i < cpWidth * cpHeight; i++) {
			pixels[i] = screen.pixels[i];
		}

		screen.render(0, 0, gt);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, cpWidth + magX, cpHeight + magY, null);
		printfps(g);
		g.dispose();
		bs.show();
	}

	private void printfps(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(fps + " FPS", 10, 20);
	}
}
