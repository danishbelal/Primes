package project.view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import project.model.SieveOfErathosthenes;
import static project.model.SieveOfErathosthenes.*;


public class GUI extends JFrame
{

    private JPanel contentPane;
    private JTextField inputText;

    /**
     * Launch the application.
     */
    public static void main(String [] args)
    {

        EventQueue.invokeLater( new Runnable()
        {

            public void run()
            {

                try
                {
                    GUI frame = new GUI();
                    frame.setVisible( true );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the frame.
     */
    public GUI() {
        setTitle(project.utils.Constants.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
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
        
        final JTextPane outputTxt = new JTextPane(); // Eclipse wanted this to be final ???
        outputTxt.setText("Ausgabe ");
        primeCalc.add(outputTxt, BorderLayout.CENTER);
        
        JPanel primeInput = new JPanel();
        primeCalc.add(primeInput, BorderLayout.WEST);
        GridBagLayout gbl_primeInput = new GridBagLayout();
        gbl_primeInput.columnWidths = new int[]{86, 26, 0, 0};
        gbl_primeInput.rowHeights = new int[]{269, 0, 0};
        gbl_primeInput.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_primeInput.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        primeInput.setLayout(gbl_primeInput);
        
        inputText = new JTextField();
        GridBagConstraints gbc_inputText = new GridBagConstraints();
        gbc_inputText.insets = new Insets(0, 0, 5, 0);
        gbc_inputText.gridwidth = 2;
        gbc_inputText.anchor = GridBagConstraints.NORTH;
        gbc_inputText.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputText.gridx = 1;
        gbc_inputText.gridy = 0;
        primeInput.add(inputText, gbc_inputText);
        inputText.setColumns(10);
        
        JLabel inputLabel = new JLabel("max");
        inputLabel.setToolTipText("Primzahlen berechnen bis ... ");
        GridBagConstraints gbc_inputLabel = new GridBagConstraints();
        gbc_inputLabel.insets = new Insets(0, 0, 5, 5);
        gbc_inputLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_inputLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputLabel.gridx = 0;
        gbc_inputLabel.gridy = 0;
        primeInput.add(inputLabel, gbc_inputLabel);
        
        JButton btnNewButton = new JButton("Start !");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) 
            {
                boolean[] primes  = null;
                try 
                {
                   
                    primes= calcPrimes(Integer.parseInt( inputText.getText() ));
                }
                catch (NumberFormatException e)
                {
                    StringBuilder buf = new StringBuilder();
                    for(StackTraceElement elem : e.getStackTrace())
                        buf.append( elem.toString() + "\n" );
                    outputTxt.setText( buf.toString() );
                        
                }
                
                outputTxt.setText( Bool2Int( primes )); 
            }
        });
        btnNewButton.setToolTipText("Startet den Vorgang !");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(10, 10, 10, 10);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 1;
        primeInput.add(btnNewButton, gbc_btnNewButton);
    }
}
