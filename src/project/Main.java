package project;

// import project.model.Prime;

import project.view.GUI;
import project.view.GUIEXPERIMENTAL;

public class Main
{

    public static void main(String [] args) // throws InterruptedException // Hä
                                            // ?
    {

        long before = System.currentTimeMillis();

        GUIEXPERIMENTAL ui = new GUIEXPERIMENTAL();
        ui.setVisible(true);
        
        GUI ui2 = new GUI();
        ui2.setVisible( true );
        
//        YES currenttly there will appear 2 GUIs : Which is better?

        // System.out.println( PrimeMath.ggT( PrimeMath.EUKLID, 17, 34 ) );

        System.out.println( "Took " + ( System.currentTimeMillis() - before )
                + "ms" );
        System.out.println( "Used Memory: "
                + ( ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                        .freeMemory() ) / 1024 / 1024 ) + " MB" );

    }

}
