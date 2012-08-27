package project.model;

public class SieveOfErathosthenes
{

    public boolean[] calcPrimes(int maxPrime)
    {
        boolean primes[]=new boolean[maxPrime+1];
        for (int i=2; i<primes.length; i++)
            primes[i]=true;
        // 214748364
        // 2147479015
        // 214748364

        for (int i=2; i<=maxPrime; i++)
        {
            for (int j=i*i; j<=maxPrime; j+=i)
            {
                if(j>9000000)
                    primes[Math.abs(j)]=false;
                primes[Math.abs(j)]=false;

            }
        }

        for (int i=0; i<=maxPrime; i++)
        {
            
            if (primes[i])
                System.out.print(" "+i+" ");

        }

        return primes;

    }

    

    
}
