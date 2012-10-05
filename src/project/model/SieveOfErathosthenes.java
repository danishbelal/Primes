package project.model;


public class SieveOfErathosthenes
{

    /**
     * This function is calculating all Primes up to maxPrime
     * 
     * @return A boolean Array where every Prime is "true"
     * @param maxPrime the maximum Prime to calculate 
     */

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

    public String toString()
    {
        
        
        return null;
    }
    
    public static String Bool2Int(boolean[] array , String endline)
    {
        if (array == null )
            return "null";
        StringBuilder buffer = new StringBuilder();
        
        for (int i = 1; i< array.length; i++)
            if (array[i])
                buffer.append( i + endline );
        
        return buffer.toString();
        
    }
    
    public static String Bool2Int(boolean[] array )
    {
        return Bool2Int(array, "\n");
    }
}
