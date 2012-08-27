package project.model;

public class SieveOfErathosthenes
{

    public boolean[] calcPrimes(int maxPrime)
    {
        boolean primes[]=new boolean[maxPrime+1];
        for (int i=2; i<primes.length; i++)
            primes[i]=true;

        for (int i=2; i<=Math.sqrt(maxPrime); i++)
        {
            for (int j=i; j<=maxPrime; j++)
            {

                if(j*i < primes.length) // not optimized (, yet) !
                    primes[j*i]=false;

            }
        }

        for (int i=0; i<=maxPrime; i++) // OUTPUT
        {

            if (primes[i])
                System.out.print(" "+i+" ");

        }

        return primes;

    }

}
