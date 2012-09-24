package project;

// import project.model.Prime;
import project.model.Prime;

public class Main
{

    public static void main(String [] args) //throws InterruptedException // H� ?
    {

        // GUI ui = new GUI();
        // ui.initGUI();

        long before = System.currentTimeMillis();
        int maxPrime = (int) Math.sqrt( Integer.MAX_VALUE ) - 1;
        // Prime test=new Prime();

        Prime p = new Prime();
        p.calcPrimes( maxPrime );

        System.out.println( "Took " + ( System.currentTimeMillis() - before )
                + "ms" );
        System.out.println( "Used Memory: "
                + ( ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                        .freeMemory() ) / 1024 / 1024 ) + " MB" );

    }

}
