package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.PrimesGUI;

public class SieveOfErathosthenes extends PrimeCalculator {
	boolean[] primes = null;

	public SieveOfErathosthenes(PrimesGUI ui) {
		this(ui, "Sieb des Eratosthenes");
	}

	protected SieveOfErathosthenes(PrimesGUI ui, String name) {
		super(ui, name);
	}

	public boolean[] determinePrimes(int max) {
		primes = new boolean[max + 1];

		for (int i = 2; i < primes.length; i++)
			primes[i] = true;

		for (int o = 2; o * o < max; o++) {
			for (int i = o; i * o <= max; i++) {
				primes[i * o] = false;
			}
		}

		return primes;
	}

	@Override
	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}
}
