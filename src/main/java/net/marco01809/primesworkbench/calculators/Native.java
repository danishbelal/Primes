package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.PrimesGUI;

public class Native extends PrimeCalculator {
	public Native(PrimesGUI ui) {
		super(ui, "Primes_native");
	}

	@Override
	public native boolean[] determinePrimes(int max);

	@Override
	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE;
	}
}
