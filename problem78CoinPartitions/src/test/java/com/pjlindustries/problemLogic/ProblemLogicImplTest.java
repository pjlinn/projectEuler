package com.pjlindustries.problemLogic;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProblemLogicImplTest {
    private final ProblemLogic problemLogic = new ProblemLogicImpl();

    @Test
    public void bruteForceRecursiveAlgorithmExample_simple_test() {
        SoftAssertions softly = new SoftAssertions();

//        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(0, new ArrayList<>(List.of(0)))).isEmpty();
//        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(1))).get()).isEqualTo(1);
//        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(2))).get()).isEqualTo(2);
//        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(3))).get()).isEqualTo(3);
//        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(4))).get()).isEqualTo(5);
        softly.assertThat(problemLogic.bruteForceRecursiveAlgorithmExample(1, new ArrayList<>(List.of(5))).get()).isEqualTo(7);

        softly.assertAll();
    }
}
