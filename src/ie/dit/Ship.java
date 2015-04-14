package ie.dit;

class Ship
{
	int gHeight = 420;
	
	Assignment3 p;
	
	float SH;
	float SW;
	float SX;
	float SY;
	float Sacc;
	
	Ship(Assignment3 _p)
	{
		p = _p;
		gHeight = 420;
		SH = 100;
		SW = 300;
		SX = p.random(0 , 800);
		SY = 50;
		Sacc = 1f;
	}
	
	public void run()
	  {
	    move();
	    reset(); 
	  }
	  
	  //moves mountains... it must be love
	  public void move()
	  {
	     SX -= Sacc *(p.p.acc/100);
	  }
	  
	  //resets the mountains
	  public void reset()
	  {
	    if(SX < 0 - SW)
	    {
	       SX = p.random(2000,5000); 
	       SY = p.random(0, 300);
	    }
	  
	  }

}