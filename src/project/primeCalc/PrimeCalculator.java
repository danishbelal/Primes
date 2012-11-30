package project.primeCalc;

import project.PrimesModel;
import project.UI;

public abstract class PrimeCalculator extends PrimesModel {
	protected PrimeCalculator(UI ui, String name) {
		super(ui, name);
	}

	public int getHighestDeterminableNumber() {
		return Integer.MAX_VALUE - 1;
	}

	public abstract boolean[] determinePrimes(int max);
}
