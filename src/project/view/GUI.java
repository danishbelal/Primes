package project.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle(project.utils.Constants.WINDOW_TITLE);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel primeTab = new JPanel();
		tabbedPane.addTab("Primzahlen", null, primeTab, "Primzahlen und alles was dazu geh\u00F6rt ...");
		primeTab.setLayout(new BorderLayout(0, 0));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setToolTipText("es tut sich was !");
		progressBar.setValue(50);
		primeTab.add(progressBar, BorderLayout.SOUTH);

		JPanel primeCalc = new JPanel();
		primeTab.add(primeCalc, BorderLayout.CENTER);
		primeCalc.setLayout(new BorderLayout(0, 0));

		JPanel primeCalcTitle = new JPanel();
		primeCalc.add(primeCalcTitle, BorderLayout.NORTH);
		primeCalcTitle.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel verfahrenLabel = new JLabel("Verfahren");
		primeCalcTitle.add(verfahrenLabel);
		
		@SuppressWarnings({ "unchecked" , "rawtypes"})
		JComboBox comboBox = new JComboBox (new String[] { "Sieb des Eratosthenes" , "Brute-Force" });
		primeCalcTitle.add(comboBox);
		
		JTextPane txtpnAusgabeWindow = new JTextPane();
		txtpnAusgabeWindow.setText("Ausgabe Window\r\n");
		primeCalc.add(txtpnAusgabeWindow, BorderLayout.CENTER);
		
		JPanel primeInput = new JPanel();
		primeCalc.add(primeInput, BorderLayout.WEST);
		
		JLabel inputLabel = new JLabel("Input");
		primeInput.add(inputLabel);
		
		textField = new JTextField();
		primeInput.add(textField);
		textField.setColumns(10);
	}

}
