/*
	Each character on a computer is assigned a unique code and the 
	preferred standard is ASCII (American Standard Code for Information 
	Interchange). For example, uppercase A = 65, asterisk (*) = 42, 
	and lowercase k = 107.

	A modern encryption method is to take a text file, convert the 
	bytes to ASCII, then XOR each byte with a given value, taken 
	from a secret key. The advantage with the XOR function is that 
	using the same encryption key on the cipher text, restores the 
	plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.

	For unbreakable encryption, the key is the same length as the 
	plain text message, and the key is made up of random bytes. The 
	user would keep the encrypted message and the encryption key in 
	different locations, and without both "halves", it is impossible 
	to decrypt the message.

	Unfortunately, this method is impractical for most users, so 
	the modified method is to use a password as a key. If the password 
	is shorter than the message, which is likely, the key is repeated 
	cyclically throughout the message. The balance for this method is 
	using a sufficiently long password key for security, but short
	 enough to be memorable.

	Your task has been made easy, as the encryption key consists of 
	three lower case characters. Using cipher1.txt (right click and 
	'Save Link/Target As...'), a file containing the encrypted ASCII 
	codes, and the knowledge that the plain text must contain common 
	English words, decrypt the message and find the sum of the ASCII 
	values in the original text.


	-------------------

	Never done this before trying to do frequency anaylsis I saw 
	on Wikipedia (http://en.wikipedia.org/wiki/Frequency_analysis).
	I also wanted to use code to bin everything. This
	is probably a really stupid way to go about this and code what 
	it is I am trying to do, but oh well.
	
	Solved it, I basically used a frequency analysis and then looked
	at what the 26 possibilities were of the most frequent character
	based on the 26 letters it could be encrypted with. The space
	was the obvious answer, so that gave me the password letter and 
	then I just solved it.

	This code is complete crap. Such as mess...
*/

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.TreeMap;

public class XorDecryption {

	// Passed the key and the encrypted value, prints the decrypted value
	private static int printDecryption(int key, String encryptedValue) {
		int decryptedValue = xor(Integer.parseInt(encryptedValue), key);
		System.out.print(Character.toString((char)decryptedValue));

		return xor(Integer.parseInt(encryptedValue), key);
	}

	// Performs the XOR operation, returns the resulting ASCII value
	private static int xor(int key, int encryptedValue) {
		// Go from int to base 2 String
		String sKey = Integer.toString(key, 2);
		String sEncryptedValue = Integer.toString(encryptedValue, 2);

		// Calculate String length, strings need to be same length for XOR
		int keyLength = sKey.length();
		int encryptedValueLength = sEncryptedValue.length();

		// Loop through adding 0's until the length is the same 
		while(keyLength != encryptedValueLength) {
			if (keyLength < encryptedValueLength) {
				sKey = "0" + sKey;
			}
			else if (encryptedValueLength < keyLength) {
				sEncryptedValue = "0" + sEncryptedValue;
			}
			keyLength = sKey.length();
			encryptedValueLength = sEncryptedValue.length();
		}

		String encryptValue = "";
		String xorValue = "";

		// XOR Operation
		for (int i = 0; i < keyLength; i++) {
			String keyValue = sKey.substring(i, i+1);
			encryptValue = sEncryptedValue.substring(i, i+1);

			if (keyValue.equals(encryptValue)) {
				xorValue += "0";
			} else {
				xorValue += "1";
			}

			// System.out.println(keyValue + " " + encryptValue + " = " + xorValue);
		}

		int binaryX = Integer.parseInt(xorValue, 2);

		// System.out.println(encryptedValue + " XOR " + key + " = " + Character.toString((char)binaryX));

		return binaryX;
	}

	// Creates a Map of the freqency of each bigram
	// Right now returns nothing, but can return that map
	private static void bigramFreq(ArrayList<String> allLetters) {
		Map<String, Integer> bigrams = new HashMap<String,Integer>();
		
		int bigramCounter = 0;
		
		String bigram = "";

		for (String letter : allLetters) {
			bigram = bigram + " " + letter;
			bigramCounter++;

			if (bigramCounter == 2) {
				if (bigrams.containsKey(bigram)) {
					bigrams.put(bigram, bigrams.get(bigram) + 1);
				} else {
					bigrams.put(bigram, 1);
				}
				bigramCounter = 0;
				bigram = "";
			}
		}
	}

