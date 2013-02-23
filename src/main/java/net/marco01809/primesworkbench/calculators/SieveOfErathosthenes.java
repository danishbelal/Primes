package net.marco01809.primesworkbench.calculators;

import net.marco01809.primesworkbench.UI;

public class SieveOfErathosthenes extends PrimeCalculator {
	boolean[] primes = null;

	public SieveOfErathosthenes(UI ui) {
		this(ui, "Sieb des Erathosthenes");
	}

	protected SieveOfErathosthenes(UI ui, String name) {
		super(ui, name);
	}

	public long getHighestDeterminableNumber() {

		return Integer.MAX_VALUE -1;
	}

	/**
	 * This Function determines all Primes between 2 and {@code max}
	 * 
	 * @return A boolean array where the index is the number you want to check,
	 *         e.g {@code determinePrimes(100)[10]} checks if the number 10 is a Prime Number.
	 * */
	public boolean[] determinePrimes(int max) {

//		 Um die berechneten Primzahlen zu speichern, nutze ich diese boolean Array, boolean ist der
//		 Wahrheitstyp von Java. Da wir allerdings mehr als nur ein boolean brauchen, verwende ich ein Array
//		 Die Assoziation zwischen Zahl und Wahrheitswert regelt der Index, über den ich auf den Wert zugreife
//		 primes[10] = false; zum Beispiel stellt sicher, dass 10 keine Primzahl ist.
		primes = new boolean[max + 1];

//		 Da alle Zahlen potentielle Primzahlen sind, setzen wir VOR der Berechnung, jeden
//		 "Array-Eintrag" auf true. Die Zahlen, die keine Primzahlen sind werden "ausgesiebt",
//		 sodass zum Schluss nur noch Primzahlen den Wahrheitswert true hinterlegt haben!
		for (int i = 2; i < primes.length; i++)
			primes[i] = true;

//		 Nun erfolgt die eigentliche Berechnung bzw. das "Sieben"...

//		 Zunächst wird die nächste zu streichende Zahl ausgewählt...
		for (int o = 2; o * o < max; o++) {
//			 ... dann alle Vielfachen dieser Zahl gebildet...
			for (int i = o; i * o <= max; i++) {
//				 ... und dann das Vielfache ( o * i ) gestrichen, also auf false gesetzt.
				primes[i * o] = false;
			}
		}

		for (int p = 0; p < primes.length; p++)
			if (primes[p])
				ui.determinedPrime(p);

//		 Das return gibt die berechneten Primzahlen dan den Aufrufer,
//		 damit dieser diese weiter verwenden kann, zum Beispiel um Zahlen
//		 zu faktorisieren.
		return primes;
	}

}
