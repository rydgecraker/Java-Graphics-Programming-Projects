package rydge.craker.computer.pong;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;


public class MainPanel extends JPanel {
	
	Paddle			leftPaddle;
	Paddle			rightPaddle;
	PaddleTwo		topPaddle;
	PaddleTwo		bottomPaddle;
	
	Random			r;
	
	Ball			ball;
	
	public int		x;
	public int		y;
	public int		width;
	public int		height;
	
	public int		counter;
	
	private int		paddleSize	= 200;
	private int		ballSpeed	= 1;
	
	private int		deaths		= 0;
	
	
	private float	speeder		= 0.0f;
	
	
	MainPanel(int x, int y, int width, int height) {
		super();
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		leftPaddle = new Paddle(x + 10, y + 10, paddleSize, 200, Color.WHITE, height);
		rightPaddle = new Paddle(width - (paddleSize + 10), y + 10, paddleSize, 200, Color.WHITE, height);
		
		topPaddle = new PaddleTwo(width / 2, y + 10, 300, paddleSize, Color.WHITE, width);
		bottomPaddle = new PaddleTwo(width / 2, height - (paddleSize + 10), 300, paddleSize, Color.WHITE, width);
		
		ball = new Ball(width / 2, height / 2, 10, 10, Color.WHITE, width, height);
		counter = 0;
		ball.setSpeed(ballSpeed);
	}
	
	
	public void doComputerStuff() {
		counter++;
		int bb = 0;
		if (counter == 10) {
			r = new Random();
			bb = r.nextInt(4);
		}
		if (counter > 10 && counter < 40) {
			if (bb == 0) {
				leftPaddle.moveRandom();
			} else if (bb == 1) {
				rightPaddle.moveRandom();
			} else if (bb == 2) {
				topPaddle.moveRandom();
			} else {
				bottomPaddle.moveRandom();
			}
			
		}
		if (counter > 100) {
			counter = 0;
		}
		
		ball.move();
		// move left
		// if (ball.x < width / 2) {
		if (ball.y + (ball.height / 2) < leftPaddle.y + (leftPaddle.height / 2)) {
			leftPaddle.move(-ballSpeed);
		} else if (ball.y + (ball.height / 2) > leftPaddle.y + (leftPaddle.height / 2)) {
			leftPaddle.move(ballSpeed);
		} else {
			leftPaddle.move(0);
		}
		// } else {
		// leftPaddle.moveRandom();
		// }
		
		// move right
		// if (ball.x + ball.width > width / 2) {
		if (ball.y + (ball.height / 2) < rightPaddle.y + (rightPaddle.height / 2)) {
			rightPaddle.move(-ballSpeed);
		} else if (ball.y + (ball.height / 2) > rightPaddle.y + (rightPaddle.height / 2)) {
			rightPaddle.move(ballSpeed);
		} else {
			rightPaddle.move(0);
		}
		// } else {
		// rightPaddle.moveRandom();
		// }
		
		// move up
		// if (ball.y < height / 2) {
		if (ball.x + (ball.width / 2) > topPaddle.x + (topPaddle.width / 2)) {
			topPaddle.move(ballSpeed);
		} else if (ball.x + (ball.height / 2) < topPaddle.x + (topPaddle.width / 2)) {
			topPaddle.move(-ballSpeed);
		}
		// } else {
		// topPaddle.moveRandom();
		// }
		// move down
		// if (ball.y + ball.height > height / 2) {
		if (ball.x + (ball.width / 2) > bottomPaddle.x + (bottomPaddle.width / 2)) {
			bottomPaddle.move(ballSpeed);
		} else if (ball.x + (ball.height / 2) < bottomPaddle.x + (bottomPaddle.width / 2)) {
			bottomPaddle.move(-ballSpeed);
		}
		// } else {
		// bottomPaddle.moveRandom();
		// }
		
		
		leftPaddle.intersect(ball);
		rightPaddle.intersect(ball);
		topPaddle.intersect(ball);
		bottomPaddle.intersect(ball);
		if (ball.reset) {
			ballSpeed = 1;
			ball = new Ball(width / 2, height / 2, 10, 10, Color.WHITE, width, height);
			ball.setSpeed(ballSpeed);
			deaths++;
		}
		speeder += 0.001f;
		if (speeder > 1) {
			ball.speed += 1;
			ballSpeed = ball.speed;
			speeder = 0;
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		leftPaddle.draw(g);
		rightPaddle.draw(g);
		topPaddle.draw(g);
		bottomPaddle.draw(g);
		ball.draw(g);
	}
	
}
