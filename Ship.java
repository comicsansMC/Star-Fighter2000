//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;
	int count= 0;

	public Ship()
	{
		this(10,10,10,10,10);
	}

	public Ship(int x, int y)
	{
	   super(x,y);
	}

	public Ship(int x, int y, int s)
	{
	   super(x,y);
	   speed = s;
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/images/ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.print("Something donked up with the ship image");
		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void move(String direction)
	{
		if(direction.equals("LEFT")){
			this.setX(this.getX()-getSpeed());
		}
		if(direction.equals("RIGHT")){
			this.setX(this.getX()+speed);
		}
		if(direction.equals("UP")){
			this.setY(this.getY()-speed);
		}
		if(direction.equals("DOWN")){
			this.setY(this.getY()+speed);
		}
	}

	public void draw( Graphics window )
	{
   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public int timesShot(List<Ammo> shots)
	{
			for(int j = 0; j < shots.size(); j++){
				if(this.getY() >= shots.get(j).getY()){
					if(this.getY() - this.getHeight() <= shots.get(j).getY()){
					if(this.getX() <= shots.get(j).getX() && this.getX() + this.getWidth() >= shots.get(j).getX()){
						count += 1;
						shots.remove(j);
						System.out.println("Shot" + count);
					}
					}
			}
		}
		return count;
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
