package project.primeCalc;

import project.UI;

public class PrimeBruter implements PrimeCalculator {
	private final UI ui;

	public PrimeBruter(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Einfaches Durchtesten";
	}

	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}

	public boolean[] determinePrimes(int max) {
//		boolean[] primes = new boolean[max + 1];
		boolean isPrime = true;
		int currentNumber;

		for (currentNumber = 2; currentNumber <= max; currentNumber++) {
			isPrime = true;
			for (int divisor = 2; isPrime && (divisor < currentNumber); divisor++) {
				if (currentNumber % divisor == 0) {
					isPrime = false;
				}
			} // for
			if (isPrime) {
				ui.determinedPrime(currentNumber);
			}
		}
//		return primes;
		return null;
	}
}
