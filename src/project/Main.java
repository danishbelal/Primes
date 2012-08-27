package project;

// import project.model.Prime;
import project.model.SieveOfErathosthenes;

/**
 * @author Danish Belal
 */

public class Main
{

    static SieveOfErathosthenes x=new SieveOfErathosthenes();

    public static void main(String[] args)
    {
        int maxP=10000000;
        System.out.println("Starte Berechnung...");
        System.out.println();
        SieveOfErathosthenes.eratosthenes(maxP < Integer.MAX_VALUE ? maxP : 100);
        // boolean[] xx=x.calcPrimes(maxP);
        System.out.println("Fertig ! ");
        System.out.println();

    }

}
