package algorithms.simpleoperations;

import java.util.stream.LongStream;

public class FunctionalSimpleOperation {

    public static long sumElements(long range) {
        return LongStream.rangeClosed(1, range)
                .parallel()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 3)
                .sum();
    }
}
