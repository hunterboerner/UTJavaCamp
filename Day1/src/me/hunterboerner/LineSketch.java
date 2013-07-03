package me.hunterboerner;

import java.applet.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LineSketch extends Applet {
	
	int width, height;
	
	// default window size: 200 x 200
	// init function is just like setup() in Processing!
	public void init()
	{ 
		width = getSize().width;
		height = getSize().height;
		System.out.println(width);
		System.out.print(height);
		setBackground(Color.black);
	}
	// paint function is just like draw() in Processing! 
	public void paint(Graphics g)
	{
		g.setColor (Color.green);
		
		g.drawLine(0, height, width, 0);
		g.drawLine(0, 0, width, height);
		g.drawLine(0, 0, width, height);
		g.drawLine(width/2, 0, width/2, height);
		g.drawLine(0, height/2, width, height/2);
	}
}

