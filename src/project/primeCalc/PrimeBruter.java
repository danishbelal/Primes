package project.primeCalc;

import java.util.Set;

import project.UI;

public class PrimeBruter implements PrimeCalculator {
	private final UI ui;

	private int lastPrime = 2;
	private int currentNumber = lastPrime;
	private int divisor;
	private boolean isPrime = true;
	private boolean thirdPrime = false;

	public PrimeBruter(UI ui) {
		this.ui = ui;
	}

	public String getName() {
		return "Einfaches durchtesten";
	}

	public int[] calcPrimes(int start, int maxPrime) {
		if (start > maxPrime)
			throw new IllegalArgumentException("start is greater than maxPrime");
		int[] primes = new int[maxPrime];

		for (currentNumber = start; currentNumber <= maxPrime; currentNumber++) {
			isPrime = true;
			for (divisor = 2; isPrime && (divisor < currentNumber); divisor++) {
				if (currentNumber % divisor == 0) {
					isPrime = false;
				}
			}
		}
		return primes;
	}

	public int[] calcPrimes(int maxPrime) {
		return calcPrimes(2, maxPrime);
	}

	public int getNextPrime() {
		if (lastPrime == 2) {
			lastPrime = 3;
			thirdPrime = true;

			return 2;
		} else if (thirdPrime) {
			thirdPrime = false;
			return 3;
		}

		for (currentNumber = lastPrime;; currentNumber++) {
			isPrime = true;
			for (divisor = 2; isPrime & (divisor < currentNumber); divisor++) {
				if (currentNumber % divisor == 0)
					isPrime = false;
			}
			if (isPrime) {
				if (currentNumber > lastPrime) {
					lastPrime = currentNumber;
					return currentNumber;
				}
			}
		}
	}

	public boolean[] calculatePrimes(int start, int max) {
		return null; // TODO: Implements these
	}

	public Set<Integer> calculatePrimes(int amount) {
		return null;
	}
}
