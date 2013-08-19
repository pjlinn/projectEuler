/*
	Smallest Multiple (Problem 5):
		2520 is the smallest number that can be divided by 
		each of the numbers from 1 to 10 without any remainder.

		What is the smallest positive number that is evenly 
		divisible by all of the numbers from 1 to 20?
*/

/*
	Fairly straightforward. Hardcoded to try each possibility. Don't
	need to try anything less than 11 becuase 10 and below are 
	factors of numbers we already tried.

	Could maybe look at later for a more dynamic implementation.
*/
public class SmallestMultiple {
	public static int smallestMultiple() {
		boolean smallestMultiple = false;

		int counter = 40;

		while(!smallestMultiple) {
			smallestMultiple = (counter % 20 == 0 && counter % 19 == 0 && counter % 18 == 0 &&
				counter % 17 == 0 && counter % 16 == 0 && counter % 15 == 0 &&
				counter % 14 == 0 && counter % 13 == 0 && counter % 12 == 0 &&
				counter % 11 == 0 && counter % 10 == 0) ? true : false;
			counter += 1;
		}
		return counter - 1;
	}

	public static void main(String[] args) {
		long curTime = System.currentTimeMillis();
		System.out.println(smallestMultiple());
		System.out.println(" -> Time: " + (System.currentTimeMillis() - curTime) + 
			" ms");
	}
}