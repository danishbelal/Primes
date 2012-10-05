package project.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public final JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Error {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			error(e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					error(e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("Primzahlen-Berechnung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel usePrimesPanel = new JPanel();
		usePrimesPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Primzahlen benutzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		usePrimesPanel.setBounds(10, 181, 207, 159);
		contentPane.add(usePrimesPanel);
		usePrimesPanel.setLayout(null);

		JPanel calcPrimesPanel = new JPanel();
		calcPrimesPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Primzahlen berechnen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		calcPrimesPanel.setBounds(10, 11, 207, 159);
		contentPane.add(calcPrimesPanel);
		calcPrimesPanel.setLayout(null);

		JLabel lblMethode = new JLabel("Methode:");
		lblMethode.setBounds(10, 22, 46, 14);
		calcPrimesPanel.add(lblMethode);
		
		JComboBox cbxMethode = new JComboBox();
		cbxMethode.addItem("Sieb des Erastosthenes");
		cbxMethode.addItem("Einfaches Durchtesten");
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
				textPane.setText("");
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

	public void appendConsoleText(String text) {
		textPane.setText(textPane.getText() + text + '\n');
	}

	public static void error(Throwable t) {
		t.printStackTrace();
		ByteArrayOutputStream byos = new ByteArrayOutputStream(1024 * 8);
		t.printStackTrace(new PrintStream(byos));
		JOptionPane.showMessageDialog(null, "Fehler des Typs " + t.getClass().getCanonicalName() + ":\n> " + t.getLocalizedMessage() + "\n\n" + byos.toString(), "Anwendungsfehler", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}
}
