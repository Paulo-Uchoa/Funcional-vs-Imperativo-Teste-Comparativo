package algorithms.bubblesort;

import java.util.Arrays;

public class FunctionalBubbleSort {

        public static int[] order(int[] array) {
            return bubble(Arrays.copyOf(array, array.length), array.length);
        }

        private static int[] bubble(int[] array, int n) {
            if (n == 1) return array;

            int[] next = new int[array.length];
            System.arraycopy(array, 0, next, 0, array.length);

            next = singlePass(next, 0, n);

            return bubble(next, n - 1);
        }

        private static int[] singlePass(int[] array, int index, int n) {
            if (index >= n - 1) return array;

            int[] newArray = Arrays.copyOf(array, array.length);

            if (array[index] > array[index + 1]) {

                newArray[index] = array[index + 1];
                newArray[index + 1] = array[index];
            }

            return singlePass(newArray, index + 1, n);
        }
    }
