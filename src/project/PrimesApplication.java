package project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import project.primeCalc.PrimeBruter;
import project.primeCalc.PrimeCalculator;
import project.primeCalc.SieveOfErathosthenes;
import project.primeUsage.PrimeUsage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimesApplication extends JFrame {
	private static final long serialVersionUID = 1L;

	private static PrimesApplication instance;

	private JPanel contentPane;
	private JTextPane textPane;
	private JComboBox cbxMethode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
					try {
						// Build the GUI
						instance = new PrimesApplication();
					} catch (Exception e) {
						error(e);
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			error(e);
		}

		// Add the content
		instance.addPrimeCalculator("Einfaches durchtesten", new PrimeBruter());
		instance.addPrimeCalculator("Sieb des Erathosthenes", new SieveOfErathosthenes());

		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Show the GUI
					instance.setVisible(true);
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
	 * Create the frame.
	 */
	public PrimesApplication() {
		setResizable(false);
		setTitle(R.GUI_WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel usePrimesPanel = new JPanel();
		usePrimesPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true), "Primzahlen benutzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		usePrimesPanel.setBounds(10, 181, 207, 159);
		contentPane.add(usePrimesPanel);
		usePrimesPanel.setLayout(null);

		JPanel calcPrimesPanel = new JPanel();
		calcPrimesPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true), "Primzahlen berechnen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		calcPrimesPanel.setBounds(10, 11, 207, 159);
		contentPane.add(calcPrimesPanel);
		calcPrimesPanel.setLayout(null);

		JLabel lblMethode = new JLabel("Methode:");
		lblMethode.setBounds(10, 22, 46, 14);
		calcPrimesPanel.add(lblMethode);

		cbxMethode = new JComboBox();
		cbxMethode.setBounds(66, 19, 131, 20);
		calcPrimesPanel.add(cbxMethode);

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appendConsoleText("Test: " + Math.random());
			}
		});
		btnTest.setBounds(311, 317, 80, 23);
		contentPane.add(btnTest);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearConsole();
			}
		});
		btnClear.setBounds(227, 317, 80, 23);
		contentPane.add(btnClear);

		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		textPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(227, 12, 217, 294);
		contentPane.add(scrollPane);
	}

	/**
	 * Clears the "Console".
	 */
	public void clearConsole() {
		textPane.setText("");
	}

	/**
	 * Used to append a line of text to the "Console" and System.out.
	 */
	public void appendConsoleText(String text) {
		textPane.setText(textPane.getText() + text + '\n');
		System.out.println("[TEXT] " + text);
	}

	/**
	 * Adds a Prime Calculation method.
	 */
	public void addPrimeCalculator(String name, PrimeCalculator primeCalc) {
		cbxMethode.addItem(name);
		// TODO: Store primeCalc somehow ... probably a HashMap!
	}

	/**
	 * Adds a Prime Usage.
	 */
	public void addPrimeUsage(String name, PrimeUsage primeUsage) {
		// TODO: Every Prime Usage should have It's own JPanel for options ... need to think about the structure!
	}

	/**
	 * We failed.
	 */
	public static void error(Throwable t) {
		t.printStackTrace();
		ByteArrayOutputStream byos = new ByteArrayOutputStream(1024 * 8);
		t.printStackTrace(new PrintStream(byos));
		JOptionPane.showMessageDialog(instance, byos.toString(), R.GUI_WINDOW_TITLE + ": Anwendungsfehler", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}
}
