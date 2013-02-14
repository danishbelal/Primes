package net.marco01809.primes.calculators;

public class PrimeFormatter {
	private String primeTag = "%P";

	// private int curPos;
	// private int begin= 0;

	public String format(String format) {
		// StringBuilder formatter = new StringBuilder(format);
		while (format.indexOf(primeTag) != -1) {

			format.replace(format, primeTag);
		}
		return format;
	}

	public String getPrimeTag() {
		return primeTag;
	}

	public void setPrimeTag(String primeTag) {
		this.primeTag = primeTag;
	}

}
