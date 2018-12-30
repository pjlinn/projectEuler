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

    static Map<Integer, Map<Integer, Integer>> summations = new HashMap<>();

    public static Map<Integer, Integer> buildMap(Integer n, Integer i, Map<Integer, Integer> sumMap) {
        // i increases by one each call, n stays current. I.e. n = 100, i = 1; n = 100, i = 2;
        if (n == i) {
            return sumMap;
        } else {
            // Create 2-index arrayList with n - i and n - (n - i). The sum of the two indicies will always be N
            List<Integer> aL = new ArrayList<>();

            Integer x = n - i;
            aL.add(x);
            Integer y = n - x;
            aL.add(y);

            Integer startingDigit = new Integer(aL.get(0));

            /**
             * When the second digit is less than the first, you can add
             * all of its partitions. E.g. x = 97, y = 3 means all partitions of
             * 3 work with 97 as the base number.
             *
             * -----
             * Check to make sure the key is there, otherwise add it and this first partition
             */
            if (y <= x) {
                if (sumMap.containsKey(startingDigit)) {
                    Integer sum = sumMap.get(startingDigit);
                    sumMap.put(startingDigit, sum + 1);
                } else {
                    sumMap.put(startingDigit, 1);
                }
            }

            /**
             * Get the map of base numbers and their partitions for y value.
             */
            Map<Integer, Integer> partitionSums = summations.get(aL.get(1));
            /**
             * Loop through the map. When the key is less than or equal to the
             * x value, you can add all the partitions. Otherwise, skip it because
             * it would have already been added.
             *
             * E.g. when it's x = 4, y = 5. Loop through the map of partitions for 5.
             * Add values when the partition base is 4 or less to the current map.
             * Pass the values and call this funciton again with i + 1.
             */
            for (Map.Entry<Integer, Integer> entry: partitionSums.entrySet()) {
                if (entry.getKey() <= startingDigit) {
                    if (sumMap.containsKey(startingDigit)) {
                        Integer localSum = sumMap.get(startingDigit);
                        sumMap.put(startingDigit, localSum + entry.getValue());
                    } else {
                        sumMap.put(startingDigit, entry.getValue());
                    }
                }
            }

            i = i + 1;
            return buildMap(n, i, sumMap);
        }
    }

    public static void main(String[] args) {
        summations.put(1, new HashMap<>());
        Integer end = 100;

        /**
         * Build a map where the key is the base value, and the value is a map where the key
         * is first value of the partition and the value is the number of partitions with that
         * key as the first value.
         */
        for (Integer j = 2; j <= end; j++) {
            Map<Integer, Integer> sum = new HashMap<>();
            Integer n = j;
            Integer i = 1;

            /**
             * j is the base value, buildMap will build the map of partition totals using previous calculations
             * in the summation map.
             */
            summations.put(j, buildMap(n, i, sum));
            Long total = 0L;
            /**
             * Sums the totals for each base value and prints it.
             */
            for (Map.Entry<Integer, Integer> entry: summations.get(j).entrySet()) {
                total = total + entry.getValue();
            }
            System.out.println("N: " + j + " total = " + total);
        }
    }
}