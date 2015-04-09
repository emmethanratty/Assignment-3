package ie.dit;

import processing.core.*;

public class Assignment3 extends PApplet{
	
	PImage moon;
	PImage title;
	PImage start;
	PImage upgrade;
	
	public void setup()
	{
		 size(1000,600);
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
		 start = loadImage("Start.png");
		 upgrade = loadImage("Upgrade.png");
	}

	public void draw()
	{
		background(moon);
		//imageMode(CENTER);
		image(title,WIDTH/2 + 200,20,600,200);
		image(start,WIDTH/2 + 250,250,400,100);
		image(upgrade,WIDTH/2 + 250,400,400,100);
	}
}
