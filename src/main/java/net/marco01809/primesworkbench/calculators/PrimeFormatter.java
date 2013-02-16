package net.marco01809.primesworkbench.calculators;

public class PrimeFormatter {
	private String primeTag = "%P";

	public String format(String format) {
		format.replace(format, primeTag);
		return format;
	}

	public String getPrimeTag() {
		return primeTag;
	}

	public void setPrimeTag(String primeTag) {
		this.primeTag = primeTag;
	}
}
