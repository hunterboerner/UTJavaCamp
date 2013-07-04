package me.hunterboerner.animation;

import java.awt.Color;
import java.util.Arrays;

import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class SeuratGenerator extends GraphicsProgram {

	private static final int SPLOTCH_DIAMETER = 5;

	private RandomGenerator rgen = new RandomGenerator();

	public static void main(String[] args) {
		new SeuratGenerator().start(args);
	}

	int[][] pixelArray;

	public void run() {
		resize(getMaximumSize());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GImage image = new GImage(
				"/Users/theron/Dropbox/BunnyPics/2012-05-20 22.02.49-1.jpg");
		double width = image.getWidth();
		double height = image.getHeight();
		double windowWidth = getWidth();
		double windowHeight = getHeight();
		boolean ran = false;
		int dotCount = 0;
		double size = (Math.pow(SPLOTCH_DIAMETER / 2, 2) * Math.PI);
		// double check = ((windowWidth * windowHeight) / size) * 10;
		double check = 1000000.0;

		// GImage myImage = new GImage(
		// "/Users/theron/Desktop/UTJavaCamp/AnimationSoln/images/16497173.jpg");
		pixelArray = image.getPixelArray();
		int intList[][] = new int[(int) width][(int) height];

		while (check > dotCount) {
			// int y = 0;
			int x = rgen.nextInt((int) width);
			int y = rgen.nextInt((int) height);
			boolean continueB = false;
			for (int i = 0; i < (SPLOTCH_DIAMETER / 2); i++) {
				try {
					if (x + i <= width && y + i <= height && x + i <= width
							&& y + i <= height) {
						if (intList[x + i][y + i] != 0) {
							// System.out.println("New #");
							continue;

						} else {
							intList[x + i][y + i] = 1;
							intList[x - i][y - i] = 1;
						}
					} else {
						// System.out.println("new2");
						continueB = true;
						continue;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("-1 crap");
				}

				if (continueB) {
					continue;
				}
			}

			// while (y < height) {
			// for (int x = 0; x < width; x = x + SPLOTCH_DIAMETER) {
			GOval myOval = new GOval(SPLOTCH_DIAMETER, SPLOTCH_DIAMETER);

			myOval.setColor(getColorAt(image, x, y));
			myOval.setFilled(true);
			double actualX = (x * windowWidth / width) - SPLOTCH_DIAMETER;
			double actualY = (y * windowWidth / width) - SPLOTCH_DIAMETER;
			myOval.setLocation(actualX, actualY);
			/*
			 * if(ran = true){ remove(1); }
			 */
			add(myOval);
			System.out.println(dotCount + "|" + check);
			dotCount++;
			// System.out.println("Iterte");
			// }

			// y = y + SPLOTCH_DIAMETER * 2;
			// }
			// ran = true;
			// y = 0;

		}
	}

	/**
	 * 
	 * @param image
	 * @param x
	 *            of a pixel
	 * @param y
	 *            of a pixel
	 * @return the color found at a specific pixel
	 */
	private Color getColorAt(GImage image, int x, int y) {
		// feel free to ignore how the program is looking up the pixel color
		// this syntax is not very nice and you don't need to understand it.
		return new Color(pixelArray[y][x]);
	}
}
