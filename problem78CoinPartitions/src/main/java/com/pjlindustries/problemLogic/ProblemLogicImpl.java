package com.pjlindustries.problemLogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProblemLogicImpl implements ProblemLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemLogicImpl.class);

    /**
     * Key - Integer - Number of total coins
     * Value - Long - Number of distinct piles
     * <p>
     * Use this map to store the number of distinct piles we calculate for each number of coins, so we don't have
     * to recalculate values we already know from previous runs.
     */
    Map<Integer, Long> coinsToDistinctNumberOfPilesMap;

    @Override
    public Optional<Long> calculateTheDistinctNumberOfPiles(final int numberOfCoins) {
        try {
            return (this.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(numberOfCoins))).isPresent()
                    ? this.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(numberOfCoins))) : Optional.of(0L));
        } catch (Exception e) {
            LOGGER.info("Exception in calculateTheDistinctNumberOfPiles", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Long> bruteForceRecursiveAlgorithmExample(final long count, final List<Integer> currentPile) {
        if (count < 1) {
            LOGGER.error("Count should at least be 1 signifying the pile with all n number of coins in it.");
            return Optional.empty();
        }

        if (currentPile.contains(0)) {
            LOGGER.error("Something wrong, there should be no zeros in the list.");
            return Optional.empty();
        }

        int currentPileSize = currentPile.size();

        if (currentPileSize == 1) {
            LOGGER.info("Updated List: {}", currentPile);
        }

        long newCount = count;

        // if the list is just the number one, return 1
        if (currentPileSize == 1 && currentPile.get(0) == 1) {
            return Optional.of(1L);
        }

        if (currentPile.get(0) == 1) {
            return Optional.of(count);
        }

        try {
            var startingIndexValue = currentPileSize - 1;
            for (int index = startingIndexValue; index >= 0; index--) {
                int indexValue = currentPile.get(index);
                boolean lastIndex = startingIndexValue == index;

                // If the value isn't 1 continue
                if (indexValue > 1) {
                    int newIndexValue = indexValue - 1;

                    if (lastIndex) {
                        currentPile.set(index, newIndexValue);
                        currentPile.add(1);
                    } else {
                        currentPile.set(index, newIndexValue);
                        var nextIndexNewValue = currentPile.get(index + 1) + 1;
                        if (nextIndexNewValue > newIndexValue) {
                            currentPile.add(1);
                        } else {
                            currentPile.set(index + 1, nextIndexNewValue);
                        }
                    }
                    newCount = newCount + 1;
                    break;
                }
            }

            LOGGER.info("Updated List: {}", currentPile);
        } catch (Exception e) {
            LOGGER.info("Exception in recursivePileCalculator", e);
            return Optional.empty();
        }

        return this.bruteForceRecursiveAlgorithmExample(newCount, currentPile);
    }
}
