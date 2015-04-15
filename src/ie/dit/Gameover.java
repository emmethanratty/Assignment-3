package ie.dit;

class Gameover
{
	
  Assignment3 p;
  
  String CHSS;
  String NHS;
  
  float upgradeP;
  float upgradeT;
  int counter;
  float CHS;
  float NS;
     
  Gameover(Assignment3 _p)
  {
	p = _p; 
	
	  
    upgradeP = 0;
    upgradeT = 0;
    counter = 0;
    
  }
  
  
  public void run()
  {
     update(); 
     upgradeP();
     display(); 
     
     //loading high score from file
     String lines[] = p.loadStrings("Highscore.txt");

     String CHSS = lines[0];
      
     CHS =  Float.parseFloat(CHSS);
     p.text("High Score: " + CHS, 500,500);
  }

  
  
  public void upgradeP()
  {
    counter++;
    //only executes once
    if(counter == 1)
    {
      upgradeP = p.p.Gdis/100;
      upgradeT = upgradeT + upgradeP;
      
      NS = p.p.Gdis;
      
      p.over.rewind();
  	  p.over.play();
      
      //if the new score if bigger then current high score it sets it to new score
      if(NS > CHS)
      {
         String NHS = Float.toString(NS);
         String[] list = p.split(NHS,' ');
         p.saveStrings("Highscore.txt", list);
         //p.println(list);
      }//end inner if 
    } 
  }
  public void display()
  {
    p.textSize(30);
    p.text("Click Anywhere to Return to the Menu", 0,p.height - p.height/4);
    p.text("You scored " + upgradeP + " Upgrade Points", p.width/2 - 30,p.height/2);
  }
  
  
  public void update()
  {
    //resets game
    if (p.gameO == '0')
    {
      p.option = '0';
    }
    //goes to main menu
    if (p.gameO == '1')
    {
      p.option = '1';
    }   
  } 
}
