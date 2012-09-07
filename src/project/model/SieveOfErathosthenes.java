package project.model;

public class SieveOfErathosthenes {

	/**
	 * This function is calculating all Primes up to maxPrime
	 * 
	 * @return A boolean Array where every Prime is "true"
	 * @param maxPrime
	 *            the maximum Prime to calculate Bugs:If maxPrime is greater than 2000 there will be no output !
	 * */

	public boolean[] calcPrimes(int maxPrime) {
		boolean primes[] = new boolean[maxPrime + 1];
		for (int i = 2; i < primes.length; i++)
			primes[i] = true;

		for (int i = 2; i < maxPrime; i++) {
			for (int j = i; j * i < maxPrime; j++) {

				primes[j * i] = false;

			}
		}

		for (int i = 0; i < maxPrime; i++) // OUTPUT
		{

			if (primes[i])
				System.out.println(i);

		}

		return primes;

	}

}
