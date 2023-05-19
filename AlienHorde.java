//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
	private List<Alien> aliens = new ArrayList<>();
	String direction ="LEFT";

	public AlienHorde(int size)
	{
		int firstLines = (600-(10*50)) / (11);
		
		int amountOfFirsts = 0;
		
		if(size>10){
			amountOfFirsts = size/10;
		}
		
		int spaceBetween = (600-((size-(amountOfFirsts*10))*50))/((size-(amountOfFirsts*10))+1);
		
		// for (int i= 0; i < size; i++){
		// 	if (i <10){
		// 	add(new Alien(100 + (50 *i), 50, 50, 50, 2));
		// 	} else if(i < 20){
		// 		add(new Alien(100 + (50 * (i - 10)), 100, 50, 50, 2));	
		// 	}else if(i < 30){
		// 		add(new Alien(100 + (50 * (i - 20)), 150, 50, 50, 2));	
		// 	}else if(i < 40){
		// 		add(new Alien(100 + (50 * (i - 30)), 200, 50, 50, 2));	
		// 	}else if(i < 50){
		// 		add(new Alien(100 + (50 * (i - 40)), 250, 50, 50, 2));	
		// 	}else if(i < 60){
		// 		add(new Alien(100 + (50 * (i - 50)), 300, 50, 50, 2));	
		// 	}
		// }

		if(amountOfFirsts>0){
			for(int i=0; i<amountOfFirsts; i++){
				
				for(int j=0; j<10; j++){
					add(new Alien(100 + firstLines*(j+1) + (50*j) - 12, 50 + (50*i), 50, 50, 1));
				}
				
				
				
				
			}
		}
		if(amountOfFirsts>0){
			for(int i=0; i<(size-amountOfFirsts*10); i++){
				add(new Alien(100+ spaceBetween*(i+1) + (50*i) - 12, 50 + (50*amountOfFirsts), 50, 50, 1));
			}
		}
		else{
			int smallBetween = (800-(size*50))/(size+1);
			
			for(int i=0; i<size; i++){
				add(new Alien(smallBetween*(i+1) + (50*i) -12, 50, 50, 50 , 1));
			}
		}
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(int i = 0; i < aliens.size();i++){
			aliens.get(i).draw(window);
		}
	}

	public void moveEmAll()
	{
		for(int i = 0; i < aliens.size();i++){
			aliens.get(i).move(direction);
			if(aliens.get(i).getX() == 0){
				direction = "RIGHT";
			} else if (aliens.get(i).getX() == 726){
				direction = "LEFT";
			}
		}
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
	}

	public String toString()
	{
		return "";
	}
}
