package project;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

import project.primeCalc.PrimeCalculator;
import project.primeUsage.PrimeUsage;

// We don't call this PrimesFrame, cause it does much more than just being a frame.
public class PrimesGUI extends JFrame implements UI {
	private static final long serialVersionUID = 1L;

	protected static final String GUI_WINDOW_TITLE = "Primzahlen-Berechnung";

	private JPanel contentPane;
	private JTextPane textPane;
	private JComboBox cbxMethode;
	private JCheckBox chckbxPrimzahlenAusgeben;
	private JTextField textFieldBerechnetBis;

	/**
	 * Stores the available PrimeCalculators.
	 */
	private Map<String, PrimeCalculator> primeCalculators = new HashMap<String, PrimeCalculator>();

	/**
	 * Stores the result of a prime calculation, for further use by a PrimeUsage.
	 */
	private boolean[] primes;

	/**
	 * Create the frame.
	 */
	public PrimesGUI() {
		setResizable(false);
		setTitle(GUI_WINDOW_TITLE);
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
		calcPrimesPanel.setBounds(10, 11, 207, 165);
		contentPane.add(calcPrimesPanel);
		calcPrimesPanel.setLayout(null);

		JLabel lblMethode = new JLabel("Methode:");
		lblMethode.setBounds(10, 22, 46, 14);
		calcPrimesPanel.add(lblMethode);

		JLabel lblBerechnePrimzahlenBis = new JLabel("Berechne Zahlen bis");
		lblBerechnePrimzahlenBis.setBounds(10, 52, 97, 14);
		calcPrimesPanel.add(lblBerechnePrimzahlenBis);

		chckbxPrimzahlenAusgeben = new JCheckBox("Primzahlen ausgeben");
		chckbxPrimzahlenAusgeben.setBounds(6, 73, 131, 23);
		calcPrimesPanel.add(chckbxPrimzahlenAusgeben);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(10000), new Integer(1), null, new Integer(100)));
		spinner.setBounds(111, 49, 86, 20);
		calcPrimesPanel.add(spinner);

		cbxMethode = new JComboBox();
		cbxMethode.setBounds(66, 19, 131, 20);
		cbxMethode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((SpinnerNumberModel) spinner.getModel()).setMaximum(primeCalculators.get(cbxMethode.getSelectedItem()).getHighestDeterminableNumber());
			}
		});
		calcPrimesPanel.add(cbxMethode);

		final JButton btnCalcStart = new JButton("Berechnung starten");
		btnCalcStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Disable the start button
				btnCalcStart.setEnabled(false);

				Thread t = new Thread("Prime Calculation") {
					public void run() {
						// Calculate the Primes
						final long timeBefore = System.currentTimeMillis();
						final boolean[] lastPrimes = primeCalculators.get(cbxMethode.getSelectedItem()).determinePrimes((Integer) spinner.getValue());
						final long tookTime = System.currentTimeMillis() - timeBefore;
						
						if (primes == null || lastPrimes.length > primes.length)
							primes = lastPrimes;

						// Re-enable the start button
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								println("Berechnung mit '" + cbxMethode.getSelectedItem() + "' für " + (lastPrimes.length - 1) + " Zahlen dauerte " + tookTime + " ms");

								textFieldBerechnetBis.setText(String.valueOf(primes.length - 1));

								btnCalcStart.setEnabled(true);
							}
						});
					}
				};
				t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						PrimesApplication.error(e, t, false);
					}
				});
				t.start();
			}
		});
		btnCalcStart.setBounds(66, 100, 131, 23);
		calcPrimesPanel.add(btnCalcStart);

		JLabel lblVerfgbarePrimzahlen = new JLabel("Zahlen berechnet bis:");
		lblVerfgbarePrimzahlen.setBounds(10, 140, 109, 14);
		calcPrimesPanel.add(lblVerfgbarePrimzahlen);

		textFieldBerechnetBis = new JTextField();
		textFieldBerechnetBis.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldBerechnetBis.setText("0");
		textFieldBerechnetBis.setEditable(false);
		textFieldBerechnetBis.setBounds(123, 137, 74, 20);
		calcPrimesPanel.add(textFieldBerechnetBis);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 128, 187, 2);
		calcPrimesPanel.add(separator);

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
		primeCalculators.put(primeCalc.getName(), primeCalc);
		cbxMethode.addItem(primeCalc.getName());
	}

	public void addPrimeUsage(PrimeUsage primeUsage) {
		// TODO: Different PrimeUsages could need different options
	}
}
