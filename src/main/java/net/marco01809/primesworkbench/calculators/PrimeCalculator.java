package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public abstract class PrimeCalculator {
	protected final UI ui;
	private final String name;

	protected PrimeCalculator(UI ui, String name) {
		this.ui = ui;
		this.name = name;
	}

	/**
	 * @return The Name of the choosen Calculation Method.
	 * @see PrimeCalculator#toString()
	 * */
	public final String getName() {
		return name;
	}

	/**
	 * @return The {@code UI } associated with the PrimeCalculator.
	 * */
	public final UI getUI() {
		return ui;
	}

	/**
	 * @return The name of the choosen Calulation Method.
	 * @see PrimeCalculator#getName()
	 * */
	@Override
	public final String toString() {
		return getName();
	}

	/**
	 * @return The highest determinable Number with the choosen Calculation Method.
	 * */
	public long getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}

	public abstract boolean[] determinePrimes(int max);

	/**
	 * This function is casting the Param {@code max} to {@code int} and calls 
	 * {@code determinePrimes(int)}.
	 * */
	public boolean[] determinePrimes(long max) {
		return determinePrimes((int) max);
	}
}
