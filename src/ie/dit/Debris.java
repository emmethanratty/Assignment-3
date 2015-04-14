package ie.dit;

class Debris {
	
	  Assignment3 p;	
	
	  int gHeight;
	  int DW;
	  int DH;
	  float DX;
	  float DY;
	  float Dacc;
	   
	  Debris(Assignment3 _p)
	  {
		p = _p;  
	    Dacc = 4;
	    DW = 50;
	    DH = 50;
	    DX = p.random(500,1000);
	    DY = p.random(100  , gHeight);
	    gHeight = 420;
	  }
	  
	  public void run()
	  {
	     move(); 
	     reset();
	     display();
	  }
	  
	  //moves the core
	  public void move()
	  {
	    DX -= Dacc *(p.p.acc/100);
	  }
	  
	  //rsets the debris
	  public void reset()
	  {
	     if(DX < 0 - DW)
	     {
	        DX = p.random(2000,15000);
	        DY = p.random(100  , gHeight);
	     }
	  }
	  
	  public void display()
	  {      
		     p.translate(DX, DY);
		       
		     p.rotate(p.counter*p.TWO_PI/360);
		     
		     p.translate(-DW/2, -DW/2);
		       
		     p.image(p.debrisI,0,0,DW,DH);
		     
		     p.counter += 15;
	  }
	}

