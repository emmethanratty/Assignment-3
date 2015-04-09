package ie.dit;

import processing.core.*;
import processing.data.*;


public class Assignment3 extends PApplet{
	
	boolean[] keys = new boolean[526];
	
	Man p;
	
	PImage moon;
	PImage title;
	PImage start;
	PImage upgrade;
	PImage stage;
	PImage spaceman;
	
	char option ='0'; 
	
	int menuW = 400;
	int menuH = 100;
			
	public void setup()
	{
		 size(1000,600);
		 
		 setUpPlayerControllers();
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
		 start = loadImage("Start.png");
		 upgrade = loadImage("Upgrade.png");
		 stage = loadImage("Stage.png");
		 spaceman = loadImage("Spaceman.png");
		 
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
				
				
				break;
			}
			case '1':
			{
				background(stage);
				
				
				p.run();
				break;
			}
			case '2':
			{
				background(0);
				
				break;
			}
		}
	}
	
	public void mousePressed()
	{
		//System.out.println("Pressed");
		if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
		{
			if(mouseY > 250 && mouseY < 250 + menuH)
			{
				option = '1';
				System.out.println("Pressed");
			}
		}
		if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
		{
			if(mouseY > 400 && mouseY < 400 + menuH)
			{
				option = '2';
				System.out.println("Pressed2");
			}
			//p.option = '1';
			//System.out.println("Pressed");
		}
	}
	
	public void keyPressed()
	{
	  
	   keys[keyCode] = true;         
	}//end key pressed
	public void keyReleased()
	{
	  keys[keyCode] = false;
	}//end key released

	public boolean checkKey(char theKey)
	{
	  return keys[Character.toUpperCase(theKey)];
	}//End checkKey()

	public char buttonNameToKey(XML xml, String buttonName)
	{
	  String value =  xml.getChild(buttonName).getContent();
	  if ("LEFT".equalsIgnoreCase(value))
	  {
	    return LEFT;
	  }
	  if ("RIGHT".equalsIgnoreCase(value))
	  {
	    return RIGHT;
	  }
	  if ("UP".equalsIgnoreCase(value))
	  {
	    return UP;
	  }
	  if ("DOWN".equalsIgnoreCase(value))
	  {
	    return DOWN;
	  }
	  //.. Others to follow
	  return value.charAt(0);  
	}//end buttonNametoKey()

	public void setUpPlayerControllers()
	{
	  XML xml = loadXML("controls.xml");
	  XML playerXML = xml.getChild("player");
	  
	  p = new Man(playerXML);
	}

class Man
{
  PVector pos;
  char up;
  char down;
  char left;
  char right;
  char start;
  char button1;
  char button2;
  
  int gHeight = 420;
  
  float PH = 200;
  float PW = 200;
  float speed = 2;
  
  
  char option = '0';
    
  Man()
  {
	pos = new PVector(width / 2, height / 2);
    pos.y = gHeight;
    pos.x = PW/2;
  }
  
  Man( char up, char down, char left, char right, char start, char button1, char button2)
  {
    this();
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    this.start = start;
    this.button1 = button1;
    this.button2 = button2;
  }
  
  Man(XML xml)
  {
    this(
          buttonNameToKey(xml, "up")
        , buttonNameToKey(xml, "down")
        , buttonNameToKey(xml, "left")
        , buttonNameToKey(xml, "right")
        , buttonNameToKey(xml, "start")
        , buttonNameToKey(xml, "button1")
        , buttonNameToKey(xml, "button2")
        );
  }
  
  
  
  
  
  public void run()
  {
	  display();
	  move();
  }
  
  public void display()
  {
	  image(spaceman,pos.x,pos.y,PH,PW);
  }
  public void move()
  {
	if(checkKey(left))
	{
    if(pos.x > 0)
    {
       pos.x -= speed;
    }
	}
    
    if (checkKey(right))
    {
          pos.x += speed;
    }
  
  
  
 }
}
}
