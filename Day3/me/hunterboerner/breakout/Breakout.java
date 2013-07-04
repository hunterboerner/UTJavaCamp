package me.hunterboerner.breakout;

/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class Breakout extends GraphicsProgram {
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 800;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 8;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	private static final int NUMBER_BRICKS = NBRICKS_PER_ROW * NBRICK_ROWS;
	/** Width of a brick */
	private static final int BRICK_WIDTH = (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1)
			* BRICK_SEP)
			/ NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of lives */
	private int lives = 3;

	// /** Delay between ball updates */
	// private static final int ANIMATION_PAUSE = 10;

	// Declaration of all the instance variables
	private GRect paddle;
	private GOval ball;
	private GLabel messageLabel;
	private GRect wall;
	private int numBricksLeft = NUMBER_BRICKS;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

	private double xv = -3.0;
	private double yv = -3.0;
	boolean run = false;
	boolean started = false;
	boolean gameRun = true;
	Font font = new Font("Courier New", Font.BOLD, 72);

	int livesUsed = 0;

	public static void main(String[] args) {
		new Breakout().start(args);
	}

	/* Method: run() */
	/** Runs the Breakout program. */

	public void run() {
		messageLabel = new GLabel("", getWidth() / 2, getHeight() / 2);
		addMouseListeners();
		addKeyListeners();
		runGame();
	}

	public void runGame() {
		while (gameRun) {
			resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
			setLabel("Press Key");

			if (started) {

				for (livesUsed = 0; livesUsed < lives; livesUsed++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setLabel("");

					drawElements();
					run = true;
					runLevel();
				}
			}

		}

	}

	/**
	 * Runs the game
	 */
	public void runLevel() {
		xv = -3.0;
		yv = -3.0;
		while (run == true) {
			updateBall();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void drawElements() {
		generateBricks(NBRICK_ROWS, NBRICKS_PER_ROW, BRICK_WIDTH, BRICK_HEIGHT,
				BRICK_Y_OFFSET);
		createPaddle(PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_Y_OFFSET);
		createBall(BALL_RADIUS);
		createWall();
	}

	/**
	 * 
	 * @param width
	 *            App Width
	 * @param height
	 *            App Height
	 */
	public void createWall() {
		wall = new GRect(2, 2, getWidth() - 4, getHeight() - 4);
		add(wall);
	}

	/**
	 * @param ballRadius
	 *            radius of dem ballz
	 * 
	 **/
	public void createBall(int ballRadius) {
		ball = new GOval(getWidth() / 2 - ballRadius, getHeight() / 2
				- ballRadius, ballRadius * 2, ballRadius * 2);
		ball.setFilled(true);
		add(ball);
	}

	/**
	 * @param pWidth
	 *            paddle width
	 * @param pHeight
	 *            paddle height
	 * @param pOffset
	 *            paddle offset
	 **/
	public void createPaddle(int pWidth, int pHeight, int pOffset) {
		// paddle.setBounds(getWidth() / 2, getHeight() - pOffset, pWidth,
		// pHeight);
		paddle = new GRect(getWidth() / 2 - pWidth / 2, getHeight() - pOffset,
				pWidth, pHeight);
		paddle.setFilled(true);
		add(paddle);
	}

	/**
	 * @param x
	 *            X coord
	 * @param y
	 *            Y coord
	 **/
	public void movePaddle(int x, int y) {
		if (paddle != null) {
			paddle.setLocation(x, paddle.getY());
			paddle.setColor(Color.black);
			add(paddle);
		}

	}

	public void setLabel(String label) {
		messageLabel.setLocation(getWidth() / 2
				- (getFontMetrics(font).stringWidth(label) / 2),
				(getHeight() / 2) - font.getSize() / 2);
		messageLabel.setLabel(label);
		messageLabel.setFont(font);
		messageLabel.setColor(Color.black);
		// remove(messageLabel);
		add(messageLabel);
	}

	public void updateBall() {
		if (ball.getY() > (getHeight() - PADDLE_Y_OFFSET)) {
			run = false;
			removeAll();
			if ((lives - livesUsed - 1) == 0) {
				setLabel("LOSER");
				gameRun = false;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
				;
			} else {
				setLabel("Lives: " + (lives - livesUsed - 1));
				println("labelset");
			}

		} else {
			if ((ball.getX() + BALL_RADIUS * 2) > getWidth()) {
				xv = -xv;
			}
			if ((ball.getY() + BALL_RADIUS * 2) > getHeight()) {
				yv = -yv;
			}
			if (ball.getX() < 0) {
				xv = -xv;
			}
			if (ball.getY() < 0) {
				yv = -yv;
			}
			if (getCollidingObject() != null) {
				if (getCollidingObject() == paddle) {
				} else if (getCollidingObject() != messageLabel
						|| getCollidingObject() != ball) {
					remove(getCollidingObject());
					numBricksLeft--;
					if (numBricksLeft < 0) {
						run = false;
						removeAll();
						setLabel("WiNnEr");
						livesUsed = 3;
					}
				}
				bounceClip.play();
				yv = -yv;

			}

			ball.setLocation(ball.getX() + xv, ball.getY() + yv);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (run) {
			movePaddle(e.getX(), e.getY());
		}
	}

	public void keyTyped(KeyEvent e) {
		started = true;
	}

	/**
	 * @param numOfRow
	 *            Number of bricks per row
	 * @param numOfBricks
	 *            Number of bricks in a row
	 * @param brickWidth
	 *            width of bricks in pixels
	 * @param brickHeight
	 *            height of bricks in pixels
	 * @param brickYOffset
	 *            offset from top
	 **/
	public void generateBricks(int numOfRow, int numOfBricks, int brickWidth,
			int brickHeight, int brickYOffset) {
		int x = (getWidth() - (numOfBricks * brickWidth)) / 4;
		int y = brickYOffset;
		for (int i = 0; i < numOfRow; i++) {
			for (int j = 0; j < numOfBricks; j++) {
				GRect brick = new GRect(x, y, brickWidth, brickHeight);
				brick.setColor(rgen.nextColor());
				brick.setFillColor(rgen.nextColor());
				brick.setFilled(true);
				add(brick);
				x = x + brickWidth + 2;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x = (getWidth() - (numOfBricks * brickWidth)) / 4;
			y = y + brickHeight + 2;
		}

	}

	private GObject getCollidingObject() {
		// Finds collider, if the ball collided on the top left corner
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		}
		// Finds collider, if the ball collided on the top right corner
		else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}
		// Finds collider, if the ball collided on the bottom left corner
		else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		}
		// Finds collider, if the ball collided on the bottom right corner
		else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2
				* BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2
					* BALL_RADIUS);
		}
		return null;
	}

}
