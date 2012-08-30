package project;

//import project.model.Prime;
import project.model.SieveOfErathosthenes;

/**
 * @author Danish Belal
 */

public class Main
{

    static SieveOfErathosthenes x=new SieveOfErathosthenes();

    public static void main(String[] args)
    {
        int maxPrime= 2000;
        //Prime test=new Prime();

        boolean[] xxx=x.calcPrimes(maxPrime);
        
    }

}
