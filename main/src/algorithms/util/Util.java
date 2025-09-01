package algorithms.util;

import algorithms.orderingsystem.Order;
import algorithms.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Util {

    private static final Random rand = new Random();

    public static int[] randomIntArray(int n, int min, int max) {
        return IntStream.range(0, n)
                .map(i -> rand.nextInt(max - min + 1) + min)
                .toArray();
    }

    public static List<Order> randomOrders(int n) {
        String[] categories = {"Eletrônicos", "Roupas", "Alimentos", "Brinquedos", "Livros"};
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String category = categories[i % categories.length] + " " + (i / categories.length + 1);
            double value = rand.nextInt(2000) + 1;
            orders.add(new Order(category, value));
        }
        return orders;
    }

    public static Node randomTree(int height, int min, int max) {
        if (height == 0) return null;
        Node left = randomTree(height - 1, min, max);
        Node right = randomTree(height - 1, min, max);
        return new Node(rand.nextInt(max - min + 1) + min, left, right);
    }
}
