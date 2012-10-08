package project.primeCalc;

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

	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE;
	}

	public boolean[] determinePrimes(int max) {
		return null; // TODO: Implement
	}
}
