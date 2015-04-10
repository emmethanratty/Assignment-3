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
	
	float counter;
			
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
		 
		 counter=0.0f;
		 
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
	  char button1;
	  char button2;
	  
	  boolean nofloor;
	  boolean nojump;
	  boolean falling;
	  
	  int gHeight = 420;
	  int moveS = 3;
	  
	  float PH = 200;
	  float PW = 200;
	  float PX = 50;
	  float PY;
	  float gravityF = 5;
	  float currentJH = 400;
	  float jumpH = currentJH;
	  float rollspeed = 4;
	  float distance = 0;
	  float Cacc = 300f;
	  float acc = Cacc;
	  float Gdis;
	  
	  //char option = '0';
	    
	  Man()
	  {
	    pos = new PVector(width / 2, height / 2);
	    pos.y = gHeight;
	    pos.x = width/2;
	  }
	  
	  Man( char up, char down, char left, char right, char button1, char button2)
	  {
	    this();
	    this.up = up;
	    this.down = down;
	    this.left = left;
	    this.right = right;
	    this.button1 = button1;
	    this.button2 = button2;
	  }
	  
	  Man( XML xml)
	  {
	    this(
	          buttonNameToKey(xml, "up")
	        , buttonNameToKey(xml, "down")
	        , buttonNameToKey(xml, "left")
	        , buttonNameToKey(xml, "right")
	        , buttonNameToKey(xml, "button1")
	        , buttonNameToKey(xml, "button2")
	        );
	  }
	  
	  public void run()
	  {
	    update();
	    display();
	    gravity(); 
	    stopped();
	    
	    jumpH = currentJH;
	  }
	  
	  
	  public void update()
	  {
	    if (checkKey(up))
	    {
	      if(option == '1')
	      {
	         if(pos.y > jumpH && nofloor == false)
	          {        
	               pos.y -= 5;
	          } 
	          else
	          {
	            nojump = false;
	            nofloor = true;
	          } 
	      }
	    }
	    else
	    {
	      if(option == '1')
	      {
	        //checks ti make sure player is still on screen
	        if(pos.y < gHeight - PH/2)
	          {        
	               nofloor = true;
	          } 
	      }
	    }
	    if (checkKey(left))
	    {
	      if(falling == false)
	      {
	        //checks ti make sure player is still on screen
	        if(pos.x > 0)
	        {
	          pos.x -= moveS;
	          rollspeed = 2 *(acc/100);
	          distance -= .1f;  
	        }
	      }
	    }
	    //changes rollspeed back to normal
	    else
	    {
	      rollspeed = 4*(acc/100);
	    }    
	    if (checkKey(right))
	    {
	      if(falling == false)
	      {
	        if(pos.x < width)
	        {
	          pos.x += moveS;
	          rollspeed = 6*(acc/100);
	          distance += .1f;
	        }
	      }
	    }
	    //changes rollspeed back to normal
	     else
	    {
	      rollspeed = 4*(acc/100);
	    }   
	  }

	  //displays text and images  
	  public void display()
	  { 
	    textSize(30);
	    text("Distance: " + distance,30,50);
	    text("Acceleration: " + acc,30,100);
	    
	     image(spaceman,pos.x - PW/2,pos.y,PW,PH);   
	     /*
	     translate(pos.x, pos.y);
	       
	     rotate(counter*TWO_PI/360);
	     
	     translate(-PW/2, -PW/2);
	       
	     image(spaceman,0,0,PW,PH);
	     
	     counter += rollspeed *(acc/100);
	     */
	  } 
	 
	  //makes gravity
	  public void gravity()
	  {
	     if(nofloor)
	     {
	        pos.y += gravityF; 
	     }
	     if(pos.y == gHeight)
	     {
	       nofloor = false;
	     }   
	   }
	   
	   //when stopped
	   public void stopped()
	   {
	     if(option == '1')
	     {
	    	System.out.println("Moving");
	       distance += .2f*(acc/100);
	       acc -= .1f;
	       // checks to see if stopped
	       if( acc < 0 )
	       {
	          option = '3';
	         Gdis = distance;
	         distance= 0f;
	         acc = Cacc; 
	       }
	     }
	   }
	}
}
