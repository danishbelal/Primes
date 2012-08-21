package project.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Danish Belal
 * @author Jan Erik Petersen
 */

public class GUI extends JFrame implements UI
{

    private static final long serialVersionUID=1L;
    private final String VERSION="Pre-Alpha 0.1";
    private final String AUTHOR=" von Jan Erik und Danish";

    MenuBar menu=new MenuBar();
    Menu file=new Menu("Datei");
    MenuItem exit=new MenuItem("Beenden");

    JTextField output=new JTextField("TEST");

    Container mainContent=getContentPane();
    JTabbedPane tabPane=new JTabbedPane();
    Container name=new Container();

    Container primeCalcContainer=new Container();
    Container primeCalcControls=new Container();
    Container primeCalcOutput=new Container();

    public void initGUI()
    {
        mainContent.add(tabPane);
        setSize(800, 500);
        setTitle("Prime Calculator "+VERSION+AUTHOR);
        createMenu();

        tabPane.addTab("Primzahlen", primeCalcContainer);

        primeCalcOutput.setLayout(new BorderLayout());
        primeCalcContainer.setLayout(new BorderLayout());
        primeCalcControls.setLayout(new GridLayout(1, 4));

        primeCalcOutput.add(output, BorderLayout.CENTER);
        primeCalcContainer.add(primeCalcControls, BorderLayout.NORTH);
        primeCalcContainer.add(primeCalcOutput, BorderLayout.EAST);

        addPrimeCalcControls();
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e)
        {
                     e.printStackTrace();
        }
        catch (InstantiationException e)
        {
                     e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e)
        {
                     e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(mainContent);

        setVisible(true);
    }

    @Deprecated
    private void createMenu()
    {
        setMenuBar(menu);
        menu.add(file);
        file.add(exit);
        exit.addActionListener(new listener());
    }

    private void addPrimeCalcControls()
    {
        String[] verfahren= { "Standard", "Sieb des Erathosthenes" };
        JComboBox option = new JComboBox(verfahren);
        option.addActionListener(new listener());
        primeCalcControls.add(new JLabel("     Verfahren"));
        primeCalcControls.add(option);
        primeCalcControls.add(new JTextField());
        primeCalcControls.add(new JCheckBox("In Datei speichern ??? "));
    }

    public void addPrime(int prime)
    {

        // TODO Implementieren !!!
    }

    class listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource().getClass().getName()=="java.awt.MenuItem")
            {
                System.out.println(e.getSource());
                System.exit(0);
            }
            System.out.println(e.getSource());
        }
    }

}
