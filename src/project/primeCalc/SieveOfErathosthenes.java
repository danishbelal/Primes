package project.primeCalc;

import java.util.Set;

import project.UI;

public class SieveOfErathosthenes implements PrimeCalculator {
	private final UI ui;

	public SieveOfErathosthenes(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Sieb des Erathosthenes";
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

	public boolean[] calculatePrimes(int start, int max) {
		return null;  // TODO: Implements these
	}

	public Set<Integer> calculatePrimes(int amount) {
		return null;
	}
}
