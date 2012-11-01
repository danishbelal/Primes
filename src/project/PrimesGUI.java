package project;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import project.primeCalc.PrimeCalculator;
import project.primeUsage.PrimeUsage;

// TODO: Recreate GUI with LayoutManager => resizable
// TODO: Some useful graphical statistics
// We don't call this PrimesFrame, cause it does much more than just being a frame.
public class PrimesGUI extends JFrame implements UI {
	private static final long serialVersionUID = 1L;

	protected static final String GUI_WINDOW_TITLE = "Primzahlen-Berechnung " + PrimesApplication.VERSION;

	protected static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#,###,###,##0");

	private JPanel contentPane;
	private JTextArea textPane;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxMethode, cbxUsage;
	private JCheckBox chckbxPrimzahlenAusgeben;
	private JTextField textFieldBerechnetBis;
	private JSpinner spinner;
	private JButton btnCalcStart;
	private JButton btnExport;
	private JButton btnClear;

	/**
	 * Stores the result of a prime calculation, for further use by a PrimeUsage.
	 */
	private boolean[] primes;

	/**
	 * Buffers the GUI Console text output.
	 */
	private StringBuilder textBuffer = new StringBuilder(1024 * 4); // 4kb should be enough

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public PrimesGUI() {
		setResizable(false);
		setTitle(GUI_WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnExport = new JButton("Primzahlen exportieren");
		btnExport.setToolTipText("Exportiert die Primzahlen in eine portable Datei.");
		btnExport.setEnabled(false);
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPrimes();
			}
		});
		btnExport.setBounds(311, 317, 142, 23);
		contentPane.add(btnExport);

		JPanel usePrimesPanel = new JPanel();
		usePrimesPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true), "Primzahlen benutzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		usePrimesPanel.setBounds(10, 181, 207, 159);
		contentPane.add(usePrimesPanel);
		usePrimesPanel.setLayout(null);

		cbxUsage = new JComboBox();
		cbxUsage.setBounds(10, 19, 187, 20);
		usePrimesPanel.add(cbxUsage);

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
		chckbxPrimzahlenAusgeben.setToolTipText("Gibt die berechneten Primzahlen in der Konsole aus.");
		chckbxPrimzahlenAusgeben.setBounds(6, 73, 131, 23);
		calcPrimesPanel.add(chckbxPrimzahlenAusgeben);

		spinner = new JSpinner();
		spinner.setToolTipText("Gibt an, bis zu welcher Zahl berechnet werden soll. Umso höher, umso zeitaufwändiger die Berechnung.");
		spinner.setModel(new SpinnerNumberModel(new Integer(10000), new Integer(1), null, new Integer(100)));
		spinner.setBounds(111, 49, 86, 20);
		calcPrimesPanel.add(spinner);

		cbxMethode = new JComboBox();
		cbxMethode.setToolTipText("Wählt die Methode aus, mit der Primzahlen berechnet werden sollen.");
		cbxMethode.setBounds(66, 19, 131, 20);
		cbxMethode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int highestDeteminableNumber = ((PrimeCalculator) cbxMethode.getSelectedItem()).getHighestDeterminableNumber();
				SpinnerNumberModel spinnerModel = (SpinnerNumberModel) spinner.getModel();

				spinnerModel.setMaximum(highestDeteminableNumber);
				if ((Integer) spinnerModel.getNumber() > highestDeteminableNumber)
					spinnerModel.setValue(highestDeteminableNumber - (highestDeteminableNumber % (Integer) spinnerModel.getStepSize()));
			}
		});
		calcPrimesPanel.add(cbxMethode);

		btnCalcStart = new JButton("Berechnung starten");
		btnCalcStart.setToolTipText("Startet die Berechnung.");
		btnCalcStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPrimeCalculation();
			}
		});
		btnCalcStart.setBounds(66, 100, 131, 23);
		calcPrimesPanel.add(btnCalcStart);

		JLabel lblVerfgbarePrimzahlen = new JLabel("Zahlen berechnet bis:");
		lblVerfgbarePrimzahlen.setBounds(10, 140, 109, 14);
		calcPrimesPanel.add(lblVerfgbarePrimzahlen);

		textFieldBerechnetBis = new JTextField();
		textFieldBerechnetBis.setToolTipText("Zeigt die Anzahl der berechneten Primzahlen.");
		textFieldBerechnetBis.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldBerechnetBis.setText("0");
		textFieldBerechnetBis.setEditable(false);
		textFieldBerechnetBis.setBounds(123, 137, 74, 20);
		calcPrimesPanel.add(textFieldBerechnetBis);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 128, 187, 2);
		calcPrimesPanel.add(separator);

		btnClear = new JButton("Clear");
		btnClear.setToolTipText("Leert den Inhalt der Ereignisanzeige.");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearText();
			}
		});
		btnClear.setBounds(227, 317, 80, 23);
		contentPane.add(btnClear);

		textPane = new JTextArea();
		textPane.setToolTipText("Zeigt die Ereignisse und Berechnungen an.");
		textPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		textPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(227, 12, 226, 298);
		contentPane.add(scrollPane);
	}

	/* *************************************************************************
	 * UI
	 */

	public void clearText() {
		if (SwingUtilities.isEventDispatchThread()) {
			textPane.setText("");
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					textPane.setText("");
				}
			});
		}

		synchronized (textBuffer) {
			textBuffer.delete(0, textBuffer.capacity());
		}
	}

	/*
	 * Direct text output - directly prints text to the textPane.
	 */
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

	/*
	 * Buffered text output - writes to the buffer. If it's full, it will be flushed by printing the buffer contents to the direct text output.
	 */
	public void printBuffered(final String text) {
		synchronized (textBuffer) {
			if (textBuffer.length() + text.length() > textBuffer.capacity())
				flushBuffer();
			textBuffer.append(text);
		}
	}

	public void printlnBuffered(final String text) {
		printBuffered(text + '\n');
	}

	public void flushBuffer() {
		synchronized (textBuffer) {
			print(textBuffer.toString());
			textBuffer.delete(0, textBuffer.capacity());
		}
	}

	public void determinedPrime(int prime) {
		if (chckbxPrimzahlenAusgeben.isSelected())
			this.printlnBuffered("Primzahl: " + String.valueOf(prime));
	}

	public void setActionComponentsEnabled(final boolean enabled) {
		if (!SwingUtilities.isEventDispatchThread()) {
			// invokeAndWait because the method calling this method likely expects
			// the components to be disabled instantly after returning from this method
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						setActionComponentsEnabled(enabled);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				PrimesApplication.error(e, false);
			}
			return;
		}

		btnCalcStart.setEnabled(enabled);
		btnExport.setEnabled(enabled && primes != null);
	}

	@SuppressWarnings("unchecked")
	public void addPrimeCalculator(PrimeCalculator primeCalc) {
		if (this.isVisible()) // Adding these while the user is able to access the GUI could cause threading problems.
			throw new IllegalStateException("Cannot add a PrimeCalculator if the GUI is visible");
		cbxMethode.addItem(primeCalc);
	}

	@SuppressWarnings("unchecked")
	public void addPrimeUsage(PrimeUsage primeUsage) {
		if (this.isVisible()) // Adding these while the user is able to access the GUI could cause threading problems.
			throw new IllegalStateException("Cannot add a PrimeUsage if the GUI is visible");
		cbxUsage.addItem(primeUsage);
	}

	/* *************************************************************************
	 * Actions
	 */

	protected void startPrimeCalculation() {
		// Not inside the Runnable, because the EDT should grab this values.
		final PrimeCalculator primeCalc = (PrimeCalculator) cbxMethode.getSelectedItem();
		final int determineMax = (Integer) spinner.getValue();

		runAction("Berechnung", new Runnable() {
			public void run() {
				// Format the number for better legibility
				final String numberAmountString = NUMBER_FORMAT.format(determineMax);
				println("Berechnung mit '" + primeCalc.getName() + "' für " + numberAmountString + " Zahlen gestartet");

				// Get the time before the calculation
				long timeBefore = System.currentTimeMillis();

				// Determine the Primes
				boolean[] lastPrimes = primeCalc.determinePrimes(determineMax);

				// Format the used time for better legibility
				final String tookTimeString = NUMBER_FORMAT.format(System.currentTimeMillis() - timeBefore);

				// Flush the console buffer after calculation finished
				PrimesGUI.this.flushBuffer(); // Special syntax to access the instance of the outer-class

				// Only set the primes if the determined primes got improved
				if (primes == null || lastPrimes.length > primes.length)
					primes = lastPrimes;

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						println("Berechnung dauerte " + tookTimeString + " ms");

						textFieldBerechnetBis.setText(numberAmountString);
					}
				});
			}
		});
	}

	protected void exportPrimes() {
		runAction("Export", new Runnable() {
			public void run() {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setMultiSelectionEnabled(false);
				int fcOption = fc.showSaveDialog(PrimesGUI.this);

				if (fcOption == JFileChooser.ERROR_OPTION)
					println("Export gescheitert: Unbekannter Fehler");
				else if (fcOption == JFileChooser.CANCEL_OPTION)
					println("Export abgebrochen.");
				else if (fcOption == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					if (f.exists()) {
						if (JOptionPane.showConfirmDialog(PrimesGUI.this, "Die Datei existiert bereits. Möchten Sie sie überschreiben?", "Datei existiert bereits", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE) == JOptionPane.NO_OPTION) {
							println("Export abgebrochen.");
							return;
						}
					}

					println("Export gestartet");

					try {
						FileWriter fw = new FileWriter(f, false);

						for (int p = 0; p < primes.length; p++) {
							if (primes[p]) {
								fw.write(String.valueOf(p));
								fw.write("\r\n");
							}
						}

						fw.close();

						println("Export erfolgreich.");
					} catch (IOException e) {
						println("Export fehlgeschalgen: Datei-Fehler.");
						PrimesApplication.error(e, false);
					}
				} else
					println("Export gescheitert: Unbekannte Option");
			}
		});
	}

	/**
	 * Disables the action components, starts a new Thread and executes the given {@link Runnable}.
	 * <p>
	 * After the Runnable finished it re-enables the action components.
	 * <p>
	 * An {@link UncaughtExceptionHandler} will print <tt>name + " fehlgeschlagen."</tt> and delegate the exception to {@link PrimesApplication#error(Throwable, Thread, boolean)}.
	 */
	protected void runAction(final String name, final Runnable r) {
		setActionComponentsEnabled(false);

		Thread t = new Thread(new Runnable() {
			public void run() {
				r.run();

				setActionComponentsEnabled(true);
			}
		}, "PrimesGUI Action: " + name);
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				println(name + " fehlgeschalgen.");
				setActionComponentsEnabled(true);
				PrimesApplication.error(e, t, false);
			}
		});
		t.start();
	}
}
