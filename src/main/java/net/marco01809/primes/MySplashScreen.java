package net.marco01809.primes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JWindow;

public class MySplashScreen extends JWindow implements Runnable
{
	/**
	 *  Source: http://www.tutorials.de/java/146899-splash-screen.html
	 */
	private static final long serialVersionUID = 1L;

	public void run()
	{
		setSize(400, 231);
//		Place Window in Center... 
		setLocationRelativeTo(null);
		setVisible(true);


		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			dispose();
		}
		dispose(); 
	}

	public void paint(Graphics g)
	{
		Image splashImage = getToolkit().getImage(".\\img\\splash.png");
		g.drawImage(splashImage, 0, 0, this);
	}
}