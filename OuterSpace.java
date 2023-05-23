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
import java.io.RandomAccessFile;
import java.net.URL;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;

import javax.imageio.ImageIO;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private Bullets bullets;
	private Bullets alienShots;

	private Image image;
	
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
		
		
		ship = new Ship(400, 300, 75, 75, 3);
		// alienOne = new Alien(200, 100, 50, 50, 2);
		// alienTwo = new Alien(600, 100, 50, 50, 2);
		
		horde = new AlienHorde(25);
		
		bullets = new Bullets();
		alienShots = new Bullets();
		alienShots();
		
		this.addKeyListener(this);
		new Thread(this).start();
		
		setVisible(true);
	}
	
	public void update(Graphics window)
	{
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
		if(ship.timesShot(alienShots.getList()) < 3){
		graphToBack.fillRect(0,0,800,600);

		graphToBack.setColor(Color.WHITE);
		graphToBack.drawString("Lives " + (3-ship.timesShot(alienShots.getList())), 5, 15);

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
			Ammo bullet = new Ammo(ship.getX() + (ship.getWidth()/2), ship.getY(), 5);
			bullets.add(bullet);
			keys[4]=false;
		}
		
		ship.draw(graphToBack);
		
		// alienOne.draw(window);
		// alienTwo.draw(window);
		// alienOne.move(alienDirection);
		// alienTwo.move(alienDirection);
		
		// if(alienOne.getX() == 0){
			// 	alienDirection ="RIGHT";
			// } else if (alienTwo.getX() == 726){
				// 	alienDirection ="LEFT";
				// }
		bullets.moveEmAll("UP");
		if(bullets.getList() != null){
		bullets.drawEmAll(graphToBack);	
		}
		
				
		
		
		horde.moveEmAll();
		
		horde.drawEmAll(graphToBack);

		horde.removeDeadOnes(bullets.getList());
		
	
	alienShots.moveEmAll("DOWN");

	if(alienShots.getList() != null){
	alienShots.drawEmAll(graphToBack);
	}
		twoDGraph.drawImage(back, null, 0, 0);

	} else {
		//twoDGraph.drawString("GAME OVER", x, y);

		try
		{
			URL url = getClass().getResource("/images/GameOver.PNG");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.print("Something donked up with the gameOver image");
		}

		twoDGraph.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}
	}

	public void alienShots(){
		Timer timer = new Timer();
		timer.schedule(new TimedAlienShots(), 500, 500);
	}

	class TimedAlienShots extends TimerTask {
		public void run() {
			Ammo bullet = new Ammo(horde.getList().get(getRandomNumber(0, horde.getList().size())).getX() +  horde.getList().get(getRandomNumber(0, horde.getList().size())).getWidth()/2, horde.getList().get(getRandomNumber(0, horde.getList().size())).getY(), 5);
			alienShots.add(bullet);
		}
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
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

