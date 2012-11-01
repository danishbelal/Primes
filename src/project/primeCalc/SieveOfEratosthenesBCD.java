package project.primeCalc;

import project.UI;
import project.primeCalc.bcd.PrimeEntry;
import project.primeCalc.bcd.PrimePool;

public final class SieveOfEratosthenesBCD extends SieveOfErathosthenes {
	// private int arrayCount;
	boolean[][] primes;
	private PrimePool primePool;

	public SieveOfEratosthenesBCD(UI ui) {
		super(ui, "Sieb des Erathosthenes (BCD)");
	}

	public int getHighestDeterminableNumber() {
		return (int) Double.MAX_VALUE;
	}

	/* Very Early Buggy Version I suggest NOT to use it... */
	public boolean[] determinePrimes(long max) {

		// ---------------------BEGIN-----------------------

		System.out.println("Entered calculating phase (" + getClass().getName() + ")");
		primePool = new PrimePool(max);
		for (int i = 0; i < max; i++) // change condition to the Square Root of max...
		{
			for (int o = i; o * i < max; o++) {
				PrimeEntry e = primePool.newInstance();
				e.index = i * o;
				e.isPrime = true;
			}

		}
		// ----------------------END -----------------------

		// Begin Output

		System.out.println("Beginning Output (" + getClass().getName() + "");
		for (PrimeEntry e : primePool.getPrimes())
			if (e.isPrime)
				ui.determinedPrime(e.index);
		// ENd Output

		return null;
	}

	public boolean[] determinePrimes(int max) {

		determinePrimes((long) max);
		return new boolean[] { true };
		// return super.determinePrimes(max);
	}
}
