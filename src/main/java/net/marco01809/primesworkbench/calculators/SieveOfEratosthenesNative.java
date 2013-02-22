package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public class SieveOfEratosthenesNative extends PrimeCalculator {

	public SieveOfEratosthenesNative(UI ui, String name) {
		super(ui, name);
	}
	public SieveOfEratosthenesNative(UI ui)
	{
		super(ui, "Native:Sieb des Eratosthenes");
	}
//	FIXME: 'The return type is incompatible with PrimeCalculator.determinePrimes(long)'
//	public native int[] determinePrimes(long max);

	@Override
	public boolean[] determinePrimes(int max) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
