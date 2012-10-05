package project;


// import project.model.Prime;

import project.model.SieveOfErathosthenes;
import project.view.GUI;


public class Main
{

    public static void main(String [] args) // throws InterruptedException // Hä
                                            // ?
    {

        int maxPrime = 10; // (int) ( Math.sqrt( Integer.MAX_VALUE ) - 1 );
        long before = System.currentTimeMillis();

        GUI ui2 = new GUI();
        ui2.setVisible( true );

        

        System.out.println( "Took " + ( System.currentTimeMillis() - before )
                + "ms" );
        System.out.println( "Used Memory: "
                + ( ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                        .freeMemory() ) / 1024 / 1024 ) + " MB" );

    }

}
