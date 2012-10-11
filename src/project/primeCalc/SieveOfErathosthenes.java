package project.primeCalc;

import project.UI;

public class SieveOfErathosthenes implements PrimeCalculator {
	private final UI ui;


	{
		System.out.println("[DEBUG]\t :\t in SieveOfErathosthenes(UI ui) " );
	}
	public SieveOfErathosthenes(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Sieb des Erathosthenes";
	}

	public int getHighestDeterminableNumber() {
		return (int) Math.sqrt(Integer.MAX_VALUE) - 1;
	}

	public boolean[] determinePrimes(int max) {
		boolean[] primes = new boolean[max + 1];

		for (int i = 2; i < primes.length; i++)
			primes[i] = true;

		for (int i = 2; i < max; i++) {
			for (int j = i; j * i < max; j++) {
				primes[j * i] = false;
			}
		}
		// bug: "Primzahl: 10000"
		// Its fixed

		for (int p = 0; p < primes.length; p++)
			if (primes[p])
				ui.determinedPrime(p);

		return primes;
	}
}
