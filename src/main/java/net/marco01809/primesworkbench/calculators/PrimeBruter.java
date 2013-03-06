package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.PrimesGUI;

public final class PrimeBruter extends PrimeCalculator {
	public PrimeBruter(PrimesGUI ui) {
		super(ui, "Einfaches Durchtesten");
	}

	public boolean[] determinePrimes(int max) {
		boolean[] primes = new boolean[max + 1];

		boolean isPrime = true;
		int currentNumber;
		for (currentNumber = 2; currentNumber <= max; currentNumber++) {
			isPrime = true;
			for (int divisor = 2; isPrime && (divisor < currentNumber); divisor++) {
				if (currentNumber % divisor == 0) {
					isPrime = false;
				}
			}

			if (isPrime) {
				primes[currentNumber] = true;
			}
		}

		return primes;
	}

	@Override
	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE;
	}
}
