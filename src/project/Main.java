package project;

// import project.model.Prime;
import project.model.Prime;
import project.model.Prime_Math;

public class Main
{

    public static void main(String [] args) //throws InterruptedException // Hä ?
    {

        // GUI ui = new GUI();
        // ui.initGUI();

        long before = System.currentTimeMillis();
//        int maxPrime = (int) Math.sqrt( Integer.MAX_VALUE ) - 1;
        

//        Prime p = new Prime();
//        p.calcPrimes( maxPrime );

        System.out.println(Prime_Math.ggT( Prime_Math.EUKLID, 17, 34 ));
        
        
        System.out.println( "Took " + ( System.currentTimeMillis() - before )
                + "ms" );
        System.out.println( "Used Memory: "
                + ( ( Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                        .freeMemory() ) / 1024 / 1024 ) + " MB" );

    }

}
