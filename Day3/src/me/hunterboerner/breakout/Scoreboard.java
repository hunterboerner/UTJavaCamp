package me.hunterboerner.breakout;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import acm.graphics.GLabel;

public class Scoreboard {

	GLabel currentScoreLabel;
	GLabel highScoreLabel;

	int currentScore;
	int highScore;

	String currentScoreStr;
	String highScoreStr;

	/**
	 * 
	 * @param str
	 *            String to be displayed
	 * @param highScoreStr
	 *            String of what the high score value should say
	 * @param x
	 *            X Coord
	 * @param y
	 *            Y Coord
	 * @param fontSize
	 *            size of the font
	 */
	public Scoreboard(String currentScoreStr, String highScoreStr, double x,
			double y, int fontSize) {
		this.currentScoreStr = currentScoreStr;
		this.highScoreStr = highScoreStr;
		Font font = new Font("Courier", Font.BOLD, fontSize);
		currentScoreLabel = new GLabel(currentScoreStr, x, y);
		highScoreLabel = new GLabel(highScoreStr, x, y + fontSize);
		highScoreLabel.setFont(font);
		currentScoreLabel.setFont(font);
	}

	public void scoreReset() {
		this.currentScore = -1;
	}

	public void addScore() {
		this.currentScore++;
		currentScoreLabel.setLabel(currentScoreStr + currentScore);
	}

	private void setHighScore() throws FileNotFoundException {

		File inFile = new File("scoreboard.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		String score = "";
		try {

			score = reader.readLine();
			reader.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		highScoreLabel.setLabel(highScoreStr + score);

	}

	public void loadScore() throws IOException {
		String line;
		File inFile = new File("scoreboard.txt");

		inFile.createNewFile();

		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		if ((line = reader.readLine()) != null) {
			System.out.println(line);
			highScore = Integer.parseInt(line);
		}

		reader.close();
		highScoreLabel.setLabel(highScoreStr + highScore);
		System.out.println(highScore);
	}

	public void saveScore() throws IOException {

		if (currentScore > highScore) {

			File outFile = new File("scoreboard.txt");

			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

			writer.write(Integer.toString(this.currentScore));
			writer.close();
			setHighScore();
		}
	}
}
