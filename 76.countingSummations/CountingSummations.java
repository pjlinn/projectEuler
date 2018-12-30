/**
 * It is possible to write five as a sum in exactly six different ways:
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 *
 *  *
 *  *
 *  * 3 + 1 + 1
 *  * 2 + 2 + 1
 *  * 2 + 1 + 1 + 1
 *  * 1 + 1 + 1 + 1 + 1
 * 
 * How many different ways can one hundred be written
 * as a sum of at least two positive integers?
 * -----------------
 */

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CountingSummations {

    static Map<Integer, List<Integer>> summations = new HashMap<>();

    public static List<Integer> buildArrayList(Integer n, Integer i, List<Integer> sum) {
        // when the base number is equal to the next level, i, stop
        if (n == i) {
            return sum;
        } else {
        // starting partition for a given i
            List<Integer> aL = new ArrayList<>();

            // x = base number minus the increment i.e. 10 - 1, 10 - 2, 10 - 3
            Integer x = n - i;
            // Add first digit of the starting partition
            aL.add(x);
            // Next digit
            Integer y = n - x;
            // Add the next digit
            aL.add(y);

            // Get the starting digit and add to the arraylist of starting digit partitions
            Integer startingDigit = new Integer(aL.get(0));

            if (y <= x) {
                sum.add(startingDigit);
            }

            List<Integer> partitionDigits = summations.get(aL.get(1));
            for (Integer digit: partitionDigits) {
                if (digit <= startingDigit) {
                    sum.add(new Integer(startingDigit));
                }
            }

            i = i + 1;
            return buildArrayList(n, i, sum);
        }
    }

    public static void main(String[] args) {

        /*
        * Manually build the first few values of the summation map to build higher values with.
        * */
        List<Integer> valuesOf1 = new ArrayList<>();
        List<Integer> valuesOf2 = new ArrayList<>();
        valuesOf2.add(1);
        List<Integer> valuesOf3 = new ArrayList<>();
        valuesOf3.add(2); valuesOf3.add(1);
        List<Integer> valuesOf4 = new ArrayList<>();
        valuesOf4.add(3); valuesOf4.add(2); valuesOf4.add(2); valuesOf4.add(1);

        summations.put(1, valuesOf1);
        summations.put(2, valuesOf2);
        summations.put(3, valuesOf3);
        summations.put(4, valuesOf4);


        Integer test = 50;

        for (Integer j = 5; j <= test; j++) {
            List<Integer> sum = new ArrayList<>();
            Integer n = j;
            Integer i = 1;

            System.out.println("N: " + j);
            summations.put(j, buildArrayList(n, i, sum));
            System.out.println(summations.get(j).size());
        }

        System.out.println(summations.get(test).size());
    }
}