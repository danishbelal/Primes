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
        boolean [] xxx = x.calcPrimes((int) 1e9);

    }

}
