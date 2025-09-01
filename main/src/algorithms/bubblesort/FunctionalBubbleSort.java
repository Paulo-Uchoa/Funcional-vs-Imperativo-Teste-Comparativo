package algorithms.bubblesort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FunctionalBubbleSort {

    public static int[] order(int[] array) {
        return bubble(array, array.length);
    }

    private static int[] bubble(int[] array, int n) {
        if (n == 1) return array;

        int[] arr = IntStream.range(0, array.length - 1)
                .mapToObj(i -> {
                    if (array[i] > array[i + 1]) {
                        return new int[]{array[i + 1], array[i]};
                    } else {
                        return new int[]{array[i], array[i + 1]};
                    }
                })
                .flatMapToInt(Arrays::stream)
                .toArray();

        return bubble(arr, n - 1);
    }

}
