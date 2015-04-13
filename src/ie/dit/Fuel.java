package ie.dit;

class Fuel
{
	
  Assignment3 p;
	
  int gHeight;
  int FW;
  int FH;
  float FX;
  float FY;
  float Facc;
  float frequency;
   
  Fuel(Assignment3 _p)
  {
	p = _p;  
    Facc = 4;
    frequency = 5000;
    FW = 30;
    FH = 30;
    FX = p.random(500,1000);
    FY = p.random(100  , gHeight);
    gHeight = 420;
  }
  
  public void run()
  {
     move(); 
     reset();
  }
  
  //moves the core
  public void move()
  {
    FX -= Facc *(p.p.acc/100);
  }
  
  //rsets the core
  public void reset()
  {
     if(FX < 0 - FW)
     {
        FX = p.random(2000,frequency);
        FY = p.random(100  , gHeight);
     }
  }
}
