package project;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import project.primeCalc.PrimeBruter;
import project.primeCalc.PrimeCalculator;
import project.primeCalc.SieveOfErathosthenes;
import project.primeUsage.PrimeUsage;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class PrimesApplication extends JFrame implements UI {
	private static final long serialVersionUID = 1L;

	private static PrimesApplication instance;

	private JPanel contentPane;
	private JTextPane textPane;
	private JComboBox cbxMethode;
	private JCheckBox chckbxPrimzahlenAusgeben;

	private Map<String, PrimeCalculator> primeCalculators = new HashMap<String, PrimeCalculator>();

	/**
	 * Stores the result of a prime calculation, for further use by a PrimeUsage.
	 */
	private boolean[] primes;

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
		instance.addPrimeCalculator(new PrimeBruter(instance));
		instance.addPrimeCalculator(new SieveOfErathosthenes(instance));

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

		JLabel lblBerechnePrimzahlenBis = new JLabel("Berechne alle Primzahlen im Bereich");
		lblBerechnePrimzahlenBis.setBounds(10, 52, 187, 14);
		calcPrimesPanel.add(lblBerechnePrimzahlenBis);

		chckbxPrimzahlenAusgeben = new JCheckBox("Primzahlen ausgeben");
		chckbxPrimzahlenAusgeben.setBounds(6, 101, 131, 23);
		calcPrimesPanel.add(chckbxPrimzahlenAusgeben);

		JLabel lblVon = new JLabel("von");
		lblVon.setBounds(10, 74, 18, 14);
		calcPrimesPanel.add(lblVon);

		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setEditable(false);
		textField.setText("0");
		textField.setBounds(34, 71, 35, 20);
		calcPrimesPanel.add(textField);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(101, 71, 88, 20);
		calcPrimesPanel.add(spinner);
		
		cbxMethode = new JComboBox();
		cbxMethode.setBounds(66, 19, 131, 20);
		cbxMethode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Problem: This will be called before the PrimeCalculators are added, causing a NPE.
				// We cannot avoid this, cause we need an initial maximum value for the spinner.
				// Probably restructuring :/
				((SpinnerNumberModel) spinner.getModel()).setMaximum(primeCalculators.get(cbxMethode.getSelectedItem()).getHighestDeterminableNumber());
			}
		});
		calcPrimesPanel.add(cbxMethode);

		JLabel lblBis = new JLabel("bis");
		lblBis.setBounds(79, 74, 26, 14);
		calcPrimesPanel.add(lblBis);

		final JButton btnCalcStart = new JButton("Berechnung starten");
		btnCalcStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Disable the start button
				btnCalcStart.setEnabled(false);

				new Thread("Prime Calculation") {
					public void run() {
						// Calculate the Primes
						primes = primeCalculators.get(cbxMethode.getSelectedItem()).determinePrimes((Integer) spinner.getValue());

						// Re-enable the start button
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								btnCalcStart.setEnabled(true);
							}
						});
					}
				}.start();
			}
		});
		btnCalcStart.setBounds(66, 125, 131, 23);
		calcPrimesPanel.add(btnCalcStart);

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				println("Test: " + Math.random());
			}
		});
		btnTest.setBounds(311, 317, 80, 23);
		contentPane.add(btnTest);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearText();
			}
		});
		btnClear.setBounds(227, 317, 80, 23);
		contentPane.add(btnClear);

		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		textPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(227, 12, 217, 298);
		contentPane.add(scrollPane);
	}

	/*
	 * UI
	 */

	public void clearText() {
		textPane.setText("");
	}

	public void print(final String text) {
		System.out.print(text);
		if (SwingUtilities.isEventDispatchThread()) {
			textPane.setText(textPane.getText() + text);
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					textPane.setText(textPane.getText() + text);
				}
			});
		}
	}

	public void println(String text) {
		print(text + '\n');
	}

	public void determinedPrime(int prime) {
		if (chckbxPrimzahlenAusgeben.isSelected())
			println("Primzahl: " + String.valueOf(prime));
	}

	public void addPrimeCalculator(PrimeCalculator primeCalc) {
		cbxMethode.addItem(primeCalc.getName());
		primeCalculators.put(primeCalc.getName(), primeCalc);
	}

	public void addPrimeUsage(PrimeUsage primeUsage) {
		// TODO: Different PrimeUsages could need different options
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
