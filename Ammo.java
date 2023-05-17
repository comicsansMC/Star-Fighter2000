//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
	private int speed;
	
	public Ammo()
	{
		this(0,0,0);
	}

	public Ammo(int x, int y)
	{
		super(x, y);
	}

	public Ammo(int x, int y, int s)
	{
		super(x,y);
		speed = s;
	}

	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		window.setColor(Color.WHITE);
		window.fillRect(this.getX(), this.getY(), 5, 10);
	}
	
	
	public void move( String direction )
	{
		if(direction.equals("UP")){
			this.setY(this.getY()-speed);
		}
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
