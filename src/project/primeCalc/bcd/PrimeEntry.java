package project.primeCalc.bcd;

public class PrimeEntry {

	public int index;
	public boolean isPrime;

	public PrimeEntry() {
		index = 0;
		isPrime = false;
	}

	public PrimeEntry(int index, boolean isPrime) {
		this.index = index;
		this.isPrime = isPrime;
	}
}
