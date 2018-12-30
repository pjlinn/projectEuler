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
 * My first thought is to create a map with the integers from 1 to n as the
 * key, and the value is the number of ways to write the sum using positive
 * integers. I should be able to use the previous values to build up new values.
 * 
 * If I knew the combinations for n-1, it might be easier to add the rest
 * for n. Or maybe just use recursion.
 */

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CountingSummations {

    static Map<Long, Long> summations = new HashMap<>();

    public static ArrayList<Integer> buildArrayListProper(Integer n, Integer i) {
        ArrayList<Integer> aL = new ArrayList<>();

            Integer x = n - i;
            aL.add(x);
            Integer y = n - x;
            y = Math.min(x, y);
            aL.add(y);

            Integer total = sum(aL);

            while (n != total) {
                y = n - total;
                y = Math.min(x, y);
                aL.add(y);
                total = sum(aL);
            }

            return aL;
    }

//    public static Long buildArrayList(Long n, Long i, Long sum) {
//        if (n == i) {
//            return sum;
//        } else {
//            List<Long> aL = new ArrayList<>();
//
//            Long x = n - i;
//            aL.add(x);
//            Long y = n - x;
//            y = Math.min(x, y);
//            aL.add(y);
//
//            Long total = sum(aL);
//
//            while (n != total) {
//                y = n - total;
//                y = Math.min(x, y);
//                aL.add(y);
//                total = sum(aL);
//            }
//
//            sum = sum + 1;
//            System.out.println(aL);
//            for (Integer p = 1; p < aL.size(); p++) {
//                sum = sum + summations.get(aL.get(p));
//            }
//
//            i = i + 1L;
//            return buildArrayList(n, i, sum);
//        }
//    }

    public static Integer sum(List<Integer> values) {
        Integer total = 0;
        for (Integer i: values) {
            total = total + i;
        }
        return total;
    }

    public static Integer tryAgain(ArrayList<Integer> x, Integer sum) {
        if (x.get(0) == 1) {
            return sum;
        }

        if ( (x.size() >= 3) ) {

            Integer current = x.size() - 1;
            Integer backOne = x.size() - 2;
            Integer backTwo = x.size() - 3;
            Integer curVal = x.get(current);
            Integer backOneVal = x.get(backOne);
            Integer backTwoVal = x.get(backTwo);

            if (curVal + backOneVal <= backTwoVal) {
                x.remove(current);
                x.remove(backOne);
                x.add(backOne, curVal + backOneVal);
                System.out.println(x);
                sum = sum + 1;
                return tryAgain(x, sum);
            } else {
                for (int index = x.size() - 1; index >= 0; index--) {
                    Integer currentVal = x.get(index);
                    if (currentVal == 1) {

                    } else {
                        x.remove(index);
                        x.add(index, currentVal - 1);

                        if (index == x.size() - 1) {
                            x.add(1);
                            sum = sum + 1;
                            System.out.println(x);
                        } else {
                            Integer nextVal = x.get(index + 1);

                            if (currentVal - 1 == nextVal) {
                                x.add(1);
                            } else {
                                x.remove(index + 1);
                                x.add(index + 1, nextVal + 1);
                            }
                            sum = sum + 1;
                            System.out.println(x);
                            break;
                        }
                    }
                }
                return tryAgain(x, sum);
            }
        } else {
            for (int index = x.size() - 1; index >= 0; index--) {
                Integer currentVal = x.get(index);
                if (currentVal == 1) {

                } else {
                    x.remove(index);
                    x.add(index, currentVal - 1);

                    if (index == x.size() - 1) {
                        x.add(1);
                        sum = sum + 1;
                        System.out.println(x);
                    } else {
                        Integer nextVal = x.get(index + 1);

                        if (currentVal - 1 == nextVal) {
                            x.add(1);
                        } else {
                            x.remove(index + 1);
                            x.add(index + 1, nextVal + 1);
                        }
                        sum = sum + 1;
                        System.out.println(x);
                        break;
                    }
                }
            }
            return tryAgain(x, sum);
        }
    }

    public static ArrayList<ArrayList<Integer>> tryTryAgain(Integer n, ArrayList<Integer> x, ArrayList<ArrayList<Integer>> sum) {
        Boolean generateNewList = false;
        Integer innerSum = 0;

        if (x.get(0) == 1) {
            return sum;
        }

        /*
        * Check if we need to generate new Array List by decreasing the first index by 1
        * */
        for (int index = x.size() - 1; index >= 1; index--) {
            Integer currentVal = x.get(index);

            if (currentVal == 1) {
                // check the next one
                generateNewList = true;
                innerSum = innerSum + 1;
            } else {
                generateNewList = false;
                break;
            }
        }

        if (generateNewList) {
            x = buildArrayListProper(n, innerSum + 1);
            sum.add(new ArrayList<Integer>(x));
//            System.out.println("here: " + x + " " + (innerSum + 1));
            return tryTryAgain(n, x, sum);
        } else {
            for (int index = x.size() - 1; index >= 0; index--) {
                Integer currentVal = x.get(index);
                if (currentVal == 1) {

                } else {
                    x.remove(index);
                    x.add(index, currentVal - 1);

                    if (index == x.size() - 1) {
                        x.add(1);
                        sum.add(new ArrayList<Integer>(x));
//                        System.out.println("here2: " + x);
                        break;
                    } else {
                        Integer nextVal = x.get(index + 1);

                        if (currentVal - 1 == nextVal) {
                            x.add(1);
                        } else {
                            x.remove(index + 1);
                            x.add(index + 1, nextVal + 1);
                        }
                        sum.add(new ArrayList<Integer>(x));
//                        System.out.println("here3: " + x);
                        break;
                    }
                }
            }
            return tryTryAgain(n, x, sum);
        }
    }

    public static Integer generateNewArrayList(ArrayList<Integer> x) {
        Integer innerSum = 0;

        for (int index = x.size() - 1; index >= 1; index--) {
            Integer currentVal = x.get(index);

            if (currentVal == 1) {
                // check the next one
                innerSum = innerSum + 1;
            } else {
//                System.out.println("Don't need new list: " + x);
                return 0;
            }
        }
//        System.out.println("Need new list: " + x);
        return innerSum;
    }

    public static ArrayList<ArrayList<Integer>> partitions(Integer n, ArrayList<Integer> list,
                                                           ArrayList<ArrayList<Integer>> sum) {
//        System.out.println("sum here: " + sum);
        if (list.get(0) == 1) {
            return sum;
        }

        /**
         * Check if we need to generate a new ArrayList
         */
        Integer innerSum = generateNewArrayList(list);

        if (innerSum > 0 || list.size() == 1) {
            list = buildArrayListProper(n, innerSum + 1);
            sum.add(new ArrayList<Integer>(list));
//            System.out.println("got new list: " + list);
//            System.out.println(list);
            return partitions(n, list, sum);
        } else {
            for (int index = 1; index < list.size(); index++) {
                Integer indexVal = list.get(index);
                // check if last element
                if (index == list.size() - 1) {
                    list.remove(index);
                    list.add(index, indexVal - 1);
                    list.add(1);
                    Collections.sort(list, Collections.reverseOrder());
//                    System.out.println("at last index add one to end" + list);
//                    System.out.println(list);
//                    System.out.println(sum);
                    sum.add(new ArrayList<Integer>(list));
//                    System.out.println(sum);
                    return partitions(n, list, sum);
                }
                for (int nextIndex = index + 1; nextIndex < list.size(); nextIndex++) {
                    Integer nextIndexVal = list.get(nextIndex);

                    if (indexVal - 1 <= nextIndexVal) {
                        // check if the next index is last
                        if (nextIndex == list.size() - 1) {
                            list.remove(index);
                            list.add(index, indexVal - 1);
                            list.add(1);
                            Collections.sort(list, Collections.reverseOrder());
                            sum.add(new ArrayList<Integer>(list));
//                            System.out.println("the next index is last, add one" + list);
//                            System.out.println(list);
                            return partitions(n, list, sum);
                        }
                    } else {
                        list.remove(index);
                        list.add(index, indexVal - 1);
                        list.remove(nextIndex);
                        list.add(nextIndex, nextIndexVal + 1);
                        Collections.sort(list, Collections.reverseOrder());
//                        System.out.println("next index can increase by one: " + list);
//                        System.out.println(sum);
                        sum.add(new ArrayList<Integer>(list));
//                        System.out.println(sum);
                        return partitions(n, list, sum);
                    }
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("shouldn't be here: " + list);
//        sum = sum + 1;
        return partitions(n, list, sum);
    }

    public static void main(String[] args) {

//        summations.put(1L, 0L);
//
//        for (Long j = 2L; j <= 7; j++) {
//            Long sum = 0L;
//            Long n = j;
//            Long i = 1L;
//
//            summations.put(j, buildArrayList(n, i, sum));
//        }

//        System.out.println(summations);

        ArrayList<Integer> n1 = new ArrayList<>();
        ArrayList<Integer> n2 = new ArrayList<>();

        ArrayList<ArrayList<Integer>> emptyList = new ArrayList<>();

        ArrayList<ArrayList<Integer>> listOfLists1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> listOfLists2 = new ArrayList<>();

        Integer a = 12;
        n1.add(a);

        listOfLists1 = partitions(a, n1, new ArrayList<ArrayList<Integer>>());
        listOfLists2 = tryTryAgain(a, n1, new ArrayList<>());

        Set<ArrayList<Integer>> set = new HashSet<>();
        set.addAll(listOfLists1);
        set.addAll(listOfLists2);

        System.out.println(set.size());
    }
}