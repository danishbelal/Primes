package project.model;

public class SieveOfErathosthenes
{

    public boolean[] calcPrimes(int maxPrime)
    {
        boolean primes[]=new boolean[maxPrime+1];
        for (int i=2; i<primes.length; i++)
            primes[i]=true;

        for (int i=2; i<maxPrime; i++)
        {
            for (int j=i; j*i<maxPrime; j++)
            {

                primes[j*i]=false;

            }
        }

        for (int i=0; i<maxPrime; i++) // OUTPUT
        {

            if (primes[i])
                System.out.print(" "+i+" ");

        }

        return primes;

    }

}
