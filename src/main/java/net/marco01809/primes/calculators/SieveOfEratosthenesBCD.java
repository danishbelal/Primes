package net.marco01809.primes.calculators;

import net.marco01809.primes.PrimesApplication;
import net.marco01809.primes.UI;
import net.marco01809.primes.calculators.bcd.PrimeEntry;

public final class SieveOfEratosthenesBCD extends SieveOfErathosthenes
{
	// private int arrayCount;
	PrimeEntry[][] primes;
	UI ui;
	int stackTop = 0;
	int cDimension = 0;
	int cDimensionSize = 1;

	public SieveOfEratosthenesBCD(UI ui)
	{
		super(ui, "Sieb des Erathosthenes (BCD)");
	}

	public int getHighestDeterminableNumber()
	{
		return (int) Integer.MAX_VALUE;
	}

	/* Very Early Buggy Version I suggest NOT to use it... */
	public boolean[] determinePrimes(long max)
	{
		PrimesApplication.error(new InternalError("YES"), false);
		if (max / Integer.MAX_VALUE > 1)
			throw new Error("indev");

		primes = new PrimeEntry[cDimensionSize][(int) max];
		for (int i = 0; i < primes.length; i++)
		{
			for (int o = 0; o < primes[0].length; o++)
				primes[i][o] = new PrimeEntry();
		}

		return null;
	}

	public boolean[] determinePrimes(int max)
	{

		return super.determinePrimes(max);
	}
}
