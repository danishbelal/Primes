package project.primeCalc;

import java.util.Set;

public interface PrimeCalculator {
	public String getName();

	public boolean[] calculatePrimes(int start, int max);
	public Set<Integer> calculatePrimes(int amount);
}
