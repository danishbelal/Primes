package project.primeCalc;

import project.UI;

public class SieveOfErathosthenes implements PrimeCalculator {
	private final UI ui;

	public SieveOfErathosthenes(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Sieb des Erathosthenes";
	}

	public int getHighestDeterminableNumber() {
		return (int) Math.sqrt(Integer.MAX_VALUE) - 1;
	}

	public boolean[] calcPrimes(int maxPrime) {
		boolean primes[] = new boolean[maxPrime + 1];
		for (int i = 2; i < primes.length; i++)
			primes[i] = true;

		for (int i = 2; i < maxPrime; i++) {
			for (int j = i; j * i < maxPrime; j++) {
				primes[j * i] = false;
			}
		}
		return primes;
	}

	public boolean[] determinePrimes(int max) {
		ui.determinedPrime(3); // Just for testing.
		ui.determinedPrime(5);
		
		return new boolean[] {false, false, false, true, false, true};  // TODO: Implement
	}
}
