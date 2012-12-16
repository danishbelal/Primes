package net.marco01809.primes.calculators;

import net.marco01809.primes.UI;

public class SieveOfErathosthenes extends PrimeCalculator {
	boolean[] primes=null;
	int last = 0;
	

	public SieveOfErathosthenes(UI ui) {
		this(ui, "Sieb des Erathosthenes");
	}

	protected SieveOfErathosthenes(UI ui, String name) {
		super(ui, name);
	}

	public int getHighestDeterminableNumber() {
		return (int) Math.sqrt(Integer.MAX_VALUE) - 1;
	}

	public boolean[] determinePrimes(int max) {
		primes = new boolean[max + 1];

		for (int i = 2; i < primes.length; i++)
			primes[i] = true;
		// START CALULATION
		

		for (int i = 2; i < max; i++) {
			for (int j = i; ((j * i) -1) < max; j++) {
				primes[j * i] = false;
			}
		}

		for (int p = 0; p < primes.length; p++)
			if (primes[p])
				ui.determinedPrime(p);
		System.out.println("IN determinePrimes of SOE.class...");

		return primes;
	}
	//EXPERIMENTAL
	@SuppressWarnings("all")
	private int getNextPrime()
	{
		
		if(primes == null)
			throw new IllegalStateException("cannot get next prime");
		for(int i=last; i < primes.length ;i++)
		{
			if(primes[i])
			{
				last = i;
				return i;
			}
		}
			return 0;
	}
}
