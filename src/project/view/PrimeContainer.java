package project.view;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextField;


class PrimeContainer
{

    public static boolean createContainer(Container c)
    {

        c.setLayout( new BorderLayout() );

        // defining SubContainers
        Container primeCalcControls = new Container();
        Container primeOutput = new Container();
        Container primeInput = new Container();
        // Filling SubContainers
        primeCalcControls.setLayout( new BorderLayout() );
        primeCalcControls.add( new JLabel( " Verfahren " ), BorderLayout.WEST );
        // SEcond Subcontainer
        primeOutput.setLayout( new BorderLayout() );
        primeOutput.add( new JTextField( " AUSGABE FENSTER " ) );

        c.add( primeCalcControls, BorderLayout.NORTH );
        c.add( primeOutput, BorderLayout.SOUTH );

        return false;

    }
}
