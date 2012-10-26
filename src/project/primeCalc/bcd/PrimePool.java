package project.primeCalc.bcd;

public class PrimePool {

	private final int MAX = 50000;
	private PrimeEntry[] primes;

	public PrimePool() {
		primes= new PrimeEntry[MAX];
	}

	public PrimePool(PrimeEntry[] primes) {
		this.primes = primes;
	}

	public PrimePool(int max) {
		primes= new PrimeEntry[max];
	}

	public PrimeEntry newInstance() {
		int i = 0;
		while (primes[i].isPrime && i < primes.length)
			;
		return primes[i];
	}


	public PrimeEntry[] getPrimes()
	{
		if (primes == null)
			throw new IllegalStateException("no primes to return");
		return primes;
	}
}
