package project.model;

public class SieveOfErathosthenes
{

    public boolean[] calcPrimes(int maxPrime)
    {
        boolean primes[]=new boolean[maxPrime+1];
        for (int i=2; i<primes.length; i++)
            primes[i]=true;

        for (int i=2; i< maxPrime; i++)
        {
            for (int j=i; j< maxPrime; j++)
            {

                if(j*i < primes.length) // not optimized (, yet) !
                    primes[Math.abs(j*i)]=false;  // Math.abs() because of NegativeArraySizeException !
                else
                {
                    if(j*i < primes.length) 
                        System.out.println("\t j * i "+j*i); /* DEBUG Code -->  I´ll remove it when the func is working */
                }

            }
        }

        for (int i=0; i<=maxPrime; i++) // OUTPUT
        {

            if (primes[Math.abs(i)])
                System.out.print(" "+i+" ");

        }

        return primes;

    }

}
