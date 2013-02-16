package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public abstract class PrimeCalculator {
	protected final UI ui;
	private final String name;

	protected PrimeCalculator(UI ui, String name) {
		this.ui = ui;
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final UI getUI() {
		return ui;
	}

	@Override
	public final String toString() {
		return getName();
	}

	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}

	public abstract boolean[] determinePrimes(int max);

	// Has to be overwritten by SieveOfEratosthenesBCD
	public boolean[] determinePrimes(long max) {
		return determinePrimes((int) max);
	}
}
