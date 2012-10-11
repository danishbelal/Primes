package project;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.EventQueue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;

import project.primeCalc.PrimeBruter;
import project.primeCalc.SieveOfErathosthenes;
import project.primeUsage.GGT;
import project.primeUsage.KGV;
import project.primeUsage.PrimeFactorization;

public final class PrimesApplication implements Runnable {
	private static PrimesGUI gui;

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
		long loadTimeBefore = System.currentTimeMillis();

		try {
			// Try to use the System's look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// It's not that bad if it doesn't work, so don't exit
			error(e, false);
		}

		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Build the GUI
					gui = new PrimesGUI();

					// Add the content
					gui.addPrimeCalculator(new PrimeBruter(gui));
					gui.addPrimeCalculator(new SieveOfErathosthenes(gui));
					gui.addPrimeUsage(new GGT());
					gui.addPrimeUsage(new KGV());
					gui.addPrimeUsage(new PrimeFactorization());

					// Display the GUI
					gui.setVisible(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			error(e, true);
		}

		System.out.println("Loading took " + (System.currentTimeMillis() - loadTimeBefore) + "ms");
	}

	/**
	 * We failed.
	 */
	public static void error(Throwable e, Thread t, boolean shouldExit) {
		String threadError = "Error in Thread #" + t.getId() + " '" + t.getName() + "':";
		System.err.println(threadError);
		e.printStackTrace();
		ByteArrayOutputStream byos = new ByteArrayOutputStream(1024);
		e.printStackTrace(new PrintStream(byos));
		JOptionPane.showMessageDialog(gui, threadError + "\n\n" + byos.toString(), PrimesGUI.GUI_WINDOW_TITLE + ": Anwendungsfehler", JOptionPane.ERROR_MESSAGE);
		if (shouldExit)
			System.exit(1);
	}

	public static void error(Throwable e, boolean shouldExit) {
		error(e, Thread.currentThread(), shouldExit);
	}
}
