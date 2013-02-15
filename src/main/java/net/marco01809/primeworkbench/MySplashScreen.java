package net.marco01809.primeworkbench;

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
			in = MySplashScreen.class.getResourceAsStream("/img/splash.png");
			splashImage = new ImageIcon(ImageIO.read(in)).getImage();
		}

		catch (IOException e) {
			error = true;
			return;
		}

		catch (IllegalArgumentException t) {
			error = true;
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