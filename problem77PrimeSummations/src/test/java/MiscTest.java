import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MiscTest {

    @Test
    public void testSetBehvaior() {
        Set<List<Integer>> test = new HashSet<>();
        test.add(Stream.of(2, 3).sorted().collect(Collectors.toList()));
        test.add(Stream.of(3, 2).sorted().collect(Collectors.toList()));

        assertThat(test.size()).isEqualTo(1);
    }

    @Test
    public void testSolve() {
        SoftAssertions softly = new SoftAssertions();

        var result = Main.solveV3();
        softly.assertThat(result.get(9).size()).isEqualTo(4);
        softly.assertThat(result.get(10).size()).isEqualTo(5);
        softly.assertThat(result.get(13).size()).isEqualTo(8);
        softly.assertThat(result.get(15).size()).isEqualTo(12);
        softly.assertThat(result.get(49).size()).isEqualTo(744);
        softly.assertThat(result.get(71).size()).isEqualTo(5006);

        softly.assertAll();
    }
}
