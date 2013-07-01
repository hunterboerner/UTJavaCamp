package me.hunterboerner;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MouseDraw extends Applet implements MouseMotionListener, KeyListener {
	
	//Global Variables
	int width, height; 
	int rval,gval, bval;
	Image backbuffer;
	Graphics backg;
	
	public void init(){
		width = getSize().width;
		height = getSize().height;
		
		// reduce background flicker ('canvas')
		backbuffer = createImage(width, height);
		backg = backbuffer.getGraphics();
		backg.setColor( Color.black);
		backg.fillRect(0,  0, width, height);
		//
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	public void mouseMoved(MouseEvent e) { }
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX(); //get mouse x position
		int y = e.getY(); // get mouse y position
		backg.setColor( Color.white);
		backg.fillOval(x-5, y-5, 10, 10);
		repaint();
		e.consume();
		showStatus("Mouse Drawing at: " + x + ", " + y);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			System.out.println("Clear screen!");
			backg.setColor(Color.black);
			backg.fillRect(0,  0, width, height);
			repaint();
			e.consume();
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void update(Graphics g){
		g.drawImage (backbuffer, 0, 0, this);
	}
	public void paint (Graphics g){
		update(g);
		
	}

}
