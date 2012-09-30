package project.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import project.utils.Constants;

public class GUI extends JFrame
{
    private static final long serialVersionUID = 1L;

    JTextField output = new JTextField( "TEST" );

    public void initGUI()
    {
        setSize( Constants.WINDOW_WIDTH, Constants.WINDOW_WIDTH );
        setTitle( Constants.WINDOW_TITLE );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        setVisible( true );
    }

    public void addPrime(int prime)
    {
        // TODO Implementieren !!!
    }
}
