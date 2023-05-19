//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private Bullets bullets;
	
	/* uncomment once you are ready for this part
	*
	private Bullets shots;
	*/
	private AlienHorde horde;
	
	private boolean[] keys;
	private BufferedImage back;
	String alienDirection = "LEFT";

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables
		//Ship, Alien
		
		
		ship = new Ship(400, 300, 100, 100, 5);
		// alienOne = new Alien(200, 100, 50, 50, 2);
		// alienTwo = new Alien(600, 100, 50, 50, 2);

		horde = new AlienHorde(20);

		bullets = new Bullets();

		this.addKeyListener(this);
		new Thread(this).start();
		
		setVisible(true);
	}

   public void update(Graphics window){
   paint(window);
	}

public void paint( Graphics window )
{
	//set up the double buffering to make the game animation nice and smooth

		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);


		if(keys[0] == true)
		{
			ship.move("LEFT");
		}

		//add code to move Alien, etc.

		if(keys[1]){
			ship.move("RIGHT");
		}
		
		if(keys[2]){
			ship.move("UP");
		}
		
		if(keys[3]){
			ship.move("DOWN");
		}
		
		// alienOne.move(alienDirection);
		// alienTwo.move(alienDirection);
		if(keys[4]){
			Ammo bullet = new Ammo(ship.getX() + ship.getWidth()/2, ship.getY(), 5);
			bullets.add(bullet);
			keys[4] = false;
		}
		
		ship.draw(twoDGraph);
		// alienOne.draw(window);
		// alienTwo.draw(window);
		// alienOne.move(alienDirection);
		// alienTwo.move(alienDirection);
		
		// if(alienOne.getX() == 0){
			// 	alienDirection ="RIGHT";
			// } else if (alienTwo.getX() == 726){
				// 	alienDirection ="LEFT";
				// }
		if(bullets.getList() != null){
		bullets.drawEmAll(twoDGraph);	
		}
		
		bullets.moveEmAll();
				
		horde.drawEmAll(twoDGraph);
		horde.moveEmAll();

		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship


		twoDGraph.drawImage(back, null, 0, 0);

	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

