package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public final class PrimeBruter extends PrimeCalculator {
	
	public PrimeBruter(UI ui) {
		super(ui, "Einfaches Durchtesten");
	}

	/**
	 * This Function determines all Primes between 2 and {@code max}.
	 * @return A boolean array where the index is the number you want to check,
	 * e.g {@code determinePrimes(100)[10]} checks if the number 10 is a Prime Number.
	 *  
	 * @see SieveOfErathosthenes#determinePrimes(int)
	 * @see SieveOfEratosthenesNative#determinePrimes(int)
	 * */
	public boolean[] determinePrimes(int max) {
		boolean[] primes = new boolean[max + 1];

		boolean isPrime = true;
		int currentNumber;
//		Increase the currentNumber...
		for (currentNumber = 2; currentNumber <= max; currentNumber++) {
			isPrime = true;
//			is it a Prime Number
			for (int divisor = 2; isPrime && (divisor < currentNumber); divisor++) {
				if (currentNumber % divisor == 0) {
//-->				In this Case its not!
					isPrime = false;
				}
			}

			if (isPrime) {
//-->			In this Case it is a Prime Number!
				ui.determinedPrime(currentNumber);
				primes[currentNumber] = true;
			}
		}

		return primes;
	}
	
	/**
	 * @return The highest determinable Number with the choosen Calculation Method.
	 * 
	 * @see PrimeCalculator#getHighestDeterminableNumber()
	 * @see SieveOfErathosthenes#getHighestDeterminableNumber()
	 * @see SieveOfEratosthenesNative#getHighestDeterminableNumber()
	 * */
	@Override
	public long getHighestDeterminableNumber()
	{
		return Integer.MAX_VALUE;
	}
}
