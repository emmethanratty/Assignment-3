package ie.dit;

import processing.core.*;

public class Assignment3 extends PApplet{
	
	PImage moon;
	PImage title;
	
	public void setup()
	{
		 size(1000,600);
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
	}

	public void draw()
	{
		background(moon);
		//imageMode(CENTER);
		image(title,WIDTH/2 + 200,20,600,200);
	}
}
