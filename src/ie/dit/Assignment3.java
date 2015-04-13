package ie.dit;

import processing.core.*;
import processing.data.*;


public class Assignment3 extends PApplet{
	
	boolean[] keys = new boolean[526];
	
	Man p;
	GameObjects[] gameObjects;
	
	Star star;
	
	PImage moon;
	PImage title;
	PImage start;
	PImage upgrade;
	PImage stage;
	PImage spaceman;
	PImage starI;
	
	char option ='0'; 
	
	int menuW = 400;
	int menuH = 100;
	int gHeight = 420;
	
	float counter;
			
	public void setup()
	{
		 size(1000,600);
		 
		// gameObjects = new GameObjects[1];
		 
		 
		 setUpPlayerControllers();
		 
		 //gameObjects[0] = new Fall();
		 
		 star = new Star(gHeight);
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
		 start = loadImage("Start.png");
		 upgrade = loadImage("Upgrade.png");
		 stage = loadImage("Stage.png");
		 spaceman = loadImage("Spaceman.png");
		 starI = loadImage("star.png");		 
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
				//gameObjects[0].run();
				
				star.run();
				
				image(starI,star.SX,250,10,10);
				
				
				break;
			}
			case '2':
			{
				option = '0';
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
	        if(pos.x > 0)
	        {
	          pos.x -= moveS;
	          rollspeed = 2 *(acc/100);
	          distance -= .1f;  
	        }
	      }
	    }
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
	
	class Fall extends GameObjects
	{
	  float gHeight;
	  float fallX;
	  float fallacc;
	  float FW;
	  float FH;
	  
	   Fall()
	  {
	     fallacc = 4;
	     FW = 200;
	     FH = 300;
	     gHeight = 500;
	     fallX = random(2000,3000);
	  }//end Fall constuctor
	 
	 //runs the methods
	  public void run()
	 {
	   display();
	   move();
	   reset();
	   stopped();
	 }//end run()

	 //display code
	 public void display()
	 {
	   fill(0);
	   stroke(0);
	   rect(fallX,gHeight,FW,FH);
	 }// end display()
	 
	 //movement code
	 public void move()
	 {
	     fallX -= fallacc*(p.acc/100);; 
	 }//end move()
	 
	 //reset code
	 public void reset()
	 {
	    if(fallX < 0 - FW)
	     {
	        fallX = random(1500,2500);        
	     } 
	 }//end reset
	 
	 //stopped code
	 public void stopped()
	 {
	    //checks to see if player falls down the hole
	    if( p.pos.x > fallX + p.PW/2 && p.pos.x < fallX + FW )
	    {
	      if(p.pos.y >= gHeight - (p.PH)/2)
	      {
	        p.nofloor = true;
	        p.acc = 10;
	        p.falling = true;
	        
	        
	        if(p.pos.y > height)
	        {
	           option = '3';
	           p.pos.y = p.gHeight - p.PH/2;
	           p.acc = 300;
	           fallX = random(1500,2500);
	           p.nofloor = false;
	           p.Gdis = p.distance;
	           p.distance = 0;
	        }//end inner if
	      }//end inner if
	    }//end outer if
	 }//end stopped()
	}//end class
	class GameObjects
	{
	  int gHeight;
	   
	 public void run()
	 {
	   println("run getting called");
	 } 

	}
	
	class Star
	{
	  int gHeight;
	  float SH = random(2,10);
	  float SW;
	  float SX = random(0,1000);
	  float Sacc;
	   
	   Star(int gHeight)
	  {
	    SW = 5;
	    Sacc = 4;
	    this.gHeight = gHeight;
	  } 
	  
	  public void run()
	  {
	     move();
	     reset(); 
	  }
	  // moves grass
	  public void move()
	  {
	     SX -= Sacc *(p.acc/100);
	  }
	  // resets grass
	  public void reset()
	  {
	     if(SX < 0 - SW)
	     {
	        SX = random(1000,1500);
	        SH = random(2,5);
	        SW = random(2,5);
	     }
	  }
	}
	
	
	
}
