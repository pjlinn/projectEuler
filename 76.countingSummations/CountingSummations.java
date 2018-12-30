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
        if (n == i) {
            return sumMap;
        } else {
            List<Integer> aL = new ArrayList<>();

            Integer x = n - i;
            aL.add(x);
            Integer y = n - x;
            aL.add(y);

            Integer startingDigit = new Integer(aL.get(0));

            if (y <= x) {
                if (sumMap.containsKey(startingDigit)) {
                    Integer sum = sumMap.get(startingDigit);
                    sumMap.put(startingDigit, sum + 1);
                } else {
                    sumMap.put(startingDigit, 1);
                }
            }

            Map<Integer, Integer> partitionSums = summations.get(aL.get(1));
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
        Integer test = 100;

        for (Integer j = 2; j <= test; j++) {
            Map<Integer, Integer> sum = new HashMap<>();
            Integer n = j;
            Integer i = 1;

            summations.put(j, buildMap(n, i, sum));
            Long total = 0L;
            for (Map.Entry<Integer, Integer> entry: summations.get(j).entrySet()) {
                total = total + entry.getValue();
            }
            System.out.println("N: " + j + " total = " + total);
        }
    }
}