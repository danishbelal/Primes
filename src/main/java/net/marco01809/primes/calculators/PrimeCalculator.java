package net.marco01809.primes.calculators;

import net.marco01809.primes.PrimesModel;
import net.marco01809.primes.UI;

public abstract class PrimeCalculator extends PrimesModel
{
	protected PrimeCalculator(UI ui, String name)
	{
		super(ui, name);
	}

	public int getHighestDeterminableNumber()
	{
		return Integer.MAX_VALUE - 1;
	}

	public abstract boolean[] determinePrimes(int max);

	// Has to be overwritten by SieveOfEratosthenesBCD
	public boolean[] determinePrimes(long max)
	{
		return determinePrimes((int) max);
	}
}
