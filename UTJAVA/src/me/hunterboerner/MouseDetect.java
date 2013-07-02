package me.hunterboerner;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class MouseDetect extends Applet implements MouseMotionListener,
		MouseListener, KeyListener {

	int width, height;
	int x, y;
	int i = 0;
	String drawText = "";
	boolean seizure = false;
	int boxX1, boxY1, boxX2, boxY2;
	boolean move = true;
	int a = 0;
	String drawWords = "";

	public void init() {
		boxX2 = 20;
		boxY2 = 20;
		width = getSize().width;
		height = getSize().height;

		setBackground(Color.red);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
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
			g.setFont(g.getFont().deriveFont(12.0F));
			g.setColor(Color.green);
			g.fillRect(0, 0, width / 3, height);
			g.setColor(Color.black);
			g.fillRect(width / 3, 0, width / 3 * 2, height);
			g.setColor(Color.blue);
			g.fillRect(width / 3 * 2, 0, width, height);
			g.setColor(Color.gray);
			g.fillRoundRect(x - 5, y - 15, drawText.length() * 7, 20, 5, 5);
			// g.fillRect(boxX1, boxY1, boxX2, boxY2);
			g.setColor(Color.white);
			g.drawString(drawText, x, y);

		} else {
			if (a < 20) {
				drawWords = "BACON";
				a++;
			} else if (a < 40) {
				drawWords = "MOAR BACON";
				a++;
			} else {
				a = 0;
			}
			g.setColor(seizure(i));
			g.fillRect(0, 0, width, height);
			g.setFont(g.getFont().deriveFont(100.0F));
			g.setColor(Color.green);
			g.drawString(drawWords, 100, 100);

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

	int moveLocationX;
	int moveLocationY;

	public void mouseDragged(MouseEvent e) {
		if (move) {
			x = e.getX();
			y = e.getY();
			moveLocationX = x - boxX1;
			moveLocationY = x - boxY1;
			boxX1 = moveLocationX;
			boxY1 = moveLocationY;
			boxX2 = moveLocationX;
			boxY2 = moveLocationY;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if (boxX1 < x && x < boxX2 && boxY1 < y && y < boxY2) {
			move = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (move) {
			boxX1 = moveLocationX;
			boxY1 = moveLocationY;
			boxX2 = moveLocationX;
			boxY2 = moveLocationY;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (seizure) {
				seizure = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} else {
				seizure = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}