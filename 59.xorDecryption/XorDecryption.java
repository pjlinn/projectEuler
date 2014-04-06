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

	***** THIS IS WRONG -- X1's are never next to each other, I want
	to collect the freq of X1X2 and X1X2X3 UGH.
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
	public static void main(String[] args) throws FileNotFoundException {
		
		Map<String, Integer> singleLetters = new HashMap<String,Integer>();
		Map<String, Integer> bigrams = new HashMap<String,Integer>();
		Map<String, Integer> trigrams = new HashMap<String,Integer>();

		// Frequencies
		Map<String, Integer> x1SingleLetters = new HashMap<String,Integer>();
		Map<String, Integer> x1Bigrams = new HashMap<String,Integer>();
		Map<String, Integer> x1Trigrams = new HashMap<String,Integer>();		

		// Just binning x1's in the order they come, easier to work with this
		ArrayList<String> x1Letters = new ArrayList<String>();
		ArrayList<String> x2Letters = new ArrayList<String>();
		ArrayList<String> x3Letters = new ArrayList<String>();

		Map<String, Integer> x2SingleLetters = new HashMap<String,Integer>();
		Map<String, Integer> x2Bigrams = new HashMap<String,Integer>();
		Map<String, Integer> x2Trigrams = new HashMap<String,Integer>();		

		Map<String, Integer> x3SingleLetters = new HashMap<String,Integer>();
		Map<String, Integer> x3Bigrams = new HashMap<String,Integer>();
		Map<String, Integer> x3Trigrams = new HashMap<String,Integer>();		

		int singleLettersCounter = 0;
		int bigramCounter = 0;
		int trigramCounter = 0;

		String singleLetter = "";
		String bigram = "";
		String trigram = "";

		int newSingleCount = 0;
		int newBigramCount = 0;
		int newTrigramCount = 0;

		int encryptionCounter = 0;

		String input = "cipher1.txt";
		String delimieter = ",";
		Scanner scanner = new Scanner(new File(input)).useDelimiter(delimieter);

		while(scanner.hasNext()) {
			singleLetter = scanner.next();
			
			bigram = bigram + " " + singleLetter;
			bigramCounter++;
			
			trigram += " " + singleLetter;
			trigramCounter++;

/*
========================================================================================
*/

			if (encryptionCounter == 0) {
				x1Letters.add(singleLetter + "x1,");
				encryptionCounter++;

				// if (x1SingleLetters.containsKey(singleLetter)) {
				// 	newSingleCount = x1SingleLetters.get(singleLetter) + 1;
				// 	x1SingleLetters.put(singleLetter, newSingleCount);
				// } else { x1SingleLetters.put(singleLetter, 1); }

			} else if (encryptionCounter == 1) {
				x2Letters.add(singleLetter + "x2,");
				encryptionCounter++;

				// if (x2SingleLetters.containsKey(singleLetter)) {
				// 	newSingleCount = x2SingleLetters.get(singleLetter) + 1;
				// 	x2SingleLetters.put(singleLetter, newSingleCount);
				// } else { x2SingleLetters.put(singleLetter, 1); }

			} else if (encryptionCounter == 2) {
				x3Letters.add(singleLetter + "x3,");
				encryptionCounter = 0;

				// if (x3SingleLetters.containsKey(singleLetter)) {
				// 	newSingleCount = x3SingleLetters.get(singleLetter) + 1;
				// 	x3SingleLetters.put(singleLetter, newSingleCount);
				// } else { x3SingleLetters.put(singleLetter, 1); }
			}

/*
========================================================================================
*/
			// if (singleLettersCounter == 0) {
			// 	if (singleLetters.containsKey(singleLetter)) {
			// 		newSingleCount = singleLetters.get(singleLetter) + 1;
			// 		singleLetters.put(singleLetter, newSingleCount);
			// 	} else {
			// 		singleLetters.put(singleLetter, 1);
			// 	}
			// }

			// if (bigramCounter == 2) {
			// 	if (bigrams.containsKey(bigram)) {
			// 		newBigramCount = bigrams.get(bigram) + 1;
			// 		bigrams.put(bigram, newBigramCount);
			// 	} else {
			// 		bigrams.put(bigram, 1);
			// 	}
			// 	bigramCounter = 0;
			// 	bigram = "";
			// }

			// if (trigramCounter == 3) {
			// 	if (trigrams.containsKey(trigram)) {
			// 		newTrigramCount = trigrams.get(trigram) + 1;
			// 		trigrams.put(trigram, newTrigramCount);
			// 	} else {
			// 		trigrams.put(trigram, 1);
			// 	}
			// 	trigramCounter = 0;
			// 	trigram = "";
			// }
/*
========================================================================================
*/
		}
/*
========================================================================================
*/
		int x1BigramCounter = 0;
		String x1Bigram = "";
		for (String x : x1Letters) {
			x1Bigram += x;
			x1BigramCounter++;
			if (x1BigramCounter == 2) {		
				if (x1Bigrams.containsKey(x1Bigram)) {
					x1Bigrams.put(x1Bigram, x1Bigrams.get(x1Bigram) + 1);
				} else { x1Bigrams.put(x1Bigram, 1); }
				x1Bigram = "";
				x1BigramCounter = 0;
			}
		}

		int x1TrigramCounter = 0;
		String x1Trigram = "";
		for (String x : x1Letters) {
			x1Trigram += x;
			x1TrigramCounter++;
			if (x1TrigramCounter == 3) {		
				if (x1Trigrams.containsKey(x1Trigram)) {
					x1Trigrams.put(x1Trigram, x1Trigrams.get(x1Trigram) + 1);
				} else { x1Trigrams.put(x1Trigram, 1); }
				x1Trigram = "";
				x1TrigramCounter = 0;
			}
		}
/*
========================================================================================
*/
		int x2BigramCounter = 0;
		String x2Bigram = "";
		for (String x : x2Letters) {
			x2Bigram += x;
			x2BigramCounter++;
			if (x2BigramCounter == 2) {		
				if (x2Bigrams.containsKey(x2Bigram)) {
					x2Bigrams.put(x2Bigram, x2Bigrams.get(x2Bigram) + 1);
				} else { x2Bigrams.put(x2Bigram, 1); }
				x2Bigram = "";
				x2BigramCounter = 0;
			}
		}

		int x2TrigramCounter = 0;
		String x2Trigram = "";
		for (String x : x2Letters) {
			x2Trigram += x;
			x2TrigramCounter++;
			if (x2TrigramCounter == 3) {		
				if (x2Trigrams.containsKey(x2Trigram)) {
					x2Trigrams.put(x2Trigram, x2Trigrams.get(x2Trigram) + 1);
				} else { x2Trigrams.put(x2Trigram, 1); }
				x2Trigram = "";
				x2TrigramCounter = 0;
			}
		}
/*
========================================================================================
*/
		int x3BigramCounter = 0;
		String x3Bigram = "";
		for (String x : x3Letters) {
			x3Bigram += x;
			x3BigramCounter++;
			if (x3BigramCounter == 2) {		
				if (x3Bigrams.containsKey(x3Bigram)) {
					x3Bigrams.put(x3Bigram, x3Bigrams.get(x3Bigram) + 1);
				} else { x3Bigrams.put(x3Bigram, 1); }
				x3Bigram = "";
				x3BigramCounter = 0;
			}
		}

		int x3TrigramCounter = 0;
		String x3Trigram = "";
		for (String x : x3Letters) {
			x3Trigram += x;
			x3TrigramCounter++;
			if (x3TrigramCounter == 3) {		
				if (x3Trigrams.containsKey(x3Trigram)) {
					x3Trigrams.put(x3Trigram, x3Trigrams.get(x3Trigram) + 1);
				} else { x3Trigrams.put(x3Trigram, 1); }
				x3Trigram = "";
				x3TrigramCounter = 0;
			}
		}
/*
========================================================================================
*/		
		for (Map.Entry x : x3Trigrams.entrySet()) {
			System.out.println(x.getKey() + " " + x.getValue());
		}
	}
}