package me.hunterboerner;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class MouseDetect extends Applet implements MouseMotionListener,
		MouseListener {

	int width, height;
	int x, y;
	int i = 0;
	String drawText = "";
	boolean seizure = false;
	int boxX1, boxY1, boxX2, boxY2;

	public void init() {
		boxX2 = 20;
		boxY2 = 20;
		width = getSize().width;
		height = getSize().height;
		System.out.println(width);
		System.out.println(height);
		setBackground(Color.red);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				width = getSize().width;
				height = getSize().height;
			}
		});
	}

	public Color seizure(int number) {
		if (number == 0) {
			return Color.red;
		} else if (number == 1) {
			return Color.orange;
		} else if (number == 2) {
			return Color.yellow;
		} else if (number == 3) {
			return Color.green;
		} else if (number == 4) {
			return Color.blue;
		} else if (number == 5) {
			return Color.magenta;
		} else {
			return Color.black;
		}
	}

	public void paint(Graphics g) {
		if (!seizure) {
			g.setColor(Color.green);
			g.fillRect(0, 0, width / 3, height);
			g.setColor(Color.black);
			g.fillRect(width / 3, 0, width / 3 * 2, height);
			g.setColor(Color.blue);
			g.fillRect(width / 3 * 2, 0, width, height);
			g.setColor(Color.gray);
			g.fillRoundRect(x - 5, y - 15, drawText.length() * 7, 20, 5, 5);
			g.fillRect(boxX1, boxY1, boxX2, boxY2);
			g.setColor(Color.white);
			g.drawString(drawText, x, y);
		} else {
			g.setColor(seizure(i));
			g.fillRect(0, 0, width, height);

		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void update(Graphics g) {
		paint(g);
		i++;
		System.out.println(i);
		if (i > 5) {
			i = 0;
		}
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		showStatus("Mouse Position: (" + x + ", " + y + ")");

		if (width / 3 < x && x < width / 3 * 2) {
			drawText = "I like trains....";
		}
		if (x > width / 3 * 2) {
			drawText = "on blue rectangle!";
		}
		if (x < width / 3) {
			drawText = "on green rectangle!";
		}
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if (boxX1 < x && x < boxX2 && boxY1 < x && x < boxY2) {
			int moveLocationX = x - boxX1;
			int moveLocationY = x - boxY1;
			boxX1 = moveLocationX;
			boxY1 = moveLocationY;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//seizure = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//seizure = false;

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}