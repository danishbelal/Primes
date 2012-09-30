package project.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import project.utils.Constants;

public class GUI extends JFrame
{
    private static final long serialVersionUID = 1L;

    

    public void initGUI()
    {
        setSize( Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT );
        setTitle( Constants.WINDOW_TITLE );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
//        Creating subcontainers ...
        
        Container primeContainer  = new Container();
        PrimeContainer.createContainer(primeContainer);
        
        
//        Adding them to the JTabbedPane
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab( Constants.PRIME_TAB_NAME, primeContainer );
        
        this.setLayout( new BorderLayout() );
        this.add(tabPane, BorderLayout.CENTER);
        
        
        
        setVisible( true );
    }

    public void addPrime(int prime)
    {
        // TODO Implementieren !!!
    }
}
