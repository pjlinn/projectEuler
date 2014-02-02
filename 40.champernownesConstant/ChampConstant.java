/*
	An irrational decimal fraction is created by concatenating the 
	positive integers:

	0.123456789101112131415161718192021...

	It can be seen that the 12th digit of the fractional part is 1.

	If dn represents the nth digit of the fractional part, find the value of 
	the following expression.

	d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

	-----------

	So I thought this one was pretty straightforward:
		- Create a string and start concatenating integers until the length
			was greater than 1,000,000 and then take the substrings of the
			String and multiply them.
	However, the generation of the String took awhile, and I'm not exactly 
	sure why, or what was so data intensize. I ended up just waiting out
	the generation of the String and saving it in a file and then practcing
	how to read in a file as a string.

	Update:
	Seems += concatenation is slow, I'm going to trying StringBuilder
*/

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.lang.StringBuilder;

public class ChampConstant {

	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	public static void main(String[] args) throws IOException {
		int nextInt = 1;
		StringBuilder sb = new StringBuilder();

		while(sb.length() < 1000003) {
			sb.append(nextInt);
			nextInt++;
		}

		String champConstant = sb.toString();

		// champConstant = readFile("output.txt", StandardCharsets.UTF_8);

		int d = 1;
		int result = 1;

		while(d < 1000001) {
			result *= Integer.parseInt(champConstant.substring(d-1, d));
			d *= 10;
		}

		System.out.println(result);
	}
}