package project.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI extends JFrame implements UI {
	private static final long serialVersionUID = 1L;

	JTextField output = new JTextField("TEST");


	public void initGUI() {
		setSize(800, 500);
		setTitle("Prime Calculator von Jan Erik und Danish");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		setVisible(true);
	}

	@Override
	public void addPrime(int prime) {
		// TODO Implementieren !!!
	}
}
