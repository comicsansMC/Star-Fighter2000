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

public class Bullets
{
	private List<Ammo> ammo;

	public Bullets(){
		ammo = new ArrayList<>();
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	//post - draw each Ammo
	public void drawEmAll( Graphics window )
	{
		for(int i=0; i<ammo.size(); i++){
			ammo.get(i).draw(window);
		}
	}

	public void moveEmAll()
	{
		for(int i=0; i<ammo.size(); i++){
			ammo.get(i).move("UP");
		}
	}

	public void cleanEmUp()
	{	
	}

	public List<Ammo> getList()
	{
		return ammo;
	}

	public String toString()
	{
		String output = "";
		
		for(int i=0; i<ammo.size(); i++){
			output+=ammo.get(i);
			output+=" ";
		}
		
		return output;
	}
}
