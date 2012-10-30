package project.primeCalc.bcd;

public class PrimePool {

	private final int MAX = 50000;
	private PrimeEntry[] primes;
		
	
	private void initArray()
	{
		for(int i=0; i< primes.length;i++)
			primes[i]=new PrimeEntry();
	}
	
	public PrimePool() {
		primes= new PrimeEntry[MAX];
		initArray();
	}

	public PrimePool(PrimeEntry[] primes) {
		this.primes = primes;
		initArray();
	}

	public PrimePool(int max) {
		primes= new PrimeEntry[max];
		initArray();
	}

	public PrimeEntry newInstance() {
		int i = 0;
		for (;i < primes.length && primes[i].isPrime ; i++)
			;
		if(i < primes.length)
			return primes[i];
		return null; 
	}


	public PrimeEntry[] getPrimes()
	{
		if (primes == null)
			throw new IllegalStateException("no primes to return");
		return primes;
	}
}
