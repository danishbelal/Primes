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

public final class PrimesApplication implements Runnable {
	private static PrimesGUI gui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new PrimesApplication(), "PrimesApplication main");
		t.setDaemon(false);
		t.setPriority(Thread.MAX_PRIORITY);
		t.setUncaughtExceptionHandler(primesExceptionHandler);
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
			// It's not that bad if it doesn't work
			e.printStackTrace();
		}

		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Build the GUI
					gui = new PrimesGUI();
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			error(e);
		}

		// Add the content
		gui.addPrimeCalculator(new PrimeBruter(gui));
		gui.addPrimeCalculator(new SieveOfErathosthenes(gui));

		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Show the GUI
					gui.setVisible(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			error(e);
		}

		System.out.println("Loading took " + (System.currentTimeMillis() - loadTimeBefore) + "ms");
	}

	/**
	 * We failed.
	 */
	public static void error(Throwable e, Thread t) {
		String threadError = "Error in Thread #" + t.getId() + " '" + t.getName() + "':";
		System.err.println(threadError);
		e.printStackTrace();
		ByteArrayOutputStream byos = new ByteArrayOutputStream(1024 * 8);
		e.printStackTrace(new PrintStream(byos));
		JOptionPane.showMessageDialog(gui, threadError + "\n\n" + byos.toString(), PrimesGUI.GUI_WINDOW_TITLE + ": Anwendungsfehler", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

	public static void error(Throwable e) {
		error(e, Thread.currentThread());
	}

	protected static final UncaughtExceptionHandler primesExceptionHandler = new UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			error(e, t);
		}
	};
}
