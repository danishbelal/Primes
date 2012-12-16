package net.marco01809.primes.calculators;

import net.marco01809.primes.UI;
import net.marco01809.primes.calculators.bcd.EndlessArray;

public final class SieveOfEratosthenesBCD extends SieveOfErathosthenes {
	// private int arrayCount;
	boolean[][] primes;

	public SieveOfEratosthenesBCD(UI ui) {
		super(ui, "Sieb des Erathosthenes (BCD)");
	}

	public int getHighestDeterminableNumber() {

		return (int) Integer.MAX_VALUE;

	}

	/* Very Early Buggy Version I suggest NOT to use it... */
	public boolean[] determinePrimes(long max) {

		// ---------------------BEGIN-----------------------
		
			new EndlessArray(max);

		// ----------------------END -----------------------

		// Begin Output
		

		// End Output

		return null;
	}

	public boolean[] determinePrimes(int max) {

		determinePrimes((long) max);
		return new boolean[] { true };
		// return super.determinePrimes(max);
	}
}
