/*
	The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so 
	the first ten triangle numbers are:

	1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

	By converting each letter in a word to a number corresponding to its 
	alphabetical position and adding these values we form a word value. For 
	example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word 
	value is a triangle number then we shall call the word a triangle word.

	Using words.txt (right click and 'Save Link/Target As...'), a 16K text 
	file containing nearly two-thousand common English words, how many are 
	triangle words?
*/

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class CodedTriangleNumbers {

	// Creates a map of triangle numbers
	private static HashMap<Double, Double> triangleNumMap(int nthTerm) {
		Double triangleNum = 0.;
		HashMap<Double, Double> triangleNumMap = new HashMap<Double, Double>();
		for (Double i = 1.; i <= nthTerm; i++) {
			triangleNum = 1 / 2. * i * (i + 1);
			triangleNumMap.put(i, triangleNum);
		}
		return triangleNumMap;
	}

	// Creates and ArrayList of the words from the attached file
	private static ArrayList<String> wordsFileReader() throws FileNotFoundException {

		ArrayList<String> words = new ArrayList<String>();

		Scanner reader = new Scanner (new File("modifiedWords.txt"));
		reader.useDelimiter(",");

		while (reader.hasNext()) {
			words.add(reader.next());
		}
		return words;
	}

	// HashMap of letters and their values
	private static void alphabetValueMap() {
	}

	// Calculates the value of the word, which is checked against triangle numbers
	private static Double calculateWordValue(String word) {
		HashMap<String, Integer> letterValue = new HashMap<String, Integer>();
		letterValue.put("A",1); letterValue.put("J", 10); letterValue.put("S", 19);
		letterValue.put("B",2); letterValue.put("K", 11); letterValue.put("T", 20);
		letterValue.put("C",3); letterValue.put("L", 12); letterValue.put("U", 21);
		letterValue.put("D",4); letterValue.put("M", 13); letterValue.put("V", 22);
		letterValue.put("E",5); letterValue.put("N", 14); letterValue.put("W", 23);
		letterValue.put("F",6); letterValue.put("O", 15); letterValue.put("X", 24);
		letterValue.put("G",7); letterValue.put("P", 16); letterValue.put("Y", 25);
		letterValue.put("H",8); letterValue.put("Q", 17); letterValue.put("Z", 26);
		letterValue.put("I",9); letterValue.put("R", 18);

		int length = word.length();
		Double wordValue = 0.;
		int letter = 0;

		for (int i = 0; i < length; i++) {
			letter = letterValue.get(word.substring(i, i + 1));
			wordValue += letter;
		}
		return wordValue;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> words = new ArrayList<String>(wordsFileReader());
		int counter = 0;
		Double max = 0.;
		// Determines the max value of the words to use as the nth term. Overkill.
		for (String word : words) {
			max = (calculateWordValue(word) > max) ? calculateWordValue(word) : max;
		}

		HashMap<Double, Double> triangleNumMap = new HashMap<Double, Double>(triangleNumMap(max.intValue()));

		for (String word : words) {
			if (triangleNumMap.containsValue(calculateWordValue(word))) {
				counter++;	
			}
		}

		System.out.println(max + " " + counter);
	}
}