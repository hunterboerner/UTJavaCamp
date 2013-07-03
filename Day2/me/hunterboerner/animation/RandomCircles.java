package me.hunterboerner.animation;

import java.awt.Frame;
import java.awt.GraphicsDevice;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class RandomCircles extends GraphicsProgram {

	private static final int PAUSE_TIME = 1;
	private static final int MAX_RADIUS = 100;
	private static final int MIN_DIAMETER = 50;
	private RandomGenerator rgen = new RandomGenerator();
	GOval oval;
	/** Radius of the ball in pixels */
	public static int APPLICATION_WIDTH = 800;
	public static int APPLICATION_HEIGHT = 600;
	private static int randomDiameter = 0;

	@SuppressWarnings("null")
	public static void main(String[] args) {
		GraphicsDevice device = null;
		Frame frame = new Frame();
		new RandomCircles().start(args);
		if (device.isFullScreenSupported()) {
			device.setFullScreenWindow(frame);
		}

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			APPLICATION_WIDTH = getWidth();
			APPLICATION_HEIGHT = getHeight();
			randomDiameter = rgen.nextInt(MIN_DIAMETER, MAX_RADIUS * 2);
			GOval myOval = new GOval(randomDiameter, randomDiameter);
			myOval.setFilled(true);
			myOval.setColor(rgen.nextColor());
			myOval.setLocation(rgen.nextInt(APPLICATION_WIDTH)
					- APPLICATION_WIDTH / 20, rgen.nextInt(APPLICATION_HEIGHT)
					- APPLICATION_HEIGHT / 20);
			add(myOval);
			if (getElementCount() > 100)
				removeAll();

		}

	}

}
