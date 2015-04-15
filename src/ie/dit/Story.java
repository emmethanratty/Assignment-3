package ie.dit;

class Story {
	
	Assignment3 p;
	
	Story(Assignment3 _p)
	{
		p = _p;
	}
	
	public void run()
	{
		display();
	}
	
	public void display()
	{
		p.image(p.storyI, 30,30, 800, 500);
		p.image(p.back, 10, 550, 100, 30);
		p.image(p.spaceman, 850, 500);
	}

}
