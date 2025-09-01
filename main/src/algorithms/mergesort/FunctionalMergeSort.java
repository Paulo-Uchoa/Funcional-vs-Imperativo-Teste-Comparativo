package algorithms.mergesort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FunctionalMergeSort {

        public static int[] order(int[] array) {
            return mergeSort(Arrays.copyOf(array, array.length));
        }

        private static int[] mergeSort(int[] array) {
            if (array.length <= 1) return array;

            int mid = array.length / 2;

            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            left = mergeSort(left);
            right = mergeSort(right);

            return merge(left, right);
        }

        private static int[] merge(int[] left, int[] right) {
            int[] result = new int[left.length + right.length];
            int li = 0, ri = 0, riResult = 0;

            while (li < left.length && ri < right.length) {
                if (left[li] <= right[ri]) {
                    result[riResult++] = left[li++];
                } else {
                    result[riResult++] = right[ri++];
                }
            }

            while (li < left.length) result[riResult++] = left[li++];
            while (ri < right.length) result[riResult++] = right[ri++];

            return result;
        }

}
