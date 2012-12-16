package net.marco01809.primes.calculators;

import net.marco01809.primes.PrimesApplication;
import net.marco01809.primes.UI;
import net.marco01809.primes.calculators.bcd.PrimeEntry;
import net.marco01809.primes.calculators.bcd.PrimePool;

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
		PrimeEntry entry = null;
		int result = 0; //change to long
		System.out.println("Beginning Calculation \t(" + getClass().getName() + ")\n");

		PrimePool prime = new PrimePool(max);

		for (int i = 1; i < max; i++)
			for (int o = i; (result = o * i) < max; o++) {
				entry = prime.newInstance();
				if (entry == null)
					PrimesApplication.error(new InternalError(" \"prime.newInstance returned null ! \"\n"), true);
				entry.isPrime = false;
				entry.index = result;
			}

		// ----------------------END -----------------------

		// Begin Output
		System.out.println("Beginning Output \t(" + getClass().getName() + ")\n");

		prime.show(ui);

		// End Output

		return null;
	}

	public boolean[] determinePrimes(int max) {

		determinePrimes((long) max);
		return new boolean[] { true };
		// return super.determinePrimes(max);
	}
}
