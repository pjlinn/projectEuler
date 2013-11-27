/*
	Taken from Kareem_Mesbah on the Project Euler Forum. I want to
	try and understand the recursive approach and his looked like 
	one I could understand. 

	I did not write this code myself.
*/

public class CoinSumRecursive {
	private int[] coins = {1,2,5,10,20,50,100,200};
	private int ways = 0;

	private int getWays (int target, int elementIndex) {
		
		while (elementIndex > 0) {
			
			int coin = coins[elementIndex]; // start with 7 and go down
			int numberOfCoins = target / coin;

			while (numberOfCoins > 0) {
				int remainder = target - (numberOfCoins * coin);

				if (remainder > 0) {
					getWays(remainder, elementIndex - 1);
				}
				ways++;
				numberOfCoins--;
			}

			elementIndex--;
		}
		return ways + 1;
	}

	public static void main(String[] args) {
		CoinSumRecursive x = new CoinSumRecursive();
		System.out.println(x.getWays(200, 7));
	}
}