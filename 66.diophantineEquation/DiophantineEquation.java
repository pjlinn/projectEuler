/*
	Consider quadratic Diophantine equations of the form:

	x2 – Dy2 = 1

	For example, when D=13, the minimal solution in x is 
	6492 – 13×1802 = 1.

	It can be assumed that there are no solutions in 
	positive integers when D is square.

	By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, 
	we obtain the following:

	32 – 2×22 = 1
	22 – 3×12 = 1
	92 – 5×42 = 1
	52 – 6×22 = 1
	82 – 7×32 = 1

	Hence, by considering minimal solutions in x for D ≤ 7, 
	the largest x is obtained when D=5.

	Find the value of D ≤ 1000 in minimal solutions of x for 
	which the largest value of x is obtained.

	------------------------------------------

	Depricated. Stop using this code because it was doomed. Copied
	over code from problem 64 and used that as a starting point.
*/

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.lang.Math;
import java.math.BigInteger;

public class DiophantineEquation {

	public static void main(String[] args) {
		
		HashSet<BigInteger> setOfSqauresMinusOne = new HashSet<BigInteger>();
		HashSet<BigInteger> setOfSqaures = new HashSet<BigInteger>();
		ArrayList<BigInteger> arrayListOfSquares = new ArrayList<BigInteger>();

		int limit = 20000000; // 100000000

		for (int i = 1; i <= limit; i++ ) {
			BigInteger square = new BigInteger(String.valueOf(i));
			square = square.multiply(square);

			BigInteger squareMinusOne = new BigInteger("0");
			squareMinusOne = square.subtract(BigInteger.ONE);

			setOfSqauresMinusOne.add(squareMinusOne);
			setOfSqaures.add(square);
			arrayListOfSquares.add(square);
		}

		for (int a = 1; a <= 1000; a++) {
			BigInteger perfectSquareCheck = new BigInteger(String.valueOf(a));
			if (setOfSqaures.contains(perfectSquareCheck)) {
				// System.out.println("skipping " + a);
				continue;
			}
			for (int y = 1; y <= limit; y++) {
				Boolean stop = false;
				BigInteger d = new BigInteger(String.valueOf(a));
				stop = setOfSqauresMinusOne.contains(d.multiply(arrayListOfSquares.get(y - 1)));

				if (stop) {
					System.out.println("D: " + a + " y^2: " + arrayListOfSquares.get(y - 1));
					break;
				}

				if (y == limit) {
					System.out.println("No Solution for " + a);
				}
			}
		}
	}
}