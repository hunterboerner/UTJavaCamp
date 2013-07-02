package me.hunterboerner.animation;

import java.awt.Color;
import java.util.Arrays;

import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class SeuratGenerator extends GraphicsProgram {

	private static final int SPLOTCH_DIAMETER = 3;

	private RandomGenerator rgen = new RandomGenerator();

	public static void main(String[] args) {
		new SeuratGenerator().start(args);
	}

	int[][] pixelArray;

	public void run() {
		resize(getMaximumSize());
		GImage image = new GImage(
				"/Users/theron/Dropbox/BunnyPics/2012-05-20 22.02.49-1.jpg");
		double width = image.getWidth();
		double height = image.getHeight();
		double windowWidth = getWidth();
		boolean ran = false;

		// GImage myImage = new GImage(
		// "/Users/theron/Desktop/UTJavaCamp/AnimationSoln/images/16497173.jpg");
		pixelArray = image.getPixelArray();

		while (true) {
			int y = 0;
			// int x = rgen.nextInt((int) width);
			// int y = rgen.nextInt((int) height);

			/*
			 * if (intList[x][y] != 0) { continue; } else { intList[x][y] = 1; }
			 */
			while (y < height) {
				for (int x = 0; x < width; x = x + 5) {
					GOval myOval = new GOval(SPLOTCH_DIAMETER, SPLOTCH_DIAMETER);

					myOval.setColor(getColorAt(image, x, y));
					myOval.setFilled(true);
					double actualX = (x * windowWidth / width)
							- SPLOTCH_DIAMETER;
					double actualY = (y * windowWidth / width)
							- SPLOTCH_DIAMETER;
					myOval.setLocation(actualX, actualY);
					if(ran = true){
						remove(1);
					}
					add(myOval);

				}
				
				y = y + 10;
			}
			ran = true;
			y = 0;

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
