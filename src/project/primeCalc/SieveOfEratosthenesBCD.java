package project.primeCalc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import project.PrimesApplication;
import project.UI;

public class SieveOfEratosthenesBCD extends SieveOfErathosthenes {

    private UI ui;
    private int arrayCount;
    boolean[][] primes;

    public SieveOfEratosthenesBCD(UI ui) {
	super(ui);
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

	arrayCount = (int) max / (Integer.MAX_VALUE - 1);
	try {
	    primes = new boolean[Math.abs(arrayCount)][Integer.MAX_VALUE];
	} catch (OutOfMemoryError e) {
	    e.fillInStackTrace();

	    PrimesApplication.error(e, false);
	}

	System.out.println("\t arrayCount = " + (long) arrayCount
		+ "\n\t max = " + max);
	// Storing Primes ...
	HashMap<Integer, Boolean> primes = new HashMap<Integer, Boolean>();
	for (int i = 2; i < max + 1; i++)
	    primes.put(Integer.valueOf(i), true);

	// Calculating Primes ...
	for (int i = 2; i <= max; i++)
	    for (int o = i; o * i < max; o++)

		primes.put(Integer.valueOf(i * o), false);

	for (int i = 0; i < primes.values().size(); i++)
	    for (Boolean b : primes.values())
		if (b.booleanValue())
		    ui.determinedPrime(i);// Do some stuff here...

	return null;
    }

    public boolean[] determinePrimes(int max) {

	determinePrimes((long) max);
	return new boolean[] { true };
	// return super.determinePrimes(max);
    }

}
