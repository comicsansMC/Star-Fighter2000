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
		for (int i= 0; i < size; i++){
			add(new Alien(100 + (50 *i), 200, 50, 50, 2));
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
