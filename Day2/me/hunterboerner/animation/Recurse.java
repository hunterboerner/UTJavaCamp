package me.hunterboerner.animation;

import java.awt.Color;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class Recurse extends GraphicsProgram {

	// constant pause time
	private static final int PAUSE_TIME = 0;

	// Initial Ball Radius
	private static final int BALL_RADIUS = 100;

	// Our Recursion Tree Depth
	private static final int DEPTH = 10;

	// Basic screen settings
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 500;

	// start our Recurse objects
	public static void main(String[] args) {
		new Recurse().start(args);
	}

	// main code logic here:
	public void run() {
		TriangleSplit(DEPTH,addBall(getWidth()/2-50,getHeight()/2-60,BALL_RADIUS,BALL_RADIUS,Color.BLUE,true));
	}

	/**
	 * @param n
	 *            is how many splits we want to do.
	 * @param ball
	 *            is the first ball to be split
	 * @return void Method takes a ball and splits it into three
	 */
	double xLoc;
	double yLoc;
	Color color;
	boolean fill;
	double radius;
	int iterations = 0;

	boolean left = true;
	GOval nextBall = new GOval(xLoc, yLoc, radius, radius);

	public void TriangleSplit(int depth, GOval ball) {
		if (depth < 1)
			return;
		remove(ball);
		TriangleSplit(
				depth - 1,
				addBall(ball.getX(), ball.getY() - ball.getHeight(),
						ball.getWidth() / 2, ball.getHeight() / 2,
						ball.getColor(), ball.isFilled()));
		pause(PAUSE_TIME);

		// left down
		TriangleSplit(
				depth - 1,
				addBall(ball.getX() - ball.getWidth(),
						ball.getY() + ball.getHeight(), ball.getWidth() / 2,
						ball.getHeight() / 2, ball.getColor(), ball.isFilled()));
		pause(PAUSE_TIME);

		// right down
		TriangleSplit(
				depth - 1,
				addBall(ball.getX() + ball.getWidth(),
						ball.getY() + ball.getHeight(), ball.getWidth() / 2,
						ball.getHeight() / 2, ball.getColor(), ball.isFilled()));
		pause(PAUSE_TIME);
	}

	/**
	 * 
	 * @param x
	 *            ball x location
	 * @param y
	 *            ball y location
	 * @param width
	 *            ball
	 * @param height
	 *            ball
	 * @param color
	 *            ball
	 * @param filled
	 *            ball
	 * @return the ball to add
	 */
	public GOval addBall(double x, double y, double width, double height,
			Color color, boolean filled) {
		GOval aball = new GOval(x, y, width, height);
		aball.setFilled(filled);
		aball.setColor(color);
		add(aball);
		return aball;
	}

}
