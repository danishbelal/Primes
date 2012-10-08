package project.primeCalc;

import project.UI;

public class PrimeBruter implements PrimeCalculator {
	private final UI ui;

	public PrimeBruter(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Einfaches durchtesten";
	}

	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}

	public boolean[] determinePrimes(int max) {
		boolean[] primes = new boolean[max + 1];

		for (int p = 2; p < primes.length; p++) {
			boolean couldBeDivided = false;
			
			for (int i = p - 1; !couldBeDivided && i >= 2; i--) {
				if (p % i == 0) {
					couldBeDivided = true;
				}
			}

			if (!couldBeDivided)
				ui.determinedPrime(p);

			primes[p] = true;
		}

		return primes;
	}
}
