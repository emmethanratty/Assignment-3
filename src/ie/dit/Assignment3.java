package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.XML;


public class Assignment3 extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1081748086625648614L;
	
	
	int gHeight = 420;
	boolean[] keys = new boolean[526];
	
	Man p;
	GameObjects[] gameObjects;
	
	ArrayList<Star> star = new ArrayList<Star>(gHeight);
	
	Gameover gameover;
	Upgrade upgrade;
	Debris debris;
	
	Earth earth = new Earth(this);
	Fuel fuel;
	
	
	PImage moon;
	PImage title;
	PImage start;
	PImage upgradeI;
	PImage stage;
	PImage spaceman;
	PImage starI;
	PImage earthI;
	PImage fuelI;
	PImage gameoverI;
	PImage debrisI;

	
	
	
	char option ='0'; 
	char gameO;
	
	int menuW = 400;
	int menuH = 100;
	
	
	float counter;
			
	public void setup()
	{
		 size(1000,600);
		 
		 gameObjects = new GameObjects[1];
		 gameover = new Gameover(this);
		 upgrade = new Upgrade(this);
		 debris = new Debris(this);
		 
		 
		 setUpPlayerControllers();
		 
		 gameObjects[0] = new Fall();
		 
		 moon = loadImage("moon.png");
		 title = loadImage("Title3.png");
		 start = loadImage("Start.png");
		 upgradeI = loadImage("Upgrade.png");
		 stage = loadImage("Stage.png");
		 spaceman = loadImage("Spaceman.png");
		 starI = loadImage("star.png");		
		 earthI = loadImage("earth2.png");
		 fuelI = loadImage("fuel.png");
		 gameoverI = loadImage("gameover.png");
		 debrisI = loadImage("crate.png");
		 counter=0.0f;
		 
		 fuel = new Fuel(this);
		 
		 for(int i = 0; i < 100; i++)
		   {
		     star.add(new Star(gHeight));
		   }
		 
	}

	public void draw()
	{
		switch(option)
		{
			case '0':
			{
				//menu
				background(moon);
				image(title,WIDTH/2 + 200,20,600,200);
				image(start,WIDTH/2 + 250,250,menuW,menuH);
				image(upgradeI,WIDTH/2 + 250,400,menuW,menuH);
								
				break;
			}
			case '1':
			{
				//game
				background(stage);
				
				gameObjects[0].run();
				earth.run();
				
				 for(int i = 0; i < 100; i++)
			      {
			        image(starI,star.get(i).SX,star.get(i).SY,star.get(i).SW,star.get(i).SH);
			      }
				 
				 for(Star star1 : star) 
			      {
			        star1.run();
			      }
				 
				 image(earthI, earth.EX, earth.EY,earth.EW,earth.EY);
				 
				 image(fuelI,fuel.FX,fuel.FY,fuel.FW,fuel.FH);
				 
				 //image(debrisI,debris.DX,debris.DY,debris.DW,debris.DH);
				 
				 p.run();
				 
				 fuel.run();
				 
				 debris.run();
				 
				 hitbox();
			
				
				
				break;
			}
			case '2':
			{
				//upgrade
				background(moon);
				upgrade.run();
				
				
				break;
			}
			case '3':
			{
				
				//gameover
				background(moon);
				gameover.run();
				image(gameoverI, width/2 - 400,10, 600,200);
				
				
				break;
			}
		}
	}
	
	public void hitbox()
	{
	   if(p.pos.x + p.PW > fuel.FX && p.pos.x < fuel.FX + fuel.FW && p.pos.y + p.PY > fuel.FY && p.pos.y < fuel.FY + fuel.FH ) 
	     {
	        fuel.FX = random(2000,fuel.frequency);
	        fuel.FY = random(50,458);
	        p.acc = p.acc + 20;
	     }
	   
	   if(p.pos.x + debris.DW > debris.DX && p.pos.x < debris.DX + debris.DW && p.pos.y + p.PY > debris.DY && p.pos.y < debris.DY + debris.DH )
	     {
	        debris.DX = random(2000,2100);
	        debris.DY = random(50,gHeight);
	        //p.acc = p.acc - 50;
	     }
	}//end hitbox
	
	public void mousePressed()
	{
		if(option == '0')
		{
			//System.out.println("Pressed");
			if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
			{
				if(mouseY > 250 && mouseY < 250 + menuH)
				{
					option = '1';
					System.out.println("Pressed");
					gameO = '2';
				}
			}
			if(mouseX > (WIDTH/2 + 250) && mouseX < (WIDTH/2 + 250) + menuW )
			{
				if(mouseY > 400 && mouseY < 400 + menuH)
				{
					option = '2';
					System.out.println("Pressed2");
				}
			}
		}
		if(option == '3')
		{
			gameO = '0';
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
	
	  return value.charAt(0);  
	}

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
	  float currentJH = 300;
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
		//System.out.println(option);
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
	        if(pos.y < gHeight)
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
		fill(255);
	    textSize(30);
	    text("Distance: " + distance+ "KM", 30, 50);
	    text("Power: " + acc + "LTR",30,100);
	    
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
	           p.pos.y = p.gHeight;
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
	  float SW = random(2,10);
	  float SX = random(0,1000);
	  float SY = random(0, 450);
	  float Sacc;
	   
	   Star(int gHeight)
	  {
	    SW = 5;
	    Sacc = 1f;
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
	        SY = random(0, 450);
	        SH = random(2,5);
	        SW = random(2,5);
	     }
	  }
	}
	
	 static public void main(String[] passedArgs) {
		    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "OPassignment2" };
		    if (passedArgs != null) {
		      PApplet.main(concat(appletArgs, passedArgs));
		    } else {
		      PApplet.main(appletArgs);
		    }
		  }
	
	
	
}
