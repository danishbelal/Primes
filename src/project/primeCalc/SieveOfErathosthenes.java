package project.primeCalc;


public class SieveOfErathosthenes implements PrimeCalculator
{

    public static boolean [] calcPrimes(int maxPrime)
    {

        boolean primes[] = new boolean[ maxPrime + 1 ];
        for ( int i = 2; i < primes.length; i++ )
            primes[ i ] = true;

        for ( int i = 2; i < maxPrime; i++ )
        {
            for ( int j = i; j * i < maxPrime; j++ )
            {
                primes[ j * i ] = false;
            }
        }
        return primes;
    }
}
