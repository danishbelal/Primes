package project;

// import project.model.Prime;

import project.view.GUI;

public class Main
{

    public static void main(String [] args) // throws InterruptedException // Hä
                                            // ?
    {

        long before = System.currentTimeMillis();

        GUI ui = new GUI();
        ui.initGUI();

        // System.out.println( PrimeMath.ggT( PrimeMath.EUKLID, 17, 34 ) );

        System.out.println( "Took " + ( System.currentTimeMillis() - before )
                + "ms" );
        System.out.println( "Used Memory: "
                + ( ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                        .freeMemory() ) / 1024 / 1024 ) + " MB" );

    }

}
