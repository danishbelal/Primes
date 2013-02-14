package net.marco01809.primes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JWindow;

public class MySplashScreen extends JWindow {

	private static final long serialVersionUID = 1L;
	private final int timeOut;

	Image splashImage = null;
	private boolean error = false;
	InputStream in;

	public MySplashScreen(int seconds) {

		timeOut = seconds;
		try {
			in = getClass().getClassLoader().getResourceAsStream("resources/splash.png");
			splashImage = new ImageIcon(ImageIO.read(in)).getImage();

		}
		catch (IOException e) {

//			I dont want to see Error Messages any more ! 
//			PrimesApplication.error(e, false);
			error = true;
			return;
		}

		catch (Throwable t) {
			error = true;
			PrimesApplication.error(t, false);
			return;
		}

		setSize(410, 231);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

	}

	public void showSplash() {
		setVisible(true);

//		If an Error occured the splash screen wont be displayed.
		if (error) {
			dispose();
			return;
		}
		try {
			Thread.sleep(timeOut * 1000);
		}
		catch (InterruptedException e) {
			dispose();
		}

		dispose();
	}

	public void paint(Graphics g) {

		g.drawImage(splashImage, 0, 0, this);

	}
}