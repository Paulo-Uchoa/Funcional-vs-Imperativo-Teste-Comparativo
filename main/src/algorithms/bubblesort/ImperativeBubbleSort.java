package algorithms.bubblesort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ImperativeBubbleSort {

    public static int[] order(int[] array) {
        return bubble(array, array.length);
    }

    private static int[] bubble(int[] array,  int n) {
        if (n == 1) return array;

        int[] arr = Arrays.copyOf(array, n);
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    changed = true;
                }
            }
            n--;
        } while (changed);
        return arr;
    }

}
