/*
	Base10 to Base2
*/

public class BaseConverter {
	private final double two = 2;
	/*
		base2Converter takes a double in base 10 format and
		returns a String representation of the argument in
		base 2.

		Works similar to the base 10 method for breaking up
		a base 10 number into its individual digits.

		Basically get a starting index, i, which is used to 
		get the value 2^i, if 2^i % the num < num then return
		the remainder and i - 1, otherwise return a 0 and 
		decrease the index by 1. Keep decreasing the index
		until it gets to 0.
	*/	
	private String base2Converter(double base10Num) {
		double index = 0;
		double divisor = Math.pow(two, index);
		double remainder = 0;
		String result = "";

		while(base10Num % divisor != base10Num) {
			index++;
			divisor = Math.pow(two, index);
		}
		
		index--;

		while(index >= 0) {
			double mod = Math.pow(two, index);
			
			if (base10Num % mod == base10Num) { result += "0"; }
			else if(base10Num % mod == 0) { result += "1"; }
			else { result += "1"; } 

			base10Num = base10Num % mod;
			index--;
		}
		return result;
	}


	private String x(double num, double index) {
		String result = "";

		while(index >= 0) {
			double mod = Math.pow(two, index);
			
			if (num % mod == num) { result += "0"; }
			else if(num % mod == 0) { result += "1"; }
			else { result += "1"; } 

			num = num % mod;
			index--;
		}
		return result;
	}


	public static void main(String[] args) {
		BaseConverter x = new BaseConverter();
		System.out.println(x.base2Converter(585));

		System.out.println(Integer.toString(585, 2));
	}
}