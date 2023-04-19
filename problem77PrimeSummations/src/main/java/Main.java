import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It is possible to write ten as the sum of primes in exactly five different ways:
 * <p>
 * 7 + 3
 * 5 + 5
 * 5 + 3 + 2
 * 3 + 3 + 2 + 2
 * 2 + 2 + 2 + 2 + 2
 * <p>
 * What is the first value which can be written as the sum of primes in over five thousand different ways?
 */
public class Main {
    public static List<Integer> primes = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499);
    public static Integer upTo = 80;

    public static void main(String[] args) {
        solve();
    }

    /**
     * Helper method to generate a seededSummationMap with the first two prime numbers, 2 and 3.
     * This map will contain a key, corresponding to the base number, and a value, that will hold a
     * list of the unique ways in which prime numbers can be added together to calculate the prime number.
     * <p>
     * I defer the uniqueness to the value's Set implementation by storing the list of integers in order so
     * that duplicates are automatically removed
     *
     * @return
     */
    public static Map<Integer, Set<List<Integer>>> generatedSeededSummationMap() {
        Map<Integer, Set<List<Integer>>> storedSummations = new HashMap<>();

        storedSummations.put(2, Set.of());
        storedSummations.put(3, Set.of());

        return storedSummations;
    }


    /**
     * @param listToHandle - List that needs to be checked and formatted
     * @param baseNumber   - Base number to ensure the list digits sum to
     * @return - Optional of the List in natural order that adds up to the base number; otherwise empty
     */
    public static Optional<List<Integer>> returnNormalizedAndValidatedList(
            final List<Integer> listToHandle
            , final Integer baseNumber
    ) {
        List<Integer> cleanedList = new ArrayList<>(listToHandle);
        cleanedList.sort(Comparator.naturalOrder());

        if (cleanedList.stream().reduce(0, Integer::sum).equals(baseNumber)) {
            return Optional.of(cleanedList);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Utility method for adding or updating a new key/value pair.
     *
     * @param storedSummations                                - Calculated Map<Integer, Set<List<Integer>>> of stored summations
     * @param baseNumber                                      - the base number used as key
     * @param formattedListOfPrimeIntegersThatSumToBaseNumber - the list we want to store
     */
    public static void updatePrimeSummationsMap(
            final Map<Integer, Set<List<Integer>>> storedSummations
            , final Integer baseNumber
            , final List<Integer> formattedListOfPrimeIntegersThatSumToBaseNumber
    ) {
        if (storedSummations.containsKey(baseNumber)) {
            storedSummations.get(baseNumber).add(formattedListOfPrimeIntegersThatSumToBaseNumber);
        } else {
            Set<List<Integer>> temp = new HashSet<>();
            temp.add(formattedListOfPrimeIntegersThatSumToBaseNumber);
            storedSummations.put(baseNumber, temp);
        }
    }

    public static Map<Integer, Set<List<Integer>>> solveV3() {
        Map<Integer, Set<List<Integer>>> storedSummations = Main.generatedSeededSummationMap();

        for (int base = 4; base < 75; base++) {
            for (int index = 0; index < primes.size(); index++) {
                var prime = primes.get(index);

                // Only need to subtract primes up to half of the base number to catch all unique possibilities
                if (prime > base / 2) {
                    break;
                }

                // Check for cases where the base is even divisible by the prime. Won't capture without an explicit check
                if (base % prime == 0) {
                    List<Integer> addThisList = new ArrayList<>();
                    for (int x = 0; x < base / prime; x++) {
                        addThisList.add(prime);
                    }
                    addThisList = Main.returnNormalizedAndValidatedList(addThisList, base).orElseThrow(() -> new RuntimeException("Number didn't add up and they should."));
                    Main.updatePrimeSummationsMap(storedSummations, base, addThisList);
                }

                var remainder = base - prime;
                var count = 0;
                // Subtract the prime from the base number until the prime is > the remainder
                while (remainder > prime) {
                    count++;
                    // If the remainder is prime, save as a new combination
                    if (primes.contains(remainder)) {
                        List<Integer> addThisList = new ArrayList<>();
                        addThisList.add(remainder);
                        for (int z = 0; z < count; z++) {
                            addThisList.add(prime);
                        }

                        addThisList = Main.returnNormalizedAndValidatedList(addThisList, base).orElseThrow(() -> new RuntimeException("Number didn't add up and they should."));
                        Main.updatePrimeSummationsMap(storedSummations, base, addThisList);
                    }
                    /**
                     * This is key, whatever the remainder is (e.g. 10 - 2 = 8 -> 2 + 8), all the previously calculated possible prime combinations of 8 will be valid here too
                     *
                     * Thus we create new lists for this base number, by add all the previous ones + the prime we subtracted
                     */
                    for (List<Integer> list : storedSummations.get(remainder)) {
                        var copy = new ArrayList<>(list);
                        for (int z = 0; z < count; z++) {
                            copy.add(prime);
                        }

                        copy = new ArrayList<>(Main.returnNormalizedAndValidatedList(copy, base).orElseThrow(() -> new RuntimeException("Number didn't add up and they should.")));
                        Main.updatePrimeSummationsMap(storedSummations, base, copy);
                    }
                    remainder = remainder - prime;
                }
            }
            if (storedSummations.get(base).size() >= 5000) {
                System.out.println(base);
                break;
            }
        }
        return storedSummations;
    }

    public static Map<Integer, Set<List<Integer>>> solveV2() {
        Map<Integer, Set<List<Integer>>> storedSummations = new HashMap<>();

        storedSummations.put(2, Set.of());
        storedSummations.put(3, Set.of());
        storedSummations.put(4, Set.of(
                Stream.of(2, 2).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(5, Set.of(
                Stream.of(2, 3).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(6, Set.of(
                Stream.of(2, 2, 2).sorted().collect(Collectors.toList()),
                Stream.of(3, 3).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(7, Set.of(
                Stream.of(5, 2).sorted().collect(Collectors.toList()),
                Stream.of(3, 2, 2).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(8, Set.of(
                Stream.of(5, 3).sorted().collect(Collectors.toList()),
                Stream.of(3, 3, 2).sorted().collect(Collectors.toList()),
                Stream.of(2, 2, 2, 2).sorted().collect(Collectors.toList())
        ));


        for (int base = 9; base < 75; base++) {

            for (int index = 0; index < primes.size(); index++) {
                var prime = primes.get(index);

                if (prime >= base) {
                    break;
                }

                if (base % prime == 0) {
                    List<Integer> addThisList = new ArrayList<>();
                    for (int x = 0; x < base / prime; x++) {
                        addThisList.add(prime);
                    }
                    if (addThisList.stream().reduce(0, Integer::sum) == base) {
                        if (storedSummations.containsKey(base)) {
                            storedSummations.get(base).add(addThisList);
                        } else {
                            Set<List<Integer>> temp = new HashSet<>();
                            temp.add(addThisList);
                            storedSummations.put(base, temp);
                        }
                    } else {
                        System.out.println("check this code");
                    }
                }

                var remainder = base - prime;
                var count = 0;
                while (remainder > prime) {
                    count++;
                    if (primes.contains(remainder)) {
                        List<Integer> addThisList = new ArrayList<>();
                        addThisList.add(remainder);
                        for (int z = 0; z < count; z++) {
                            addThisList.add(prime);
                        }
                        Main.formatListWrapper(addThisList);

                        if (addThisList.stream().reduce(0, Integer::sum) == base) {
                            if (storedSummations.containsKey(base)) {
                                storedSummations.get(base).add(addThisList);
                            } else {
                                Set<List<Integer>> temp = new HashSet<>();
                                temp.add(addThisList);
                                storedSummations.put(base, temp);
                            }
                        } else {
                            System.out.println("integers didn't sum to " + base);
                        }
                    }

                    for (List<Integer> list : storedSummations.get(remainder)) {
                        var copy = new ArrayList<>(list);
                        for (int z = 0; z < count; z++) {
                            copy.add(prime);
                        }
                        Main.formatListWrapper(copy);

                        if (copy.stream().reduce(0, Integer::sum).equals(base)) {
                            if (storedSummations.containsKey(base)) {
                                storedSummations.get(base).add(copy);
                            } else {
                                Set<List<Integer>> temp = new HashSet<>();
                                temp.add(copy);
                                storedSummations.put(base, temp);
                            }
                        } else {
                            System.out.println("v2 - integers didn't sum to " + base);
                        }
                    }
                    remainder = remainder - prime;
                }
            }
        }

        storedSummations.keySet().forEach(key -> {
            if (storedSummations.get(key).size() >= 5000) {
                System.out.println(key);
            }
            if (key == 13)
                System.out.println(storedSummations.get(key));
        });

        return storedSummations;
    }

    public static void formatListWrapper(final List<Integer> listToFormat) {
        listToFormat.sort(Comparator.naturalOrder());
    }

    public static Map<Integer, Set<List<Integer>>> solve() {

        Map<Integer, Set<List<Integer>>> storedSummations = new HashMap<>();


        storedSummations.put(4, Set.of(
                Stream.of(2, 2).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(5, Set.of(
                Stream.of(2, 3).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(6, Set.of(
                Stream.of(2, 2, 2).sorted().collect(Collectors.toList()),
                Stream.of(3, 3).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(7, Set.of(
                Stream.of(5, 2).sorted().collect(Collectors.toList()),
                Stream.of(3, 2, 2).sorted().collect(Collectors.toList())
        ));
        storedSummations.put(8, Set.of(
                Stream.of(5, 3).sorted().collect(Collectors.toList()),
                Stream.of(3, 3, 2).sorted().collect(Collectors.toList()),
                Stream.of(2, 2, 2, 2).sorted().collect(Collectors.toList())
        ));

        var seed2 = 7;
        var seed3 = 6;
        var seed5 = 4;

        for (int j = 0; j < upTo; j++) {
            var search1 = 9 + j;
            Set<List<Integer>> listsTwoAway = storedSummations.get(seed2 + j);
            Set<List<Integer>> listsThreeAway = storedSummations.get(seed3 + j);
            Set<List<Integer>> listsFiveAway = storedSummations.get(seed5 + j);

            Set<List<Integer>> newList = new HashSet<>();
            listsTwoAway.forEach(list -> {
                var copy = new ArrayList<>(list);
                copy.add(2);
                copy.sort(Comparator.naturalOrder());
                if (copy.stream().reduce(0, Integer::sum) == search1) {
                    newList.add(copy);
                } else {
                    System.out.println("did something wrong");
                }
            });
            listsThreeAway.forEach(list -> {
                var copy = new ArrayList<>(list);
                copy.add(3);
                copy.sort(Comparator.naturalOrder());
                if (copy.stream().reduce(0, Integer::sum) == search1) {
                    newList.add(copy);
                } else {
                    System.out.println("did something wrong");
                }
                newList.add(copy);
            });
            listsFiveAway.forEach(list -> {
                var copy = new ArrayList<>(list);
                copy.add(5);
                copy.sort(Comparator.naturalOrder());
                if (copy.stream().reduce(0, Integer::sum) == search1) {
                    newList.add(copy);
                } else {
                    System.out.println("did something wrong");
                }
                newList.add(copy);
            });

            storedSummations.put(search1, newList);

            for (int i = 0; i < primes.size(); i++) {
                var primeToSubtract = primes.get(i);
                if (primeToSubtract > (search1 * .5)) {
                    break;
                }
                // Check if the number is a factor of this prime
                if (search1 % primeToSubtract == 0) {
                    List<Integer> addThisList = new ArrayList<>();
                    for (int x = 0; x < search1 / primeToSubtract; x++) {
                        addThisList.add(primeToSubtract);
                    }
                    if (addThisList.stream().reduce(0, Integer::sum) == search1) {
                        storedSummations.get(search1).add(addThisList);
                    } else {
                        System.out.println("check this code");
                    }
                }
                var remainder = search1 - primeToSubtract;
                var count = 0;
                while (remainder > primeToSubtract) {
                    count++;
                    if (primes.contains(remainder)) {
                        List<Integer> addThisList = new ArrayList<>();
                        for (int z = 0; z < count; z++) {
                            addThisList.add(primeToSubtract);
                        }
                        addThisList.add(remainder);
                        addThisList.sort(Comparator.naturalOrder());

                        if (addThisList.stream().reduce(0, Integer::sum) == search1) {
                            storedSummations.get(search1).add(addThisList);
                        } else {
                            System.out.println("check this code");
                        }
                    }
                    remainder -= primeToSubtract;
                }
            }

            System.out.println(search1 + " has " + storedSummations.get(search1).size());
        }

        storedSummations.keySet().forEach(key -> {

//            System.out.println(key + " has " + storedSummations.get(key).size() + " prime summations");

            if (key == 9 | key == 11 || key == 12)
                System.out.println(storedSummations.get(key));
        });

        return storedSummations;
    }
}