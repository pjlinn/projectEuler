/*
	You are given the following information, but you may 
	prefer to do some research for yourself.

	1 Jan 1900 was a Monday.
	Thirty days has September,
	April, June and November.
	All the rest have thirty-one,
	Saving February alone,
	Which has twenty-eight, rain or shine.
	And on leap years, twenty-nine.
	
	A leap year occurs on any year evenly divisible by 4, but 
	not on a century unless it is divisible by 400.
	How many Sundays fell on the first of the month during the 
	twentieth century (1 Jan 1901 to 31 Dec 2000)?

	Jan  - 31
	Feb  - 28/29
	Mar  - 31
	Apr  - 30
	May  - 31
	June - 30 
	July - 31
	Aug  - 31
	Sept - 30
	Oct  - 31
	Nov  - 30
	Dec  - 31
*/


public class CountingSundays {

	public static boolean isLeapYear(int num) {
		if (num % 4 == 0 && num % 100 != 0) {
			return true;
		} else if (num % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}


	/*
		This method determines the starting day of year given
		the day of Jan 1 of the previous year.

		Returns an int which stands for a day:
		Mon   - 1
		Tues  - 2
		Wed   - 3
		Thurs - 4
		Fri   - 5
		Sat   - 6
		Sun   - 7
	*/
	public static int startingDay(int num) {

		// figure out how many days in the year
		int days = (isLeapYear(num) == false) ? 365 : 366;
		
		// System.out.println(days);

		int count = 0;	

		for (int x = 0; x < days; x++) {
			count++; // JAn 1 1900 was a Monday (given)

			if (count % 8 == 0) {
				count = 1;
			}
		}

		// count stops in the for loop on the last day of the year, 
		// add 1 for the start of the following year
		return count;
	}

	public static int totalDays() {
		int year = 365;
		int leapYear = 366;
		int sum = 0;

		for (int x = 1901; x < 2001; x++) {
			if (isLeapYear(x) == true) {
				sum += leapYear;
			} else {
				sum += year;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		
		int sundayCount = 0;
		int iStartingDay = startingDay(1900);
		int year = 1900;
		int month = 0;


		/*
			Didn't really need all the methods, I misunderstood
			the question for awhile, and was answering the wrong
			question.
		*/
		for (int i = 1901; i < 2001; i++) {
			for (int j = 1; j < 13; j++) {
				
				int k = 0;

				if (isLeapYear(i) == true) {
					if (j == 1) {
						k = 31;
					} else if (j == 2) {
						k = 29;
					} else if (j == 3) {
						k = 31;
					} else if (j == 4) {
						k = 30;
					} else if (j == 5) {
						k = 31;
					} else if (j == 6) {
						k = 30;
					} else if (j == 7) {
						k = 31;
					} else if (j == 8) {
						k = 31;
					} else if (j == 9) {
						k = 30;
					} else if (j == 10) {
						k = 31;
					} else if (j == 11) {
						k = 30;
					} else if (j == 12) {
						k = 31;
					}
				} else {
					if (j == 1) {
						k = 31;
					} else if (j == 2) {
						k = 28;
					} else if (j == 3) {
						k = 31;
					} else if (j == 4) {
						k = 30;
					} else if (j == 5) {
						k = 31;
					} else if (j == 6) {
						k = 30;
					} else if (j == 7) {
						k = 31;
					} else if (j == 8) {
						k = 31;
					} else if (j == 9) {
						k = 30;
					} else if (j == 10) {
						k = 31;
					} else if (j == 11) {
						k = 30;
					} else if (j == 12) {
						k = 31;
					}					
				}

				for (int l = 1; l <= k; l++) {
					iStartingDay++;

					if (l == 1 && iStartingDay == 7) {
						sundayCount++;
					}

					if (iStartingDay == 8) {
						iStartingDay = 1;
					}
				}

			}
		}

		System.out.println(sundayCount);
	}
}