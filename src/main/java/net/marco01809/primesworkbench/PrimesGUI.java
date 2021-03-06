package net.marco01809.primesworkbench;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.marco01809.primesworkbench.calculators.PrimeCalculator;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

/**
 * Primes GUI, a single class containing the entire GUI.
 */
public class PrimesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	protected static final String GUI_WINDOW_TITLE = "PrimesWorkbench " + PrimesApplication.VERSION;

	protected static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#,###,###,##0");
	protected static final DateFormat SDF = new SimpleDateFormat("[HH:mm:ss] ");

	private PrimesMenuBar menubar;
	private JTabbedPane tabber;

	private JTextArea logTextPane;
	private JTextArea faktorisierungTextPane;
	private JTextField faktorisierungsZahl;
	private JTextArea primzahlenTextPane;
	private JTextArea istPrimTextArea;
	private JButton btnIstPrim;

	@SuppressWarnings("rawtypes")
	private JComboBox cbxMethode;
	private JCheckBox chckbxAusgabe;
	private JTextField textFieldBerechnetBis;
	private JSpinner spinner;
	private JButton btnCalcStart;
	private JTextField textField;

	/**
	 * Stores the result of a prime calculation, for further use by a PrimeUsage.
	 */
	private boolean[] primes;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public PrimesGUI() {
		setTitle(GUI_WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(PrimesGUI.class.getResourceAsStream("/img/logo_96x96.png")));
		} catch (IOException e) {
			PrimesApplication.error(e, false);
		} catch (IllegalArgumentException e) {
			PrimesApplication.error(e, false);
		}
		setMinimumSize(new Dimension(520, 340));
		setLocationRelativeTo(null);

		menubar = new PrimesMenuBar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPrimes();
			}
		});
		setJMenuBar(menubar);

		tabber = new JTabbedPane();
		setContentPane(tabber);

		/*
		 * Panel "Berechnung"
		 */
		JPanel berechnungPanel = new JPanel();
		berechnungPanel.setLayout(new BorderLayout());

		JPanel calcPrimesPanel = new JPanel();
		calcPrimesPanel.setSize(250, 160);
		calcPrimesPanel.setMinimumSize(new Dimension(250, 160));
		calcPrimesPanel.setPreferredSize(new Dimension(250, 160));
		calcPrimesPanel.setLayout(null);

		JLabel lblMethode = new JLabel("Methode:");
		lblMethode.setBounds(10, 11, 46, 14);
		calcPrimesPanel.add(lblMethode);

		JLabel lblBerechnePrimzahlenBis = new JLabel("Berechne Zahlen bis");
		lblBerechnePrimzahlenBis.setBounds(10, 36, 97, 14);
		calcPrimesPanel.add(lblBerechnePrimzahlenBis);

		spinner = new JSpinner();
		spinner.setToolTipText("Gibt an, bis zu welcher Zahl berechnet werden soll. Umso höher, umso zeitaufwändiger die Berechnung.");
		spinner.setModel(new SpinnerNumberModel(new Integer(10000), new Integer(1), null, new Integer(100)));
		spinner.setBounds(121, 33, 119, 20);
		calcPrimesPanel.add(spinner);

		cbxMethode = new JComboBox();
		cbxMethode.setToolTipText("Wählt die Methode aus, mit der Primzahlen berechnet werden sollen.");
		cbxMethode.setBounds(66, 8, 174, 20);
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
		btnCalcStart.setBounds(107, 61, 133, 23);
		calcPrimesPanel.add(btnCalcStart);

		JLabel lblVerfgbarePrimzahlen = new JLabel("Zahlen berechnet bis:");
		lblVerfgbarePrimzahlen.setBounds(10, 98, 109, 14);
		calcPrimesPanel.add(lblVerfgbarePrimzahlen);

		textFieldBerechnetBis = new JTextField();
		textFieldBerechnetBis.setToolTipText("Zeigt die Anzahl der berechneten Primzahlen.");
		textFieldBerechnetBis.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldBerechnetBis.setText("0");
		textFieldBerechnetBis.setEditable(false);
		textFieldBerechnetBis.setBounds(121, 95, 119, 20);
		calcPrimesPanel.add(textFieldBerechnetBis);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 123, 230, 2);
		calcPrimesPanel.add(separator);

		chckbxAusgabe = new JCheckBox("Ausgabe");
		chckbxAusgabe.setBounds(10, 61, 97, 23);
		chckbxAusgabe.setSelected(true);
		chckbxAusgabe.setToolTipText("Bei sehr großen Berechnungen sollte die Primzahl-Ausgabe ausgeschaltet werden, da sie das Programm ansonsten abstürzen lässt.");
		calcPrimesPanel.add(chckbxAusgabe);

		JLabel lblIst = new JLabel("Ist");
		lblIst.setBounds(10, 137, 13, 14);
		calcPrimesPanel.add(lblIst);
		
		ActionListener istPrimActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (primes == null) return;
				
				int zahl = 0;
				try {
					zahl = Integer.parseInt(textField.getText().replace(".", ""));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(PrimesGUI.this,
							"Eingabe ist keine gültige Zahl.\nDie Zahl muss aus Ziffern bestehen, positiv sein und zwischen 2 und " + NUMBER_FORMAT.format(Integer.MAX_VALUE) + " liegen.",
							"Eingabefehler", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (primes.length <= zahl) {
					istPrimTextArea.setText(zahl + "\tnoch nicht berechnet" + "\n" + istPrimTextArea.getText());
				} else {
					if (primes[zahl]) {
						istPrimTextArea.setText(zahl + "\tist eine Primzahl!" + "\n" + istPrimTextArea.getText());
					} else {
						istPrimTextArea.setText(zahl + "\tist keine Primzahl." + "\n" + istPrimTextArea.getText());
					}
				}
			}
		};

		textField = new JFormattedTextField(NumberFormat.getInstance());
		textField.setBounds(33, 134, 97, 20);
		textField.addActionListener(istPrimActionListener);
		calcPrimesPanel.add(textField);
		textField.setColumns(10);
		
		btnIstPrim = new JButton("eine Primzahl?");
		btnIstPrim.setEnabled(false);
		btnIstPrim.addActionListener(istPrimActionListener);
		btnIstPrim.setBounds(131, 133, 109, 23);
		calcPrimesPanel.add(btnIstPrim);

		istPrimTextArea = new JTextArea();
		istPrimTextArea.setToolTipText("Zeigt die zuletzt geprüften Zahlen an.");
		istPrimTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		istPrimTextArea.setEditable(false);
		istPrimTextArea.setBounds(10, 162, 230, 127);

		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setLayout(new BorderLayout());

		leftSidePanel.add(calcPrimesPanel, BorderLayout.PAGE_START);
		leftSidePanel.add(new JScrollPane(istPrimTextArea), BorderLayout.CENTER);
		berechnungPanel.add(leftSidePanel, BorderLayout.LINE_START);

		primzahlenTextPane = new JTextArea();
		primzahlenTextPane.setToolTipText("Zeigt die berechneten Primzahlen an.");
		primzahlenTextPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		primzahlenTextPane.setEditable(false);
		primzahlenTextPane.setLineWrap(true);
		berechnungPanel.add(new JScrollPane(primzahlenTextPane), BorderLayout.CENTER);

		tabber.add(berechnungPanel, 0);
		tabber.setTitleAt(0, "Berechnung");

		/*
		 * Panel "Faktorisierung"
		 */
		JPanel faktorisierungPanel = new JPanel();
		faktorisierungPanel.setLayout(new BorderLayout());

		JPanel faktorisierungHeadPanel = new JPanel();
		faktorisierungHeadPanel.setLayout(new BorderLayout());

		faktorisierungHeadPanel.add(new JLabel("Zu faktorisierende Zahl:"), BorderLayout.LINE_START);

		ActionListener faktorisiereListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int zahl = 0;
				try {
					zahl = Integer.parseInt(faktorisierungsZahl.getText().replace(".", ""));
					if (zahl <= 1)
						throw new NumberFormatException();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(PrimesGUI.this,
							"Eingabe ist keine gültige Zahl.\nDie Zahl muss aus Ziffern bestehen, positiv sein und zwischen 2 und " + NUMBER_FORMAT.format(Integer.MAX_VALUE) + " liegen.",
							"Eingabefehler", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int arbeitsZahl = zahl;
				StringBuilder sb = new StringBuilder(40);
				faktorisieren: for (int teiler = 2; teiler < primes.length; teiler++) {
					if (primes[teiler]) {
						while (arbeitsZahl % teiler == 0) {
							sb.append(teiler);

							arbeitsZahl = arbeitsZahl / teiler;

							if (arbeitsZahl == 1) {
								break faktorisieren;
							} else {
								sb.append(" * ");
							}
						}
					}
				}

				if (arbeitsZahl != 1) {
					faktorisierungTextPane.setText(zahl + "\t= Benötige mehr Primzahlen zum Faktorisieren dieser Zahl.\n" + faktorisierungTextPane.getText());
				} else {
					faktorisierungTextPane.setText(zahl + "\t= " + sb.toString() + "\n" + faktorisierungTextPane.getText());
				}
			}
		};

		faktorisierungsZahl = new JFormattedTextField(NumberFormat.getInstance());
		faktorisierungsZahl.addActionListener(faktorisiereListener);
		faktorisierungHeadPanel.add(faktorisierungsZahl, BorderLayout.CENTER);

		JButton faktorisierenBtn = new JButton("Faktorisieren");
		faktorisierenBtn.addActionListener(faktorisiereListener);
		faktorisierungHeadPanel.add(faktorisierenBtn, BorderLayout.LINE_END);

		faktorisierungPanel.add(faktorisierungHeadPanel, BorderLayout.PAGE_START);

		faktorisierungTextPane = new JTextArea();
		faktorisierungTextPane.setToolTipText("Zeigt die zuletzt faktorisierten Zahlen an.");
		faktorisierungTextPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		faktorisierungTextPane.setEditable(false);
		faktorisierungPanel.add(new JScrollPane(faktorisierungTextPane), BorderLayout.CENTER);
		JButton faktorisierungBtnClear = new JButton("Faktorisierungsliste leeren");
		faktorisierungBtnClear.setToolTipText("Leert den Inhalt der Faktorisierungsliste.");
		faktorisierungBtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				faktorisierungTextPane.setText("");
			}
		});
		faktorisierungPanel.add(faktorisierungBtnClear, BorderLayout.PAGE_END);

		tabber.add(faktorisierungPanel, 1);
		tabber.setTitleAt(1, "Faktorisierung");
		tabber.setEnabledAt(1, false);

		/*
		 * Panel "Ereignisanzeige"
		 */
		JPanel ereignisPanel = new JPanel();
		ereignisPanel.setLayout(new BorderLayout());
		logTextPane = new JTextArea();
		logTextPane.setToolTipText("Zeigt Ereignisse, Fehler und den Fortschritt bestimmter Operationen an.");
		logTextPane.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		logTextPane.setEditable(false);
		ereignisPanel.add(new JScrollPane(logTextPane), BorderLayout.CENTER);
		JButton logBtnClear = new JButton("Ereignisanzeige leeren");
		logBtnClear.setToolTipText("Leert den Inhalt der Ereignisanzeige.");
		logBtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLog();
			}
		});
		ereignisPanel.add(logBtnClear, BorderLayout.PAGE_END);
		tabber.add(ereignisPanel, 2);
		tabber.setTitleAt(2, "Ereignisanzeige");
		try {
			tabber.setIconAt(2, new ImageIcon(ImageIO.read(PrimesGUI.class.getResourceAsStream("/img/ereignislog.png")).getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			PrimesApplication.error(e, false);
		} catch (IllegalArgumentException e) {
			PrimesApplication.error(e, false);
		}
	}

	/* *************************************************************************
	 * UI
	 */

	public void clearLog() {
		if (SwingUtilities.isEventDispatchThread()) {
			logTextPane.setText("");
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					logTextPane.setText("");
				}
			});
		}
	}

	public void logPrintln(final String text) {
		final String formattedText = SDF.format(new Date()) + text + '\n';

		System.out.print(formattedText);

		if (SwingUtilities.isEventDispatchThread()) {
			logTextPane.setText(logTextPane.getText() + formattedText);
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					logTextPane.setText(logTextPane.getText() + formattedText);
				}
			});
		}
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
		menubar.setExportButtonEnabled(enabled && primes != null);
		tabber.setEnabledAt(1, enabled && primes != null);
		btnIstPrim.setEnabled(enabled && primes != null);
	}

	@SuppressWarnings("unchecked")
	public void addPrimeCalculator(PrimeCalculator primeCalc) {
		if (this.isVisible()) // Adding these while the user is able to access the GUI could cause threading problems.
			throw new IllegalStateException("Cannot add a PrimeCalculator if the GUI is visible");
		cbxMethode.addItem(primeCalc);
	}

	/* *************************************************************************
	 * Actions
	 */

	protected void startPrimeCalculation() {
		// Not inside the Runnable, because the EDT should grab this values.
		final PrimeCalculator primeCalc = (PrimeCalculator) cbxMethode.getSelectedItem();
		final int determineMax = (Integer) spinner.getValue();

		// Format the number for better legibility
		final String numberAmountString = NUMBER_FORMAT.format(determineMax);
		logPrintln("Berechnung mit '" + primeCalc.getName() + "' für " + numberAmountString + " Zahlen gestartet.");
		primzahlenTextPane.setText("Benutze '" + primeCalc.getName() + "' für " + numberAmountString + " Zahlen.");

		runAction("Berechnung", new Runnable() {
			public void run() {
				// Get the time before the calculation
				long timeBefore = System.currentTimeMillis();

				// Determine the Primes
				boolean[] lastPrimes = primeCalc.determinePrimes(determineMax);

				// Format the used time for better legibility
				final String tookTimeString = NUMBER_FORMAT.format(System.currentTimeMillis() - timeBefore);

				// Only set the primes if the determined primes got improved
				if (primes == null || lastPrimes.length > primes.length) {
					primes = lastPrimes;

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							textFieldBerechnetBis.setText(numberAmountString);
						}
					});
				}

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						logPrintln("Berechnung dauerte " + tookTimeString + " ms.");
						primzahlenTextPane.setText(primzahlenTextPane.getText() + "\nBerechnung dauerte " + tookTimeString + " ms...");
					}
				});

				if (chckbxAusgabe.isSelected()) {
					final StringBuilder sb = new StringBuilder(2000);
					for (int i = 0; i < lastPrimes.length; i++) {
						if (lastPrimes[i]) {
							sb.append(Integer.toString(i));
							sb.append(",\t");
						}
					}
					sb.setLength(sb.length() - 2);

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							primzahlenTextPane.setText(primzahlenTextPane.getText() + "\n" + sb.toString());
						}
					});
				}
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
					logPrintln("Export gescheitert: Unbekannter Fehler");
				else if (fcOption == JFileChooser.CANCEL_OPTION)
					logPrintln("Export abgebrochen.");
				else if (fcOption == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					if (f.exists()) {
						if (JOptionPane.showConfirmDialog(PrimesGUI.this, "Die Datei existiert bereits. Möchten Sie sie überschreiben?", "Datei existiert bereits", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE) == JOptionPane.NO_OPTION) {
							logPrintln("Export abgebrochen.");
							return;
						}
					}

					tabber.setSelectedIndex(2);

					logPrintln("Export gestartet.");

					try {
						FileWriter fw = new FileWriter(f, false);

						for (int p = 0; p < primes.length; p++) {
							if (primes[p]) {
								fw.write(String.valueOf(p));
								fw.write("\r\n");
							}
						}

						fw.close();

						logPrintln("Export erfolgreich.");
					} catch (IOException e) {
						logPrintln("Export gescheitert: Datei-Fehler.");
						PrimesApplication.error(e, false);
					}
				} else
					logPrintln("Export gescheitert: Unbekannte Option");
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
				logPrintln(name + " fehlgeschlagen.");
				setActionComponentsEnabled(true);
				PrimesApplication.error(e, t, false);
			}
		});
		t.start();
	}
}
