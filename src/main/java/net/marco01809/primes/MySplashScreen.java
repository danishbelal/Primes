package net.marco01809.primes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import static net.marco01809.primes.PrimesApplication.error;

public class MySplashScreen extends JWindow
{

	private static final long serialVersionUID = 1L;
	private final int timeOut;

	Image splashImage = null;

	public MySplashScreen(int seconds)
	{

		timeOut = seconds;
		InputStream in = getClass().getResourceAsStream("/splash.png");
		try
		{
			splashImage = new ImageIcon(ImageIO.read(in)).getImage();
		} catch (IOException e)
		{

			error(e, false);
		}

		setSize(410, 231 );
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

	}

	public void showSplash()
	{
		setVisible(true);

		try
		{
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e)
		{
			PrimesApplication.error(e, false);
		}

		dispose();
	}

	public void paint(Graphics g)
	{

		g.drawImage(splashImage, 0, 0, this);
		
	}
}