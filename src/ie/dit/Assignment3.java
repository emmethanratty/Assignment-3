package ie.dit;

import processing.core.*;

public class Assignment3 extends PApplet{
	
	PImage moon;
	
	public void setup()
	{
		 size(1000,600);
		 
		 moon = loadImage("moon.png");
	}

	public void draw()
	{
		background(moon);
	}
}
