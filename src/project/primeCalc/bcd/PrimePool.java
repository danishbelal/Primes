package project.primeCalc.bcd;

import project.UI;


public class PrimePool {

	private PrimeEntry[] primes;

	private void initArray() {
		//		new Throwable("[DEBUG]").printStackTrace(); // [DEBUG]
		for (int i = 0; i < primes.length; i++)
			primes[i] = new PrimeEntry();
	}

	public PrimePool(long max) {
		primes = new PrimeEntry[(int) max];
		initArray();
	}

	public PrimeEntry newInstance() {

		int i = 0;
		for (; i < primes.length; i++) //FIXME ArrayIndexOutOfBoundsException 10000 ?
		{
			if (!primes[i].isPrime)
				return primes[i];
		}
		
	
			return primes[i-1];
		
	}

	public PrimeEntry[] getPrimes() {
		if (primes == null)
			throw new IllegalStateException("no primes to return");
		return primes;
	}

	public void show(final UI ui)
	{
		for(PrimeEntry e : primes)
			if ( e.isPrime)
				ui.determinedPrime(e.index);
	}
}