	// Creates a Map of the freqency of each trigram
	// Right now returns nothing, but can return that map
	private static void trigramFreq(ArrayList<String> allLetters) {
		Map<String, Integer> trigrams = new HashMap<String,Integer>();	

		int trigramCounter = 0;

		String trigram = "";

		for (String letter : allLetters) {

			trigram = trigram + " " + letter;
			trigramCounter++;

			if (trigramCounter == 3) {
				if (trigrams.containsKey(trigram)) {
					trigrams.put(trigram, trigrams.get(trigram) + 1);
				} else {
					trigrams.put(trigram, 1);
				}
				trigramCounter = 0;
				trigram = "";	
			}
		}

		// for (Map.Entry x : trigrams.entrySet()) {
		// 	System.out.println(x.getKey() + " " + x.getValue());
		// }
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Map<String, Integer> singleLetters = new HashMap<String,Integer>();	

		// Just binning x1's in the order they come, easier to work with this
		ArrayList<String> x1Letters = new ArrayList<String>();
		ArrayList<String> x2Letters = new ArrayList<String>();
		ArrayList<String> x3Letters = new ArrayList<String>();
		ArrayList<String> allLetters = new ArrayList<String>();

		int singleLettersCounter = 0;
		int encryptionCounter = 0;
		int answer = 0;

		String singleLetter = "";

		final int password1 = 103;
		final int password2 = 111;
		final int password3 = 100;

		String input = "cipher1.txt";
		String delimieter = ",";
		Scanner scanner = new Scanner(new File(input)).useDelimiter(delimieter);

		while(scanner.hasNext()) {
			singleLetter = scanner.next();

			if (encryptionCounter == 0) {
				x1Letters.add(singleLetter + "x1,");
				allLetters.add(singleLetter + "x1,");
				encryptionCounter++;

				answer += printDecryption(password1, singleLetter);

			} else if (encryptionCounter == 1) {
				x2Letters.add(singleLetter + "x2,");
				encryptionCounter++;
				allLetters.add(singleLetter + "x2,");

				answer += printDecryption(password2, singleLetter);

			} else if (encryptionCounter == 2) {
				x3Letters.add(singleLetter + "x3,");
				encryptionCounter = 0;
				allLetters.add(singleLetter + "x3,");

				answer += printDecryption(password3, singleLetter);				
			}

		}

		// Possible characters for password character 1
		ArrayList<Integer> passwordChar1 = new ArrayList<Integer>();
		ArrayList<Integer> passwordChar2 = new ArrayList<Integer>();
		ArrayList<Integer> passwordChar3 = new ArrayList<Integer>();

		// Loop through lower case ASCII codes, if the result isn't a possibility, exclude it
		// Manually playing around with which value to decrypt, using the most frequent from 
		// previous analysis.
		for (int i = 97; i < 123; i++) {
			int x = xor(i, 71);
			// Excluded possible values due to problem spec
			if (x < 58 && x > 47 || x > 32 && x < 44) {
				continue;
			} else {
				passwordChar1.add(i);
			}
		}

		// Look and see what the possibilities are
		for (Integer x : passwordChar1) {
			// printDecryption(x, "71");
		}

		for (int i = 97; i < 123; i++) {
			int x = xor(i, 79);
			if (x < 58 && x > 47 || x > 32 && x < 44) {
				continue;
			} else {
				passwordChar2.add(i);
			}
		}

		// System.out.println(passwordChar2);

		for (Integer x : passwordChar2) {
			// printDecryption(x, "79");
			// System.out.println();
		}

		for (int i = 97; i < 123; i++) {
			int x = xor(i, 68);
			if (x < 58 && x > 47 || x > 32 && x < 44) {
				continue;
			} else {
				passwordChar3.add(i);
			}
		}

		// System.out.println(passwordChar3);

		for (Integer x : passwordChar3) {
			// printDecryption(x, "68");
			// System.out.println();
		}

		System.out.println("\n" + answer);

	}
}