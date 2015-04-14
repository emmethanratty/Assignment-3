package ie.dit;

public class Upgrade {
	
	Assignment3 p;
	
	float jumpP;
	  float accP;
	  float fuelP;
	  float moveP;
	  
	  char selected;

	  
	  Upgrade(Assignment3 _p)
	  {
		p = _p;
	    jumpP = 10;
	    accP = 10;
	    fuelP = 10;
	    moveP = 10;
	  }
	  
	  public void run()
	  {
	     display();
	     update(); 
	  }
	  
	  
	  public void display()
	  {
		  
	  }
	  
	  public void update()
	  {
		  switch(selected)
	      {
	        case '0':
	        {
	           if( p.gameover.upgradeT > jumpP)
	           {
	             p.p.currentJH -= 10;
	             p.gameover.upgradeT = p.gameover.upgradeT - jumpP;
	             selected = '5';
	             jumpP = jumpP * 1.2f;
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Upgraded!", p.width/2 - 100 ,p.height/2); 
	           }
	           else
	           {
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Not Enough upgrade Points", p.width/2 - 100 ,p.height/2); 
	           }
	           break;
	        }
	        case '1':
	        {
	           if( p.gameover.upgradeT > accP)
	           {
	             p.p.Cacc += 20;
	             p.gameover.upgradeT = p.gameover.upgradeT - accP;
	             selected = '5';
	             accP = accP * 1.2f;
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Upgraded!", p.width/2 - 100 ,p.height/2); 
	           }
	           else
	           {
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Not Enough Upgrade Points", p.width/2 - 100 ,p.height/2);  
	           } 
	           break;
	        }
	        case '2':
	        {
	          //if upgrade is more then the price upgrades
	          if( p.gameover.upgradeT > fuelP)
	           {
	             p.fuel.frequency -= 20;
	             p.gameover.upgradeT = p.gameover.upgradeT - fuelP;
	             p.println("worked");
	             selected = '5';
	             fuelP = fuelP * 1.2f;
	              p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Upgraded!", p.width/2 - 100 ,p.height/2); 
	           }
	           else
	           {
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Not Enough Upgrade Points", p.width/2 - 100 ,p.height/2);  
	           }
	           break;
	        }
	        case '3':
	        {
	          //if upgrade is more then the price upgrades
	          if( p.gameover.upgradeT > moveP)
	           {
	             p.p.moveS++;
	             p.gameover.upgradeT = p.gameover.upgradeT - moveP;
	             p.println("worked");
	             selected = '5';
	             moveP = moveP * 1.2f;
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Upgraded!", p.width/2 - 100 ,p.height/2);  
	           }
	           else
	           {
	             p.fill(255,0,0);
	             p.textSize(30);
	             p.text("Not Enough Upgrade Points", p.width/2 - 100 ,p.height/2);  
	           }
	           break;
	        }      
	      }
	    }
	  }


