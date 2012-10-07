package project.primeCalc;

public interface PrimeCalculator {
	public String getName();

	public int getHighestDeterminableNumber();

	public boolean[] determinePrimes(int max);
}
