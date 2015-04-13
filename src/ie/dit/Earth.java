package ie.dit;

public class Earth {
	
	int gHeight = 420;
	
	Assignment3 p;
	
	float EH;
	float EW;
	float EX;
	float EY;
	float Eacc;
	
	Earth(Assignment3 _p)
	{
		p = _p;
		gHeight = 420;
		EH = 400;
		EW = 100;
		EX = p.random(0 , 800);
		EY = 50;
		Eacc = 1f;
	}
	
	public void run()
	  {
	    move();
	    reset(); 
	  }
	  
	  //moves mountains... it must be love
	  public void move()
	  {
	     EX -= Eacc *(p.p.acc/100);
	  }
	  
	  //resets the mountains
	  public void reset()
	  {
	    if(EX < 0 - EW)
	    {
	       EX = p.random(1000,1500); 
	    }
	  
	  }

}
