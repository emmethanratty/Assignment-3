package ie.dit;

import processing.core.*;

public class Assignment3 extends PApplet{
	
	Start s;
	
	PImage moon;
	PImage title;
	PImage start;
	PImage upgrade;
	
	char option ='0'; 
	
	int menuW = 400;
	int menuH = 100;
			
	public void setup()
	{
		 size(1000,600);
		 
		 s = new Start(this);
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
		 start = loadImage("Start.png");
		 upgrade = loadImage("Upgrade.png");
		 
	}

	public void draw()
	{
		switch(option)
		{
			case '0':
			{
				background(moon);
				image(title,WIDTH/2 + 200,20,600,200);
				image(start,WIDTH/2 + 250,250,menuW,menuH);
				image(upgrade,WIDTH/2 + 250,400,menuW,menuH);
				
				s.run();
				
				break;
			}
			case '1':
			{
				background(255);
				
				break;
			}
			case '2':
			{
				background(0);
				
				break;
			}
		}
	}
	
	public static void Main(String args[])
	{
		PApplet.main(new String[]{ie.dit.Assignment3.class.getName() });
	}
	
	public void mousePressed()
	{
		//System.out.println("Pressed");
		if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
		{
			if(mouseY > 250 && mouseY < 250 + menuH)
			{
				//p.option = '1';
				System.out.println("Pressed");
			}
			//p.option = '1';
			//System.out.println("Pressed");
		}
		if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
		{
			if(mouseY > 400 && mouseY < 400 + menuH)
			{
				//p.option = '1';
				System.out.println("Pressed2");
			}
			//p.option = '1';
			//System.out.println("Pressed");
		}
	}
}
