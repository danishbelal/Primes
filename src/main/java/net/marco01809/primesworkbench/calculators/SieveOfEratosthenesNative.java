package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public class SieveOfEratosthenesNative extends PrimeCalculator {

	protected SieveOfEratosthenesNative(UI ui, String name) {
		super(ui, name);
	}

	public SieveOfEratosthenesNative(UI ui) {
		this(ui, "Native:Sieb des Eratosthenes");
	}

	public long getHighestDeterminableNumber() {
		return Long.MAX_VALUE;
	}

//	FIXME: 'The return type is incompatible with PrimeCalculator.determinePrimes(long)'
//	public native int[] determinePrimes(long max);

	@Override
	public boolean[] determinePrimes(int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
