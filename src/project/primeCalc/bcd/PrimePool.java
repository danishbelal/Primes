package project.primeCalc.bcd;


public class PrimePool {

	
	private PrimeEntry[] primes;

	private void initArray() {
		for (int i = 0; i < primes.length; i++)
			primes[i] = new PrimeEntry();
	}

	

	
	public PrimePool(long max) {
		primes = new PrimeEntry[ (int ) max  ];
		initArray();
	}

	public PrimeEntry newInstance() {
		
		int i = 0;
		for( ; i < primes.length & primes[i].isPrime ; i++) //FIXME ArrayIndexOutOfBoundsException 10000 ?
			;
		
		if(i < primes.length)
			return primes[i];
		return null;
	}
	

	public PrimeEntry[] getPrimes() {
		if (primes == null)
			throw new IllegalStateException("no primes to return");
		return primes;
	}
}
