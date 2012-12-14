package net.marco01809.primes.primeUsage;

// TODO!
public class PrimeMaths {
	public final static int EUKLID = 0x1;
	public final static int PRIME_FACTORIZATION = 0x2;

	public static long kgV(int method, long first, long second) {
		switch (method) {

		case PRIME_FACTORIZATION:
			break;

		default:
			throw new UnsupportedOperationException(" the method is not defined ! ");
		}

		return 0L;
	}

	public static long ggT(int method, long first, long second) {
		switch (method) {
		case EUKLID:
			return ggTEuklid(first, second);

		default:
			throw new IllegalArgumentException("method not defined !");
		}
	}

	private static long ggTEuklid(long first, long second) {
		if (first == 0)
			return second;

		while (second != 0)
			if (first > second)
				first -= second;
			else
				second -= first;

		return first;
	}
}
