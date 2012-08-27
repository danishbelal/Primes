package project.model;

/**
 * @author Danish Belal
 */
public class Prime implements Model
{
    int lastPrime, currentNumber, maxPrime, divisor;
    boolean isPrime;

    public Prime()
    {
        lastPrime=2;
        currentNumber=lastPrime;
        isPrime=true;
    }

    public int[] calcPrimes(int start, int maxPrime)
    {

        int[] primes=new int[maxPrime];
        for (currentNumber=start; currentNumber<=maxPrime; currentNumber++)
        {
            isPrime=true;
            for (divisor=2; isPrime&&(divisor<currentNumber); divisor++)
            {
                if (currentNumber%divisor==0)
                {
                    isPrime=false;
                }
            } // for
            if (isPrime)
            {

                primes[currentNumber]=currentNumber;
            }
        }
        return primes;

    }

    public int[] calcPrimes(int maxPrime)
    {
        return calcPrimes(2, maxPrime);
    }

    public int getNextPrime()
    {

        if (lastPrime==2)
        {
            lastPrime=3;
            return 2;
        }
        for (currentNumber=2;; currentNumber++)
        {
            isPrime=true;
            for (divisor=2; isPrime&(divisor<currentNumber); divisor++)
            {
                if (currentNumber%divisor==0)
                    isPrime=false;
            }
            if (isPrime)
            {
                if (currentNumber>lastPrime)
                {
                    lastPrime=currentNumber;
                    return currentNumber;
                }
            }
        }

    }

    
}
