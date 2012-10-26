package project.primeCalc;

import project.UI;
import project.primeCalc.bcd.PrimeEntry;
import project.primeCalc.bcd.PrimePool;

public class SieveOfEratosthenesBCD extends SieveOfErathosthenes {

	private UI ui;
	// private int arrayCount;
	boolean[][] primes;
	private PrimePool primePool = new PrimePool();

	public SieveOfEratosthenesBCD(UI ui) {
		super(ui); // useless but required 
		this.ui = ui;
		

	}

	public String getName() {
		return "Sieb des Erathosthenes_BCD";
	}

	public int getHighestDeterminableNumber() {
		return (int) Double.POSITIVE_INFINITY;
	}

	/* Very Early Buggy Version I suggest NOT to use it... */
	public boolean[] determinePrimes(long max) {

		// ---------------------BEGIN-----------------------

		for (int i = 0; i < max; i++) // change condition to the Square Root of max...
		{
			for (int o = i; o * i < max; o++)
			{
				PrimeEntry e =primePool.newInstance();
				e.index= i*o;
				e.isPrime = true;
			}

		}
		// ----------------------END -----------------------

		// Begin Output
		
		for(PrimeEntry e : primePool.getPrimes())
			if(e.isPrime)
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
