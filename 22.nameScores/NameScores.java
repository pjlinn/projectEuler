/*
	Using names.txt (right click and 'Save Link/Target As...'), a 
	46K text file containing over five-thousand first names, begin by 
	sorting it into alphabetical order. Then working out the alphabetical 
	value for each name, multiply this value by its alphabetical position 
	in the list to obtain a name score.

	For example, when the list is sorted into alphabetical order, COLIN, 
	which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the 
	list. So, COLIN would obtain a score of 938 × 53 = 49714.

	What is the total of all the name scores in the file?

	=======================================================

	Modified the file using sed, I removed all the "" so each name
	is separated by a , 
		sed 's/\"//g' names.txt > namesAdjusted

	there is also a .split() method and if I used an Array instead
	of an ArrayList Array has a sort method.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;


public class NameScores {

	public static int nameScore(String name) {

		int iNameLength = name.length();
		int iSum = 0;

		for (int i = 0; i < iNameLength; i++) {
			
			String letter = Character.toString(name.charAt(i));

			// System.out.println(letter);

			if (letter.equals("A")) { iSum += 1; }
			else if (letter.equals("B")) { iSum +=2; }
			else if (letter.equals("C")) { iSum +=3; }
			else if (letter.equals("D")) { iSum +=4; }
			else if (letter.equals("E")) { iSum +=5; }
			else if (letter.equals("F")) { iSum +=6; }
			else if (letter.equals("G")) { iSum +=7; }
			else if (letter.equals("H")) { iSum +=8; }
			else if (letter.equals("I")) { iSum +=9; }
			else if (letter.equals("J")) { iSum +=10; }
			else if (letter.equals("K")) { iSum +=11; }
			else if (letter.equals("L")) { iSum +=12; }
			else if (letter.equals("M")) { iSum +=13; }
			else if (letter.equals("N")) { iSum +=14; }
			else if (letter.equals("O")) { iSum +=15; }
			else if (letter.equals("P")) { iSum +=16; }
			else if (letter.equals("Q")) { iSum +=17; }
			else if (letter.equals("R")) { iSum +=18; }
			else if (letter.equals("S")) { iSum +=19; }
			else if (letter.equals("T")) { iSum +=20; }
			else if (letter.equals("U")) { iSum +=21; }
			else if (letter.equals("V")) { iSum +=22; }
			else if (letter.equals("W")) { iSum +=23; }
			else if (letter.equals("X")) { iSum +=24; }
			else if (letter.equals("Y")) { iSum +=25; }
			else if (letter.equals("Z")) { iSum +=26; }

		}

		return iSum;
	}

	public static void main(String[] args) throws IOException, 
	FileNotFoundException{
		
		int iSum = 0;
		/*
			Read in the adjusted names file
		*/
		BufferedReader reader = new BufferedReader(new FileReader("./namesAdjusted"));
		String text = reader.readLine();
		reader.close();
		/*
			Tokenize the file using the , as a delimiter
		*/
		StringTokenizer tokenizer = new StringTokenizer(text, ",");

		ArrayList<String> names = new ArrayList<String>();
		/*
			Add each token, or name, to the ArrayList
		*/
		while(tokenizer.hasMoreTokens()) {
			names.add(tokenizer.nextToken());
		}
		/*
			Sort the ArrayList alphabetically
		*/
		Collections.sort(names);
		/*
			Sum the nameScore by the alphabetical rank
		*/
		for (int i = 0; i < names.size(); i++) {
			iSum += (nameScore(names.get(i)) * (i + 1));
		}
		/*
			Print the sum, which is the answer!
		*/
		System.out.println(iSum);

		// for (String name : names) {
		// 	System.out.println(name);
		// }

		// int x = nameScore("COLIN");
		// System.out.println(x);
	}
}