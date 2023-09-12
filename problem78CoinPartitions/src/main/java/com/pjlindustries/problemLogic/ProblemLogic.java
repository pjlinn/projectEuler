package com.pjlindustries.problemLogic;

import java.util.List;
import java.util.Optional;

public interface ProblemLogic {

    Optional<Long> calculateTheDistinctNumberOfPiles(final int numberOfCoins);

    Optional<Long> bruteForceRecursiveAlgorithmExample(final long count, final List<Integer> currentPile);
}
