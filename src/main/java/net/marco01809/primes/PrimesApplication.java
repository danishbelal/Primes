package net.marco01809.primes;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import net.marco01809.primes.calculators.PrimeBruter;
import net.marco01809.primes.calculators.SieveOfErathosthenes;
import net.marco01809.primes.calculators.SieveOfEratosthenesBCD;
import net.marco01809.primes.primeUsage.GGT;
import net.marco01809.primes.primeUsage.KGV;
import net.marco01809.primes.primeUsage.PrimeFactorization;

public final class PrimesApplication implements Runnable {
	public static final String VERSION = "0.5-SNAPSHOT";

	private static PrimesGUI gui;
	private static int SPLASH_TIME = 3;
	private ImageIcon icon = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Thread t = new Thread(new PrimesApplication(), "PrimesApplication_main");
		t.setPriority(Thread.MAX_PRIORITY);
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				error(e, t, true);
			}
		});
		t.start();

	}

	/**
	 * Prepare and display the UI.
	 */
	public void run() {

		/**
		 * This Function Call will Block the GUI loading, for currently {@code SPLASH_TIME} Seconds.
		 * */
		new MySplashScreen(SPLASH_TIME).showSplash();
		long loadTimeBefore = System.currentTimeMillis();

		try {
			// Try to use the System's look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			// It's not that bad if it doesn't work, so don't exit
			error(e, false);
		}

		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Build the GUI
					gui = new PrimesGUI();
					// Set the Icon
					if (!loadIcon())
						;
//						do currently nothing if that happens...
					;

					// Add the content
					gui.addPrimeCalculator(new PrimeBruter(gui));
					gui.addPrimeCalculator(new SieveOfErathosthenes(gui));
					gui.addPrimeCalculator(new SieveOfEratosthenesBCD(gui));
					gui.addPrimeUsage(new GGT(gui));
					gui.addPrimeUsage(new KGV(gui));
					gui.addPrimeUsage(new PrimeFactorization(gui));

					// Display the GUI
					gui.setVisible(true);

//					gui.println("GUI Test");
				}
			});
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			error(e, true);
		}

		System.out.println("Load Duration: " + (System.currentTimeMillis() - loadTimeBefore) + "ms");
	}

	/**
	 * We failed.
	 */
	public static void error(Throwable e, Thread t, boolean shouldExit) {
		String threadError = "Error in Thread #" + t.getId() + " '" + t.getName() + "':";
		System.err.println(threadError);
		e.printStackTrace();
		if (!GraphicsEnvironment.isHeadless()) {
			ByteArrayOutputStream byos = new ByteArrayOutputStream(1024);
			e.printStackTrace(new PrintStream(byos));
			JOptionPane.showMessageDialog(gui, threadError + "\n\n" + byos.toString(), PrimesGUI.GUI_WINDOW_TITLE + ": Anwendungsfehler", JOptionPane.ERROR_MESSAGE);
		}
		if (shouldExit)
			System.exit(1);
	}

	public static void error(Throwable e, boolean shouldExit) {
		error(e, Thread.currentThread(), shouldExit);
	}

	private boolean loadIcon() {

		InputStream in = getClass().getClassLoader().getResourceAsStream("logo_48x48.png");
		if (in == null) {

			return false;
		}
		try {
			icon = new ImageIcon(ImageIO.read(in));
		}
		catch (IOException e) {
			return false;
		}
		gui.setIconImage(icon.getImage());
//		 Tell the calling func that the Icon has been loaded succesfully...
		return true;
	}

}
