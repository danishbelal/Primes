package net.marco01809.primes.calculators.bcd;

public class EndlessArray{
	boolean[][] array ;

	// FIXME split up into the dimension...
	public EndlessArray(long max)
	{
		int firstDimension 	= (int) (max / (long)Integer.MAX_VALUE);
		int secondDimension	= (int) (max - firstDimension * Integer.MAX_VALUE);
//		System.out.println("\n"+array.toString()+ "\n");
//		System.out.println("\n"+"firstDimension = "+ firstDimension+ "\n");
//		System.out.println("\n"+"secondDimension =" +secondDimension + "\n");
		array = new boolean[firstDimension][secondDimension];
	}
	
	
	
	
}
