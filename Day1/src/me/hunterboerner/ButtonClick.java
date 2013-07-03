package me.hunterboerner;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ButtonClick extends Applet implements MouseListener, MouseMotionListener {

	int width, height;
	int mouseX;
	int mouseY;
	int pressCount = 0;
	
	public void init() {
		 width = getSize().width;
	     height = getSize().height;
	     setBackground(Color.black);
		
		 addMouseListener( this );
	     addMouseMotionListener( this );
	}
	
	public void mousePressed(MouseEvent e) {
		// called when mouse is pressed 
		int x = e.getX();
		int y = e.getY();
		
		//write code for button event handling here! 
	}
	public void mouseReleased(MouseEvent e) {
		// mouse button released; set isButtonPressed variable to false
		setBackground(Color.black);
	}
	
	public void paint(Graphics g) {
		//set color of rectangle to green
		g.setColor(Color.green);
		//draw rectangle at position (80, 80) with a width and height of 50
		g.fillRect(80, 80, 50, 50);
	}
	
	public void mouseMoved(MouseEvent e) {
		// show mouse position status
		int x = e.getX();
		int y = e.getY();
		showStatus("Mouse Position: (" + x + ", " + y + ")");
	}

	
	// MouseMotionListener functions (inherited - not used in this exercise)
	public void mouseDragged(MouseEvent e) {}
	// MouseListener functions (inherited)
	public void mouseClicked(MouseEvent e) {}
	//called when mouse enters application window
	public void mouseEntered(MouseEvent e) {}
	// called when mouse exits application window 
	public void mouseExited(MouseEvent e) {}
}
