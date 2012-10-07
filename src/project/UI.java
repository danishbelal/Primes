package project;

import project.primeCalc.PrimeCalculator;
import project.primeUsage.PrimeUsage;

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

	public void addPrimeCalculator(PrimeCalculator primeCalc);
	public void addPrimeUsage(PrimeUsage primeUsage);
}
