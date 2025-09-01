package algorithms.simpleoperations;

public class ImperativeSimpleOperation {


    public static long sumElements(long range) {
        long soma = 0L;

        for (int i = 1; i <= range; i++) {
            if (i % 2 == 0) {
                soma += i * 3L;
            }
        }
        return soma;
    }
}

