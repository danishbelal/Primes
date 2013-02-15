package net.marco01809.primeworkbench;

import net.marco01809.primeworkbench.calculators.PrimeCalculator;

public interface UI {
	/**
	 * Used to print text. It'll not add a newline.
	 */
	public void print(String text);

	/**
	 * Used to print a line of text.
	 */
	public void println(String text);

	/**
	 * Used to inform the UI about the successful determination of a prime.
	 */
	public void determinedPrime(int prime);

	/**
	 * Removes all text from the text output.
	 */
	public void clearText();

	/**
	 * Disables all buttons a.s.o. that could start a new action.
	 */
	public void setActionComponentsEnabled(boolean enabled);

	public void addPrimeCalculator(PrimeCalculator primeCalc);
}
